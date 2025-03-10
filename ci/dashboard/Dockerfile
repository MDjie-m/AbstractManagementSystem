# 使用官方Nginx镜像作为基础镜像
FROM nginx:latest

# +8 时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 复制自定义Nginx配置文件到容器中
COPY nginx.conf /etc/nginx/nginx.conf

# 可选：如果您有其他静态文件或网站内容，可以将它们复制到容器中的合适目录
COPY dist /usr/share/ruoyi/dist

# 可选：如果需要将端口映射到主机，请取消注释以下行
EXPOSE 80


# 启动Nginx服务
CMD ["nginx", "-g", "daemon off;"]
