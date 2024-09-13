package com.renxin.common.core.domain.dto;
import lombok.Data;



@Data
public class ConsultLoginDTO  {

   
   String access_token;
   
   String openid;

   //设备id
   private String deviceId;

   //设备品牌
   private String deviceBrand;

   //设备型号
   private String deviceModel;

   //最后登录ip
   private String lastLoginIp;
   
   private String phone;
   private String smsCode;


}