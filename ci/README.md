# 使用docker部署
> 需要安装docker 和 docker-compose。自行百度安装

## 准备工作
> 将最新的jar文件放在 /ci/ruoyi/ 下，名称为 ruoyi-admin.jar
>
> 将最新的前端文件放在 /ci/dashboard/ 下，名称为dist
> 
> 初始mysql脚本放在 /ci/mysql/sql/ 下，名称任意   
> 
> 

## 使用脚本打docker镜像&启动
``` 
-- 打镜像&启动容器
docker-compose up -d ruoyi-mysql
```

## 后续启动&停止容器
``` 
-- 查看启动中的容器   
docker ps

-- 查看所有容器(包括运行和停止的容器)   
docker ps -a

-- 停容器
docker stop 容器名称

-- 停所有容器，并删除所有容器
docker-compose down

-- 启动容器
docker-compose up -d 服务名称（compose中定义的名称）      
eg: docker-compose up -d ruoyi-mysql
eg: docker-compose up -d ruoyi-redis
```