# /bin/bash

# Defining variables with values from docker-compose
NETWORK_NAME="outbox-net"
VOLUME_NAME="pgdata"
COMPOSE_FILE="docker-compose.yaml"
DOCKERFILE="postgres.dockerfile"
CONTAINER_NAME="outbox_event_listner_pg"

#create network
create_network() {
    echo "Creating network..."
    if [ $(docker network ls | grep -w "$NETWORK_NAME" | wc -l) -eq 0 ]; then
        docker network create "$NETWORK_NAME"
    else
        echo "Network $NETWORK_NAME already exists"
    fi
}

#crerate volume
create_volume() {
    echo "Creating volume..."
    if [ $(docker volume ls | grep -w "$VOLUME_NAME" | wc -l) -eq 0 ]; then
        docker volume create "$VOLUME_NAME"
    else
        echo "Volume $VOLUME_NAME already exists"
    fi
}

# create service method
create_service(){
    create_network
    create_volume
    echo "Running docker-compose ..."
    docker-compose up -d
}


#check if container is already running
if docker ps --format '{{.Names}}' | grep -q "^$CONTAINER_NAME$"; then
    echo "Postgres service is already running with container: $CONTAINER_NAME"
elif docker ps -a --format '{{.Names}}' | grep -q "^$CONTAINER_NAME$"; then 
    echo "Postgres service already exists with container name: $CONTAINER_NAME but is stopped"
    echo "Please select an option from below:"
    echo "1. Start the service"
    echo "2. Remove the existing container and recreate "
    echo "3. Exit"
    read -p "Enter your choice: " choice

    case $choice in
        1)
            echo "Starting the service"
            docker start "$CONTAINER_NAME"
            ;;
        2)
            echo "Removing the existing service"
            docker rm -f "$CONTAINER_NAME"
            echo "Creating new service"
            create_service
            ;;
        3)
            echo "Exiting"
            exit 0
            ;;
        *)
            echo "Invalid choice"
            ;;
    esac
else
    create_service
fi