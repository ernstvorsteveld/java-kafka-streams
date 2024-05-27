package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.in.http;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

record ExceptionRule(Class<?> exceptionClass, HttpStatus status) {
}

public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private final List<ExceptionRule> exceptionsRules = List.of(
            new ExceptionRule(Exception.class, HttpStatus.NOT_ACCEPTABLE)
    );


    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = getError(request);

        final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        Optional<ExceptionRule> exceptionRuleOptional = exceptionsRules.stream()
                .map(exceptionRule -> exceptionRule.exceptionClass().isInstance(error) ? exceptionRule : null)
                .filter(Objects::nonNull)
                .findFirst();

        return exceptionRuleOptional
                .<Map<String, Object>>map(exceptionRule ->
                        Map.of(
                                ErrorAttributes.STATUS_CODE.name(), exceptionRule.status().value(),
                                ErrorAttributes.ERROR_CODE.getAttribute(), getErrorCode(error),
                                ErrorAttributes.MESSAGE.getAttribute(), error.getMessage(),
                                ErrorAttributes.TIMESTAMP.getAttribute(), timestamp))
                .orElseGet(() ->
                        Map.of(
                                ErrorAttributes.STATUS_CODE.getAttribute(), getHttpStatus(error).value(),
                                ErrorAttributes.MESSAGE.getAttribute(), error.getMessage(),
                                ErrorAttributes.TIMESTAMP.getAttribute(), timestamp));
    }

    private String getErrorCode(Throwable error) {
        if (error == null) {
            return "NULL";
        }
        return "TBD";
    }

    private HttpStatusCode getHttpStatus(Throwable error) {
        return error instanceof ResponseStatusException err ? err.getStatusCode() :
                MergedAnnotations.from(error.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY)
                        .get(ResponseStatus.class).getValue(ErrorAttributes.STATUS_CODE.getAttribute(), HttpStatus.class)
                        .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
