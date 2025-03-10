#!/bin/bash

mkdir -p /data/ruoyi/mysql/log
mkdir -p /data/ruoyi/mysql/data

mkdir -p /data/ruoyi/redis/log
mkdir -p /data/ruoyi/redis/data

mkdir -p /data/ruoyi/admin/data
mkdir -p /data/ruoyi/admin/upload
mkdir -p /data/ruoyi/admin/logs
mkdir -p /data/ruoyi/admin/conf

mkdir -p /data/ruoyi/dashboard/logs

cp ../../sql/* ./mysql/sql/
cp ./ruoyi/conf/* /data/ruoyi/admin/conf
