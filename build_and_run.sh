#!/bin/bash
IMAGE_NAME=qn_system
IMG_TAG=registry.cn-qingdao.aliyuncs.com/bookln/${IMAGE_NAME}

docker build -t ${IMG_TAG} --platform linux/amd64 .

# 容器名称
CONTAINER_NAME="hao_app"

# 检查容器是否正在运行
if docker ps -q -f name=$CONTAINER_NAME; then
    echo "Container $CONTAINER_NAME is running. Stopping and removing it..."
    # 停止并删除容器
    docker stop $CONTAINER_NAME
    docker rm $CONTAINER_NAME
else
    # 检查容器是否存在但未运行
    if docker ps -aq -f name=$CONTAINER_NAME; then
        echo "Container $CONTAINER_NAME exists but is not running. Removing it..."
        # 删除容器
        docker rm $CONTAINER_NAME
    else
        echo "Container $CONTAINER_NAME does not exist."
    fi
fi

# 运行新的容器
echo "Running a new container with the name $CONTAINER_NAME..."
docker run --name $CONTAINER_NAME \
  --cpus="8" \
  -m="4g" \
  --privileged \
  -u root \
  -d \
  -p 8092:80 \
  ${IMG_TAG}

echo "New container $CONTAINER_NAME has been started."