package com.renxin.app;

import cn.hutool.core.bean.BeanUtil;
import com.renxin.RuoYiApplication;
import com.renxin.pocket.controller.wechat.dto.WechatPayDTO;
import com.renxin.common.constant.PsyConstants;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.service.ICourCourseService;
import com.renxin.gauge.constant.GaugeConstant;
import com.renxin.gauge.domain.PsyGauge;
import com.renxin.gauge.service.IPsyGaugeService;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.service.IPsyConsultWorkService;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.psychology.vo.PsyConsultWorkVO;
import com.renxin.wechat.service.WechatPayV3ApiService;
import com.renxin.wechat.vo.WechatPayVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuoYiApplication.class)
@Slf4j
public class WxPayTest {

    @Resource
    private IPsyUserService psyUserService;

    @Resource
    private IPsyConsultWorkService workService;

    @Resource
    private WechatPayV3ApiService wechatPayV3ApiService;

    @Resource
    private ICourCourseService iCourCourseService;

    @Resource
    private IPsyGaugeService iPsyGaugeService;

    @Test
    public void payTest() {
        WechatPayDTO dto = new WechatPayDTO();
        dto.setUserId(84L);
        dto.setServeId(1673151921080299521L);
        dto.setWorkId(0L);
        dto.setAmount(new BigDecimal("106.00"));
        dto.setModule("consult");
        Long userId = dto.getUserId(); //用户id
        String out_trade_no = null;

        BigDecimal amount = dto.getAmount(); //单位：元
        String content = "支付demo-课程金"; //先写死一个商品描述

        switch (dto.getModule()) {
            case CourConstant.MODULE_COURSE:
                out_trade_no = createOrderNo(PsyConstants.ORDER_COURSE, userId); //创建商户订单号
                CourCourse courCourse = iCourCourseService.selectCourCourseById(dto.getCourseId());
                content = courCourse.getName() + "-" + courCourse.getAuthor();

                break;
            case GaugeConstant.MODULE_GAUGE:
                out_trade_no = createOrderNo(PsyConstants.ORDER_GAUGE, userId); //创建商户订单号
                PsyGauge psyGauge = iPsyGaugeService.selectPsyGaugeById(dto.getGaugeId());
                content = psyGauge.getTitle();
                break;
            case ConsultConstant.MODULE_CONSULT:
                out_trade_no = createOrderNo(PsyConstants.ORDER_CONSULT, userId); //创建商户订单号
                content = "预约心理咨询服务";

                if (dto.getWorkId() > 0) {
                    PsyConsultWorkVO work = workService.getOne(dto.getWorkId());
                    if ("1".equals(work.getStatus())) {
                        System.out.println("排班状态异常");
                    }
//                    if (work.getNum() == 0) {
//                        System.out.println("当前班次已经约满");
//                    }
                }
                break;
        }

        // 将订单、支付单放入事务中
        String attach = "订单号: " + out_trade_no; //先写死一个附加数据 这是可选的 可以用来判断支付内容做支付成功后的处理
        WechatPayVO vo = BeanUtil.toBean(dto, WechatPayVO.class);
        vo.setOutTradeNo(out_trade_no);
        wechatPayV3ApiService.wechatPay(vo);
        System.out.println(attach);
    }

    @Test
    public void notifyTest() {
        String trade = "ZX202309181145045974";
        String order = "1222";
        wechatPayV3ApiService.wechatPayNotify(trade, order);
    }

    @Test
    public void testOrderNo() {
        System.out.println(createOrderNo(PsyConstants.ORDER_CONSULT, 84L));
    }

    private String  createOrderNo(String head, Long id) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        int length = uid.length();
//        for (int i = 0; i < 8 - length; i++) {
//            uid.insert(0, "0");
//        }
        // KC20230810101928
        // KC202308101020489990
        // KC202308101019596022
//        return head + sdf.format(date);
        return head + sdf.format(date) + (int) ((Math.random() * 9 + 1) * 1000);
    }

}
