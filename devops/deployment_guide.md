# Deployment Guide
This guide documents the steps performed in order to deliver the software to the production server.

## Software Packaging
Execute the following commands in @/app:

    # Production
    ./mvnw clean package -Ddocker

## OS Configuration
Execute the following commands in @/devops.

    # Production
    ansible-playbook -i environments/production os.yml

## Software Deployment
Execute the following commands in @/devops.

    # Production
    ansible-playbook -i environments/production webapp.yml -e "webapp_image=shop-management:1.0.0"