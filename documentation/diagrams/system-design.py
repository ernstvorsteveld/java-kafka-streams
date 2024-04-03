from diagrams import Diagram
from diagrams.c4 import SystemBoundary, Container, Relationship

graph_attr = {
    "splines": "spline",
}
with Diagram("Article Price Change Notification Application", direction="TB", graph_attr=graph_attr, outformat=["png","svg"]):

    with SystemBoundary("Article Price and Notification Application"):

        with SystemBoundary("Article Price Module"):
            articlePriceAPIInPort = Container(
                name="Article Price HTTP InPort",
                technology="Java and Spring MVC",
                description="Article Price management HTTP Consumer",
            )
            articlePriceSocketInPort = Container(
                name="Article Price TcpSocket InPort",
                technology="Java and Spring MVC",
                description="Article Price management TcpSocket Consumer",
            )
            articlePriceService = Container(
                name="Article Price Service",
                technology="Java",
                description="Provides Article Price Services",
            )
            articlePriceKafkaOutPort = Container(
                name = "Article Price Producer OutPort",
                technology="Java, Spring and Kafka",
                description="Article Price Change Producer",
            )

            articlePriceChangeKafkaInPort = Container(
                name = "Price Change InPort",
                technology="Java, Spring and Kafka",
                description="Article Price Change Consumer",
            )
            articlePriceChangeKafkaOutPort = Container(
                name="Current Price OutPort",
                technology="Java, Spring, Kafka",
                description="Current Article Price Producer",                
            )

        with SystemBoundary("Boundary Module"):
            boundaryInPort = Container (
                name = "Boundary Crud InPort",
                technology="Java, Spring and Kafka",
                description="Boundary Crud Consumer",
            )
            boundaryService = Container(
                name = "Boundary Cross Service",
                technology="Java",
                description="Calculates Price Boundary crossings",
            )
            boundaryOutPort = Container(
                name = "Boundary Crud OutPort",
                technology="Java, Spring and Kafka",
                description="Boundary Crud Producer Producer",
            )
            boundaryCrossingInPort = Container(
                name = "Boundary Query InPort",
                technology="Java, Spring and Kafka",
                description="Query Boundary Crossing Consumer Consumer",
            )
            boundaryCrossedOutPort = Container(
                name = "Boundary Crossed OutPort",
                technology="Spring and Kafka",
                description="Notification Producer",
            )

        with SystemBoundary("Kafka"):
            ksqldb = Container(
                name="KSQLDB",
                technology="KSQLSB",
                description="KSQLDB"
            )
            kafka = Container(
                name="Kafka",
                technology="Kafka Broker and Controller",
                description="Kafka Topics and Streams"
            )

        with SystemBoundary("Notification Module"):
            priceNotificationInPort = Container(
                name="Notification InPort",
                technology="Java, Spring and Kafka",
                description="Price Notification Consumer",
            )
            priceNotificationService = Container(
                name="Execute Notifications",
                technology="Java",
                description="Price Notification management",
            )

    articlePriceAPIInPort >> Relationship("Produces Article Price changes") >> articlePriceService
    articlePriceSocketInPort >> Relationship("Produces Article Price changes") >> articlePriceService
    articlePriceService >> Relationship("") >> articlePriceKafkaOutPort
    articlePriceKafkaOutPort >> Relationship("1. Article Price Changed Event") >> kafka

    ksqldb >> Relationship("6. Consumer Crossing Events") >> boundaryCrossingInPort
    boundaryService >> Relationship("Selects Notification Events") >> boundaryCrossedOutPort
    boundaryCrossedOutPort >> Relationship("7. Publish Crossing Event") >> kafka

    boundaryInPort >> Relationship("4. Boundary Notification Changed") >> boundaryService
    boundaryService >> Relationship("") >> boundaryOutPort
    boundaryCrossingInPort >> Relationship("") >> boundaryService
    boundaryOutPort >> Relationship("5. Produce Boundary Changed Event") >> kafka

    kafka >> Relationship("2") >> articlePriceChangeKafkaInPort
    articlePriceChangeKafkaInPort >> Relationship("") >> articlePriceService
    articlePriceService >> Relationship("") >> articlePriceChangeKafkaOutPort
    articlePriceChangeKafkaOutPort >> Relationship("3. Publish Price Threshold Changed Event") >> kafka

    kafka >> Relationship("8. Consume Boundary Crossed Event") >> priceNotificationInPort
    priceNotificationInPort >> Relationship("") >> priceNotificationService

    kafka >> Relationship("") >> ksqldb