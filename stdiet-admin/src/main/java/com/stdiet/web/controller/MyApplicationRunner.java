package com.stdiet.web.controller;

import com.stdiet.common.utils.AliyunVideoUtils;
import com.stdiet.common.utils.StringUtils;
import com.stdiet.common.utils.poi.ExcelUtil;
import com.stdiet.custom.domain.SysCustomerHealthy;
import com.stdiet.custom.domain.SysCustomerHealthyExtended;
import com.stdiet.custom.domain.SysNutritionQuestion;
import com.stdiet.custom.domain.SysNutritionalVideo;
import com.stdiet.custom.mapper.SysCustomerHealthyExtendedMapper;
import com.stdiet.custom.mapper.SysCustomerHealthyMapper;
import com.stdiet.custom.mapper.SysWxUserInfoMapper;
import com.stdiet.custom.mapper.SysWxUserLogMapper;
import com.stdiet.custom.service.ISysNutritionQuestionService;
import com.stdiet.custom.service.ISysNutritionalVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@Component
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner {

//    @Autowired
//    private SysWxUserLogMapper sysWxUserLogMapper;

//    @Autowired
//    private SysWxUserInfoMapper sysWxUserInfoMapper;

    @Autowired
    private ISysNutritionQuestionService sysNutritionQuestionService;

    @Autowired
    private ISysNutritionalVideoService sysNutritionalVideoService;

    @Autowired
    private SysCustomerHealthyMapper sysCustomerHealthyMapper;

    @Autowired
    private SysCustomerHealthyExtendedMapper sysCustomerHealthyExtendedMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("--------------项目启动调用方法开始----------");

        System.out.println("--------------项目启动调用方法结束-------------");
    }

    /**
     * 给所有的健康信息加上扩展信息
     *
     */
    public void addExtendedHealthy(){
        List<SysCustomerHealthy> sysCustomerHealthies = sysCustomerHealthyMapper.selectSysCustomerHealthyList(new SysCustomerHealthy());
        System.out.println("总条数："+sysCustomerHealthies.size());
        int success = 0;
        if(sysCustomerHealthies != null && sysCustomerHealthies.size() > 0){
            for (SysCustomerHealthy sysCustomerHealthy : sysCustomerHealthies) {
                SysCustomerHealthyExtended extended = new SysCustomerHealthyExtended();
                extended.setHealthyId(sysCustomerHealthy.getId());
                if(sysCustomerHealthyExtendedMapper.insertSysCustomerHealthyExtended(extended)  > 0){
                    success++;
                }
            }
        }
        System.out.println("成功条数："+success);
    }

    /**
     * 给现有视频更新截图图片
     */
    public void getVideoSn(){
        List<SysNutritionalVideo> list = sysNutritionalVideoService.selectSysNutritionalVideoList(new SysNutritionalVideo(), false);
        int success = 0;
        if(list != null && list.size() > 0){
            for (SysNutritionalVideo video : list) {
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(AliyunVideoUtils.submitVideoSnapshot(video.getVideoId())){
                    success ++;
                }
            }
        }
        System.out.println("成功数量：" + success);
    }


    /**
     * 从微信用户表中查询openid更新到用户打卡日志表中
     */
    public void dealWxUserLog() {
        /*List<String> phoneList = sysWxUserLogMapper.getAllSysWxUserLogPhone();
        SysWxUserLog sysWxUserLog = new SysWxUserLog();
        if(phoneList.size() > 0){
            for (String phone : phoneList) {
                System.out.println(phone);
                SysWxUserInfo sysWxUserInfo = new SysWxUserInfo();
                sysWxUserInfo.setPhone(phone);
                List<SysWxUserInfo> list = sysWxUserInfoMapper.selectSysWxUserInfoList(sysWxUserInfo);
                if(list != null && list.size() > 0){
                    sysWxUserLog.setOpenid(list.get(0).getOpenid());
                    sysWxUserLog.setPhone(phone);
                    sysWxUserLogMapper.updateSysWxUserLog(sysWxUserLog);
                }
            }
        }*/
    }

    /**
     * 导入营养小知识方法
     *
     * @param path
     */
    public void importNutritionQuestion(String path) {
        try {
            int count = 0;
            ExcelUtil<SysNutritionQuestion> util = new ExcelUtil<SysNutritionQuestion>(SysNutritionQuestion.class);
            File file = new File(path);
            List<SysNutritionQuestion> questionList = util.importExcel(new FileInputStream(file));
            System.out.println(questionList.size());
            for (SysNutritionQuestion sysNutritionQuestion : questionList) {
                //System.out.println(sysNutritionQuestion);
                if (StringUtils.isNotEmpty(sysNutritionQuestion.getTitle())
                        && StringUtils.isNotEmpty(sysNutritionQuestion.getContent())) {
                    //System.out.println(sysNutritionQuestion.getTitle() + "\n");
                    sysNutritionQuestion.setShowFlag(1);
                    if (sysNutritionQuestionService.insertSysNutritionQuestion(sysNutritionQuestion) > 0) {
                        count++;
                        Thread.sleep(100);
                        System.out.println(count);
                    }

                }
            }
            System.out.println("结束：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateVideo() {
        Map<String, Object> map = sysNutritionalVideoService.searchVideo(null, null, 1, 100, null);
        if (map != null) {
            List<SysNutritionalVideo> list = (List<SysNutritionalVideo>) map.get("nutritionalVideoList");
            if (list != null && list.size() > 0) {
                for (SysNutritionalVideo video : list) {
                    sysNutritionalVideoService.insertSysNutritionalVideo(video);
                }
            }
        }
    }


}
