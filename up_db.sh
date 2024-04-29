CONTAINER_NAME=$1
CONTAINER_NAME=${CONTAINER_NAME:='ktor_authentication'}

if [[ $(docker ps -a --filter="name=$CONTAINER_NAME" --filter "status=exited" | grep -w "$CONTAINER_NAME") ]]; then
    echo "Start container $CONTAINER_NAME"
    docker-compose up
elif [[ $(docker ps -a --filter="name=$CONTAINER_NAME" --filter "status=running" | grep -w "$CONTAINER_NAME") ]]; then
    echo "docker still running"
else
    echo "Build container $CONTAINER_NAME"
    docker-compose build --no-cache
     echo "Start container $CONTAINER_NAME after build"
    docker-compose up
fi