package com.ruoyi.reportSys.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.windSys.domain.BladePart;
import com.ruoyi.windSys.domain.WindTurbineInfo;
import com.ruoyi.windSys.service.IBladePartService;
import com.ruoyi.windSys.service.IWindTurbineInfoService;
import freemarker.core.ParseException;
import freemarker.log.Logger;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
import org.springframework.web.bind.annotation.*;



/**
 * 
 * @author GG
 * @date 2024-07-15
 */
@RestController
@RequestMapping("/reportSys/reportmanager")
public class ReportManagerController extends BaseController {

    private Logger log = Logger.getLogger(ReportManagerController.class.toString());
    private Configuration config ;

    public ReportManagerController() {
        config = new Configuration(Configuration.VERSION_2_3_0);
        config.setDefaultEncoding("utf-8");
    }

    @Autowired
    private IWindTurbineInfoService windTurbineInfoService;

    @Autowired
    private IBladePartService bladePartService;

    /**
     * 查询风机管理列表
     */
    @PreAuthorize("@ss.hasPermi('reportSys:historyreport:query')")
    @GetMapping("/queryBlade/{wid}")
    public void queryBlade(@PathVariable Long[] wid) {



        for (int j = 0; j < wid.length; j++) {
            WindTurbineInfo wind = windTurbineInfoService.selectWindTurbineInfoByWId(wid[j]);
            List<BladePart> list1 = bladePartService.selectBladePartByBcId(Long.parseLong(wind.getBlade1Code()));

            ReportManagerController controller = new ReportManagerController();
            Map<String, Object> dataMap = new HashMap<String, Object>();
            String realTurbineCodePhoto = wind.getTurbineCodePhoto().replace("profile", "D:\\ruoyi\\uploadPath");
            dataMap.put("tb_code_img", controller.getImageStr(realTurbineCodePhoto));
            dataMap.put("YPXH", wind.getBladeModel());
            dataMap.put("JCDW", wind.getEntryStaff());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = wind.getInspectionDate();
            String normativeDate = dateFormat.format(date);

            dataMap.put("XJSJ", normativeDate);
            dataMap.put("bl1_code_img", controller.getImageStr(wind.getBlade1PhotoUrl().replace("profile", "D:\\ruoyi\\uploadPath")));
            dataMap.put("bl2_code_img", controller.getImageStr(wind.getBlade2PhotoUrl().replace("profile", "D:\\ruoyi\\uploadPath")));
            dataMap.put("bl3_code_img", controller.getImageStr(wind.getBlade3PhotoUrl().replace("profile", "D:\\ruoyi\\uploadPath")));
            dataMap.put("L1", wind.getBlade1Code());
            dataMap.put("L2", wind.getBlade2Code());
            dataMap.put("L3", wind.getBlade3Code());
            for (int i = 0; i < list1.size(); i++) {
                dataMap.put("blPartImg1_" + (i + 1), controller.getImageStr(list1.get(i).getBladePartPhotoUrl().replace("profile", "D:\\ruoyi\\uploadPath")));
                dataMap.put("msg1_" + (i + 1), list1.get(i).getBladePartInspectionResult());
            }


            List<BladePart> list2 = bladePartService.selectBladePartByBcId(Long.parseLong(wind.getBlade2Code()));

            for (int i = 0; i < list2.size(); i++) {
                dataMap.put("blPartImg2_" + (i + 1), controller.getImageStr(list2.get(i).getBladePartPhotoUrl().replace("profile", "D:\\ruoyi\\uploadPath")));
                dataMap.put("msg2_" + (i + 1), list2.get(i).getBladePartInspectionResult());
            }


            List<BladePart> list3 = bladePartService.selectBladePartByBcId(Long.parseLong(wind.getBlade3Code()));

            for (int i = 0; i < list3.size(); i++) {
                dataMap.put("blPartImg3_" + (i + 1), controller.getImageStr(list3.get(i).getBladePartPhotoUrl().replace("profile", "D:\\ruoyi\\uploadPath")));
                dataMap.put("msg3_" + (i + 1), list3.get(i).getBladePartInspectionResult());
            }
            controller.createWord(dataMap, "A-001.ftl", "E:/风机信息test.docx");
        }
    }



    public void createWord(Map<String, Object> dataMap, String templateName, String saveFilePath) {
        //加载模板(路径)数据
        config.setClassForTemplateLoading(this.getClass(), "/ftl");
        //设置异常处理器 这样的话 即使没有属性也不会出错 如：${list.name}...不会报错
        config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        Template template = null;
        if(templateName.endsWith(".ftl")) {
            templateName = templateName.substring(0, templateName.indexOf(".ftl"));
        }
        try {
            template = config.getTemplate(templateName + ".ftl");
        } catch (TemplateNotFoundException e) {
            log.error("模板文件未找到", e);
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            log.error("模板类型不正确", e);
            e.printStackTrace();
        } catch (ParseException e) {
            log.error("解析模板出错，请检查模板格式", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("IO读取失败", e);
            e.printStackTrace();
        }
        File outFile = new File(saveFilePath);
        if(!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }
        Writer out = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outFile);
        } catch (FileNotFoundException e) {
            log.error("输出文件时未找到文件", e);
            e.printStackTrace();
        }
        out = new BufferedWriter(new OutputStreamWriter(fos));
        //将模板中的预先的代码替换为数据
        try {
            template.process(dataMap, out);
        } catch (TemplateException e) {
            log.error("填充模板时异常", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("IO读取时异常", e);
            e.printStackTrace();
        }
        log.info("由模板文件：" + templateName + ".ftl" + " 生成文件 ：" + saveFilePath + " 成功！！");
        try {
            out.close();//web项目不可关闭
        } catch (IOException e) {
            log.error("关闭Write对象出错", e);
            e.printStackTrace();
        }
    }
    public String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
        } catch (FileNotFoundException e) {
            log.error("加载图片未找到", e);
            e.printStackTrace();
        }
        try {
            data = new byte[in.available()];
            //注：FileInputStream.available()方法可以从输入流中阻断由下一个方法调用这个输入流中读取的剩余字节数
            in.read(data);
            in.close();
        } catch (IOException e) {
            log.error("IO操作图片错误", e);
            e.printStackTrace();
        }
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);

    }
public class ReportManagerController extends BaseController
{

}
