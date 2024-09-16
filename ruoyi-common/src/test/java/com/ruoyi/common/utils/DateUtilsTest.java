package com.ruoyi.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

@Slf4j
public class DateUtilsTest {

     @Test
    public  void testRemoveSeconds(){
         Date date=new  Date();
         log.info("date----{},{}",date,DateUtils.removeSeconds(date));
         log.info("date----{},{}",date,DateUtils.fileSecondsAddOneMinutes(date));
     }
}