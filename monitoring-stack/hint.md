sudo docker rm -vf $(docker ps -aq) - remove all containers

sudo docker rmi -f $(docker images -q) - remove all images
