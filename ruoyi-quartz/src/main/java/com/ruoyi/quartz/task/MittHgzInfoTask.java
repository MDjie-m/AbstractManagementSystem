package com.ruoyi.quartz.task;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ve.domain.MittHgzInfo;
import com.ruoyi.ve.domain.THgzBuMittHgzinfo;
import com.ruoyi.ve.service.ITHgzBuMittHgzinfoService;
import info.vidc.www.certificate.operation.CertificateInfo;
import info.vidc.www.certificate.operation.CertificateRequestVIPProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("mittHgzInfoTask")
public class MittHgzInfoTask
{
    @Autowired
    private ITHgzBuMittHgzinfoService mittHgzInfoService;

    public void queryMittHgzInfo() throws RemoteException {
        THgzBuMittHgzinfo md = new THgzBuMittHgzinfo();
        md.setFlag("0");
        List<THgzBuMittHgzinfo> mdList = mittHgzInfoService.selectTHgzBuMittHgzinfoList(md);
        for (THgzBuMittHgzinfo mittHgzInfo : mdList) {
            if (StringUtils.isNotEmpty(mittHgzInfo.getHgzbh())) {
                System.out.println("执行有参方法：" + new Date() + "=" + mittHgzInfo.getHgzbh());
//                CertificateInfo[]   certificateInfo = this.queryCertificateSingle("HX272513U001", "DFCVhgz88!", mittHgzInfo.getHgzbh(), "");

                CertificateRequestVIPProxy proxy = new CertificateRequestVIPProxy();
                CertificateInfo certificateInfo[] = proxy.queryCertificateSingle("HX272513U001", "DFCVhgz88!", mittHgzInfo.getHgzbh(), "");
                if (certificateInfo!=null && certificateInfo.length > 0) {
                    CertificateInfo certInfo = certificateInfo[0];
                    String hgzinfo = JSON.toJSONString(certInfo);
                    THgzBuMittHgzinfo md1 = new THgzBuMittHgzinfo();
                    md1.setId(mittHgzInfo.getId());
                    md1.setFlag("1");
                    md1.setHgzinfo(hgzinfo);
                    System.out.println(hgzinfo);
                    mittHgzInfoService.updateTHgzBuMittHgzinfo(md1);
                }else {
                    THgzBuMittHgzinfo md1 = new THgzBuMittHgzinfo();
                    md1.setId(mittHgzInfo.getId());
                    md1.setFlag("2");
                    mittHgzInfoService.updateTHgzBuMittHgzinfo(md1);
                }

            }
        }
        if(mdList.size()==0){
            System.out.println("无带查询数据：" + new Date());
        }
    }
//
//    public List queryCertificateSingle(String name,String pwd,String hgzbh,String clsbdh)
//    {
//        System.out.println("执行无参方法：" + new Date());
//        // 接口地址
////        String address = "https://hgz.miit.gov.cn/enterprise/services/CertificateRequestVIPService?wsdl";
//        String address = "http://www.vidc.info/certificate/operation/";
//        // 代理工厂
//        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//        // 设置代理地址
//        jaxWsProxyFactoryBean.setAddress(address);
//        // 设置接口类型
//        jaxWsProxyFactoryBean.setServiceClass(CertificateRequestVIP.class);
//        // 创建一个代理接口实现
//        CertificateRequestVIP us = (CertificateRequestVIP) jaxWsProxyFactoryBean.create();
//        // 数据准备
//        // 调用代理接口的方法调用并返回结果
//        java.util.List<mitt.CertificateInfo> _queryCertificateSingle__return = us.queryCertificateSingle(name, pwd, hgzbh, clsbdh);
//        System.out.println("queryCertificateSingle.result=" + _queryCertificateSingle__return);
//        return _queryCertificateSingle__return;
//    }

//    public CertificateInfo[] queryCertificateSingle(String name, String pwd, String hgzbh, String clsbdh) {
//        try {
//            // web服务地址，不是wsdl地址
//            String url = "https://hgz.miit.gov.cn/enterprise/services/CertificateRequestVIPService";
//            //创建客户端实体类
//            CertificateRequestVIPStub stub = new CertificateRequestVIPStub(url);
//            //创建调用方法的实体，设置参数
//            QueryCertificateSingle queryCertificateSingle = QueryCertificateSingle.Factory.newInstance();
//            queryCertificateSingle.setUsername("HX272513U001");
//            queryCertificateSingle.setPassword("DFCVhgz88!");
//            queryCertificateSingle.setWzhgzbh("YF00X0030051813");
//            queryCertificateSingle.setClsbdh("");
//
//            QueryCertificateSingleDocument document
//                    = QueryCertificateSingleDocument.Factory.newInstance();
//            document.setQueryCertificateSingle(queryCertificateSingle);
//
//            //客户端调用方法，并返回xml信息
//            QueryCertificateSingleResponseDocument responseDocument =  stub.queryCertificateSingle(document);
//            QueryCertificateSingleResponse documentResult =  responseDocument.getQueryCertificateSingleResponse();
//
//            CertificateInfo[] cinfo = documentResult.getQueryCertificateSingleResultArray();
//
//            return cinfo;
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//        return new CertificateInfo[0];
//    }

}
