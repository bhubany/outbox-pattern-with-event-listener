# /bin/bash

# Defining variables with values from docker-compose
NETWORK_NAME="outbox-net"
VOLUME_NAME="pgdata"
COMPOSE_FILE="docker-compose.yaml"
DOCKERFILE="postgres.dockerfile"
# CONTAINER_NAME="outbox_event_listner_pg"
CONTAINER_NAME="outboxwitheventlistner_postgres_1"


# create service method
create_service(){
    echo "Creating service"
}


#check if container is already running
if 
    docker ps -a --format '{{.Names}}' | grep -q "^$CONTAINER_NAME$" ; 
then 
    echo "Service is already running with container name: $CONTAINER_NAME"
    echo "Do you want to remove existing and recreate it ?\n(Y/N):"
    read res
    create_service
    echo $res
fi
