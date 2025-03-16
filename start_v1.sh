#!/bin/bash
java -Xms1g -Xmx1g -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -jar ruoyi-admin/target/ruoyi-admin.jar
