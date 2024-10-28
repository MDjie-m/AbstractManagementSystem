package com.ruoyi.web.controller.data;

import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "数据采集")
@RestController
@RequestMapping("/data")
public class DataApiController {

    @ApiOperation("获取设备数据")
    @ResponseBody
    public R<String> getDeviceData(@RequestBody DeviceUploadData deviceUploadData) {

        return R.ok();
    }

    @ApiModel(value = "DeviceUploadData", description = "设备上报数据")
    class DeviceUploadData {
        //upPacketSN	上行报文序号
        //upDataSN	数据上报报文序号
        //topic	数据上报主题
        //timestamp	时间戳
        //tenantId	租户ID
        //serviceId	服务标识（MQTT、HTTP、网关）
        //protocol	协议类型
        //productId	产品ID
        //messageType	消息类型
        //deviceType	设备标识
        //deviceId	设备ID
        //assocAssetId	合作伙伴ID
        //IMSI	NB终端sim卡标识
        //IMEI	NB终端设备识别号
        //longitude	经度
        //latitude	 纬度
        //altitude	海拔
        //temperature	温度
        //pressure	气压
        //humidity	湿度
        //payload_C_980
        //payload_C_780
        //payload_C_532
        //payload_C_405
        //payload_CO2
        //payload_CH4
        //windSpeed	风速
        //windDirect	风向

        @ApiModelProperty(value = "上行报文序号")
        private String upPacketSN;
        @ApiModelProperty(value = "数据上报报文序号")
        private String upDataSN;
        @ApiModelProperty(value = "数据上报主题")
        private String topic;
        @ApiModelProperty(value = "时间戳")
        private String timestamp;
        @ApiModelProperty(value = "租户ID")
        private String tenantId;
        @ApiModelProperty(value = "服务标识（MQTT、HTTP、网关）")
        private String serviceId;
        @ApiModelProperty(value = "协议类型")
        private String protocol;
        @ApiModelProperty(value = "产品ID")
        private String productId;
        @ApiModelProperty(value = "消息类型")
        private String messageType;
        @ApiModelProperty(value = "设备标识")
        private String deviceType;
        @ApiModelProperty(value = "设备ID")
        private String deviceId;
        @ApiModelProperty(value = "合作伙伴ID")
        private String assocAssetId;
        @ApiModelProperty(value = "NB终端sim卡标识")
        private String IMSI;
        @ApiModelProperty(value = "NB终端设备识别号")
        private String IMEI;
        @ApiModelProperty(value = "经度")
        private String longitude;
        @ApiModelProperty(value = "纬度")
        private String latitude;
        @ApiModelProperty(value = "海拔")
        private String altitude;
        @ApiModelProperty(value = "气压")
        private String pressure;
        @ApiModelProperty(value = "湿度")
        private String humidity;
        @ApiModelProperty(value = "payload_C_980")
        private String payload_C_980;
        @ApiModelProperty(value = "payload_C_780")
        private String payload_C_780;
        @ApiModelProperty(value = "payload_C_532")
        private String payload_C_532;
        @ApiModelProperty(value = "payload_C_405")
        private String payload_C_405;
        @ApiModelProperty(value = "payload_CO2")
        private String payload_CO2;
        @ApiModelProperty(value = "payload_CH4")
        private String payload_CH4;
        @ApiModelProperty(value = "风速")
        private String windSpeed;
        @ApiModelProperty(value = "风向")
        private String windDirect;

        public DeviceUploadData(String upPacketSN, String upDataSN, String topic, String timestamp, String tenantId,
                                String serviceId, String protocol, String productId, String messageType,
                                String deviceType, String deviceId, String assocAssetId, String IMSI,
                                String IMEI, String longitude, String latitude, String altitude, String pressure,
                                String humidity, String payload_C_980, String payload_C_780, String payload_C_532,
                                String payload_C_405, String payload_CO2, String payload_CH4, String windSpeed,
                                String windDirect) {
            this.upPacketSN = upPacketSN;
            this.upDataSN = upDataSN;
            this.topic = topic;
            this.timestamp = timestamp;
            this.tenantId = tenantId;
            this.serviceId = serviceId;
            this.protocol = protocol;
            this.productId = productId;
            this.messageType = messageType;
            this.deviceType = deviceType;
            this.deviceId = deviceId;
            this.assocAssetId = assocAssetId;
            this.IMSI = IMSI;
            this.IMEI = IMEI;
            this.longitude = longitude;
            this.latitude = latitude;
            this.altitude = altitude;
            this.pressure = pressure;
            this.humidity = humidity;
            this.payload_C_980 = payload_C_980;
            this.payload_C_780 = payload_C_780;
            this.payload_C_532 = payload_C_532;
            this.payload_C_405 = payload_C_405;
            this.payload_CO2 = payload_CO2;
            this.payload_CH4 = payload_CH4;
            this.windSpeed = windSpeed;
            this.windDirect = windDirect;
        }

        public String getUpPacketSN() {
            return upPacketSN;
        }

        public void setUpPacketSN(String upPacketSN) {
            this.upPacketSN = upPacketSN;
        }

        public String getUpDataSN() {
            return upDataSN;
        }

        public void setUpDataSN(String upDataSN) {
            this.upDataSN = upDataSN;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getAssocAssetId() {
            return assocAssetId;
        }

        public void setAssocAssetId(String assocAssetId) {
            this.assocAssetId = assocAssetId;
        }

        public String getIMSI() {
            return IMSI;
        }

        public void setIMSI(String IMSI) {
            this.IMSI = IMSI;
        }

        public String getIMEI() {
            return IMEI;
        }

        public void setIMEI(String IMEI) {
            this.IMEI = IMEI;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getAltitude() {
            return altitude;
        }

        public void setAltitude(String altitude) {
            this.altitude = altitude;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPayload_C_980() {
            return payload_C_980;
        }

        public void setPayload_C_980(String payload_C_980) {
            this.payload_C_980 = payload_C_980;
        }

        public String getPayload_C_780() {
            return payload_C_780;
        }

        public void setPayload_C_780(String payload_C_780) {
            this.payload_C_780 = payload_C_780;
        }

        public String getPayload_C_532() {
            return payload_C_532;
        }

        public void setPayload_C_532(String payload_C_532) {
            this.payload_C_532 = payload_C_532;
        }

        public String getPayload_C_405() {
            return payload_C_405;
        }

        public void setPayload_C_405(String payload_C_405) {
            this.payload_C_405 = payload_C_405;
        }

        public String getPayload_CO2() {
            return payload_CO2;
        }

        public void setPayload_CO2(String payload_CO2) {
            this.payload_CO2 = payload_CO2;
        }

        public String getPayload_CH4() {
            return payload_CH4;
        }

        public void setPayload_CH4(String payload_CH4) {
            this.payload_CH4 = payload_CH4;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getWindDirect() {
            return windDirect;
        }

        public void setWindDirect(String windDirect) {
            this.windDirect = windDirect;
        }
    }
}


