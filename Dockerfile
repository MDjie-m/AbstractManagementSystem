FROM registry.cn-shenzhen.aliyuncs.com/haoziu/qnb:base_v1

MAINTAINER hao

ENV LANG C.UTF-8
ENV HOME /root

# Set timezone
ENV DEBIAN_FRONTEND noninteractive
ENV DEBCONF_NOWARNINGS yes

COPY app.jar /opt/
COPY run.sh /opt/
COPY dist/ /opt/dist/

RUN mkdir /opt/uploadPath

RUN chmod +x /opt/run.sh

WORKDIR /opt

CMD ["./run.sh"]