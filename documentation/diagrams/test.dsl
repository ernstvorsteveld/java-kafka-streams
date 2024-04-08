workspace {

    model {
        user = person "User"
        
        softwareSystem = softwareSystem "Software System" {
            webapp = container "Web Application"
            db = container "Database Schema"
        }

        user -> webapp "Uses"
        webapp -> db "Reads from and writes to"
    }

    views {
        systemContext softwareSystem "SystemContext" {
            include *
            autolayout lr
        }

        container softwareSystem "Containers" {
            include *
            autolayout lr
        }
        
        styles {
            element "Person" {
                shape person
            }
        }
    }

}
