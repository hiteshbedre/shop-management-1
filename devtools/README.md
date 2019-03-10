# Developer Guide

## Setup Database
Execute the following commands in @/devtools:

    docker-compose -p shop up -d

## Database Updates
Auto-DDL is set in dev environment.
Execute the following command at @/app to generate liquibase changelog:

    ./mvnw liquibase:generateChangeLog