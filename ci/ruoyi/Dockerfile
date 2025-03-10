# 使用官方 OpenJDK 8 镜像作为基础镜像
FROM eclipse-temurin:8-jdk-focal

# +8 时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 设置工作目录
WORKDIR /app

# 复制您的 Java JAR 文件到容器中
COPY ruoyi-admin.jar arthas-boot.jar /app/

# 创建多个目录，使用 && 运算符来组合多个命令
RUN mkdir /app/upload && \
    mkdir /app/logs && \
    mkdir /app/data

ENV JAVA_OPTS=""
# 环境变量来配置 JVM 参数，默认为空

# 使用 java -jar 命令和配置的 JVM 参数启动您的应用程序
CMD ["sh", "-c", "java $JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar ruoyi-admin.jar"]
