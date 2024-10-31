rootProject.name = "Project-T"
include("radar-data")
include("radar-data:radar-data-app")
findProject(":radar-data:radar-data-app")?.name = "radar-data-app"
include("radar-data:radar-data-models")
findProject(":radar-data:radar-data-models")?.name = "radar-data-models"
include("api-gateway")
include("radar-data:radar-data-client")
findProject(":radar-data:radar-data-client")?.name = "radar-data-client"
include("vote-data")
include("vote-data:vote-data-client")
findProject(":vote-data:vote-data-client")?.name = "vote-data-client"
include("vote-data:vote-data-app")
findProject(":vote-data:vote-data-app")?.name = "vote-data-app"
