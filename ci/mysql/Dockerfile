# 使用官方MySQL镜像作为基础镜像
FROM mysql:8.0

# +8 时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# charset 设置
COPY ./charset.cnf /etc/mysql/conf.d/charset.cnf

# 将多个初始化SQL脚本复制到容器的初始化目录
COPY ./sql/* /docker-entrypoint-initdb.d/

# 修改文件权限以确保MySQL可以读取
RUN chmod -R 755 /docker-entrypoint-initdb.d
