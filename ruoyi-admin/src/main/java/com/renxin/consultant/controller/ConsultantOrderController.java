package com.renxin.consultant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renxin.common.aliPay.AlipayPayUtil;
import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.constant.PsyConstants;
import com.renxin.common.constant.RespMessageConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.LimitType;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.OrderIdUtils;
import com.renxin.common.wechat.wxPay.WechatPaymentService;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.service.ICourCourseService;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.gauge.constant.GaugeConstant;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.pocket.controller.wechat.constant.WechatMCHConstants;
import com.renxin.pocket.controller.wechat.constant.WechatUrlConstants;
import com.renxin.pocket.controller.wechat.dto.WechatPayDTO;
import com.renxin.pocket.controller.wechat.utils.WechatPayV3Utils;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.*;
import com.renxin.psychology.dto.OrderDTO;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.wechat.service.WechatPayV3ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 心理咨询师Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/consultant/order")
@Slf4j
@Api(value = "ConsultantCourseController" ,tags = {"咨询师订单Api"})
public class ConsultantOrderController extends BaseController
{
    @Resource
    private IPsyConsultantOrderService psyConsultantOrderService;

    @Resource
    private IPsyConsultWorkService workService;

    @Resource
    private WechatPayV3ApiService wechatPayV3ApiService;

    @Resource
    private IPsyConsultantTeamSupervisionService teamService;

    @Resource
    private ConsultantTokenService consultantTokenService;
    
    @Resource
    private ICourCourseService courCourseService;
    
    @Resource
    private IPsyConsultantPackageService consultantPackageService;
    
    @Resource
    private IPsyConsultantScheduleService consultantScheduleService;
    
    @Resource
    private IPsyConsultServeService consultServeService;

    @Resource
    private IPsyConsultOrderService psyConsultOrderService;
    
    @Resource
    private IPsyConsultService consultService;

    @Resource
    public WechatPayV3Utils wechatPayV3Utils;
    
    @Resource
    private WechatPaymentService wechatPaymentService;

    @Value("${wechat.appid}")
    private String WECHAT_MP_APPID;

    @Value("${wechat.secret}")
    private String secret;


   /* @ApiOperation(value = "查询订单信息")
    @GetMapping(value = "/getOrderInfo/{id}")
    @RateLimiter
    public AjaxResult getOrderInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultantOrderService.getOne(id));
    }

    @ApiOperation(value = "查询订单详情")
    @GetMapping(value = "/getOrderDetail/{id}")
    @RateLimiter
    public AjaxResult getOrderDetail(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultantOrderService.getOrderDetail(id));
    }*/

    @ApiOperation(value = "订单ID查询详情")
    @PostMapping(value = "/getOrderDetailByNo")
    @RateLimiter
    public AjaxResult getOrderDetailByNo(@RequestBody PsyConsultantOrder req)
    {
        return AjaxResult.success(psyConsultantOrderService.selectPsyConsultantOrderByOrderNo(req.getOrderNo()));
    }

    @ApiOperation(value = "获取订单列表")
    @PostMapping(value = "/getOrderList")
    @RateLimiter
    public AjaxResult getOrderList(@RequestBody PsyConsultantOrder req,HttpServletRequest request) {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setPayConsultantId(consultId);
        req.setIsConsultantReq(true);
        startPage();
        List<PsyConsultantOrder> orderList = psyConsultantOrderService.selectPsyConsultantOrderList(req);
        return AjaxResult.success(orderList);
    }

   /* @ApiOperation(value = "咨询")
    @PostMapping(value = "/doConsult/{id}/{workId}/{time}")
    @RateLimiter
    public AjaxResult doConsult(@PathVariable("id") Long id, @PathVariable("workId") Long workId, @PathVariable("time") Integer time)
    {
        return AjaxResult.success(psyConsultantOrderService.doConsult(id, workId, time));
    }

    @ApiOperation(value = "取消")
    @GetMapping(value = "/cancel/{id}")
    @RateLimiter
    public AjaxResult cancel(@PathVariable("id") Long id)
    {
        PsyConsultOrder order = psyConsultantOrderService.getOrderById(id);
        psyConsultantOrderService.cancel(order, order.getNickName());
        return AjaxResult.success();
    }*/

    /**
     * 创建咨询师订单
     *
     * 用于换取openid 正式使用时openid可以直接从用户信息中获取 不需要在此接口中获取
     * @return 小程序支付所需参数
     */
    @PostMapping("/create")
    @RateLimiter(limitType = LimitType.IP)
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult create(@RequestBody PsyConsultantOrder consultantOrder, HttpServletRequest request) {
        Long consultId = consultantTokenService.getConsultId(request);
        String payConsultId = consultId+""; //付费咨询师id
        if (consultId == -1) {
            return error("用户信息异常,请登录后重试");
        }

        consultantOrder.setPayConsultantId(consultId);
        String out_trade_no = "";

        //BigDecimal amount = consultantOrder.getPayAmount(); //单位：元
        String serverName = ""; //服务描述
        BigDecimal originalPrice = new BigDecimal(0); //原价
        BigDecimal consultantRatio = new BigDecimal(0); //咨询师分成比例
        BigDecimal consultantPrice = new BigDecimal(0); //咨询师收取费用

        //根据不同类型创建订单
        switch (consultantOrder.getServerType()) {
            //团队督导
            case PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM:
                out_trade_no = OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_TEAM_SUP, null);
                PsyConsultantTeamSupervision team = teamService.selectPsyConsultantTeamSupervisionById(Long.valueOf(consultantOrder.getServerId()));
                if (ObjectUtils.isEmpty(team)){
                    throw new ServiceException("未找到该id的团队督导");
                }
                if (team.getConsultantId().equals(consultId)){
                    throw new ServiceException("不可报名自己开班的团队督导");
                }
                originalPrice = team.getPrice();
                consultantPrice = team.getLectureAmount();
                
                serverName = team.getTitle() + "-第" + team.getPeriodNo() +"期";
                //TODO 超卖问题.
                if (team.getSurplusJoinNum() <= 0){
                    return error("该团队已满额");
                }
                break;

            case PsyConstants.CONSULTANT_ORDER_PERSON_SUP_NUM:
                out_trade_no = OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_PERSON_SUP, null);
                //PsyConsultantTeamSupervision consultantExp = teamService.selectPsyConsultantTeamSupervisionById(Long.valueOf(consultantOrder.getServerId()));
                PsyConsultServeConfig serverDetail = consultServeService.getServerDetailByRelationId(consultantOrder.getServerId());
                if (ObjectUtils.isEmpty(serverDetail)){
                    throw new ServiceException("未找到该id的个督服务, 请传入relationId");
                }
                if (serverDetail.getConsultantId().equals(consultId)){
                    throw new ServiceException("不可购买自己提供的服务");
                }
                originalPrice = serverDetail.getPrice();
                consultantRatio = serverDetail.getConsultantRatio();
                
                serverName = "个案督导服务购买-" + serverDetail.getName() + "-" + serverDetail.getConsultantName();

                // 支付单时需要校验服务库存
                /*if (consultantOrder.getWorkId() > 0) {
                    if (!workService.checkWork(consultantOrder.getWorkId(),null, consultantOrder.getTime())) {
                        return error("当前班次已经约满");
                    }
                }*/
                break;

            case PsyConstants.CONSULTANT_ORDER_PERSON_EXP_NUM:
                out_trade_no = OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_PERSON_EXP, null);
                //PsyConsultantTeamSupervision consultantExp = teamService.selectPsyConsultantTeamSupervisionById(Long.valueOf(consultantOrder.getServerId()));
                PsyConsultServeConfig serverDetailExp = consultServeService.getServerDetailByRelationId(consultantOrder.getServerId());
                if (ObjectUtils.isEmpty(serverDetailExp)){
                    throw new ServiceException("未找到该id的个人体验服务, 请传入relationId");
                }
                if (serverDetailExp.getConsultantId().equals(consultId)){
                    throw new ServiceException("不可购买自己提供的服务");
                }
                originalPrice = serverDetailExp.getPrice();
                consultantRatio = serverDetailExp.getConsultantRatio();
                serverName = "个人体验服务购买-" + serverDetailExp.getName() + "-" + serverDetailExp.getConsultantName();

                // 支付单时需要校验服务库存
                /*if (consultantOrder.getWorkId() > 0) {
                    if (!workService.checkWork(consultantOrder.getWorkId(),null, consultantOrder.getTime())) {
                        return error("当前班次已经约满");
                    }
                }*/
                break;
                
            case PsyConstants.CONSULTANT_ORDER_COURSE_NUM:
                out_trade_no = OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_COURSE, null);
                CourCourse courCourse = courCourseService.selectCourCourseById(Long.valueOf(consultantOrder.getServerId()));
                if (ObjectUtils.isEmpty(courCourse)){
                    throw new ServiceException("未找到该id的课程");
                }
                originalPrice = courCourse.getPrice();
                serverName = "购买课程-"+courCourse.getName();
                break;
                
            case PsyConstants.CONSULTANT_ORDER_PACKAGE_NUM:
                out_trade_no = OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_PACKAGE, null);
                PsyConsultantPackage psyConsultantPackage = consultantPackageService.selectPsyConsultantPackageByPackageId(Long.parseLong(consultantOrder.getServerId()));
                if (ObjectUtils.isEmpty(psyConsultantPackage)){
                    throw new ServiceException("未找到该id的套餐");
                }
                originalPrice = psyConsultantPackage.getPrice();
                serverName = "购买套餐权益-"+psyConsultantPackage.getProductName();
                
                //校验优惠券发行量是否足够
                consultantPackageService.checkConsultantPackageOrder(psyConsultantPackage);
                break;
                
            default:
                throw new ServiceException("没有相应的服务类型, 请检查ServerType为1~5之间的整数");
        }
        
        //创建订单
        String attach = "订单号: " + out_trade_no; //先写死一个附加数据 这是可选的 可以用来判断支付内容做支付成功后的处理
            consultantOrder.setOrderNo(out_trade_no);
            consultantOrder.setServerName(serverName);
            consultantOrder.setOriginalPrice(originalPrice);
            consultantOrder.setConsultantRatio(consultantRatio);
            consultantOrder.setConsultantPrice(consultantPrice);
        PsyConsultantOrder newOrder = psyConsultantOrderService.createConsultantOrder(consultantOrder);
        PsyConsultantOrder result = new PsyConsultantOrder();
            result.setPayAmount(newOrder.getPayAmount());
            result.setOrderNo(newOrder.getOrderNo());
        //应付金额为0, 直接返回
        if (newOrder.getPayAmount().compareTo(BigDecimal.ZERO) == 0){
            return AjaxResult.success(result);
        }

        //初次发起支付
        ////支付宝
        if ("aliPay".equals(consultantOrder.getPaymentChannel())){
            AjaxResult aliResult = AlipayPayUtil.alipayAppPay(out_trade_no, newOrder.getPayAmount(), newOrder.getServerName());
            if ((Integer) aliResult.get("code") == 200) {
                //获取支付参数后, 将其保存
                String msg = (String) aliResult.get("msg");
                    newOrder.setAliPayParam(msg);
                psyConsultantOrderService.updatePsyConsultantOrder(newOrder);
                
                result.setAliPayParam(newOrder.getAliPayParam());
                return AjaxResult.success(result);
            } else {
                log.error("发起支付宝支付失败, result : " + aliResult);
                throw new ServiceException("发起支付宝支付失败");
            }
        }
        
        ////微信
        if ("wechatPay".equals(consultantOrder.getPaymentChannel())){
            Map<String, Object> wxResult = wechatPaymentService.weChatDoUnifiedOrder(out_trade_no, newOrder.getPayAmount(), newOrder.getServerName());
            if ((Integer) wxResult.get("code") == 200) {
                Map<String,String> dataMap = (Map)wxResult.get("data");
                String wxPayParam = JSONObject.toJSONString(dataMap);
                
                result.setWxPayParam(wxPayParam);
                return AjaxResult.success(result);
            }else {
                log.error("发起微信支付失败, result : " + wxResult);
                throw new ServiceException("发起微信支付失败");
            }
        }
        
        return AjaxResult.error("该支付方式不存在.");
    }

 
    
    /**
     * 金额元转分字符串
     * @param cny 元
     * @return
     */
    private String amount_fee(BigDecimal cny) {
        BigDecimal b2 = new BigDecimal(100);
        return cny.multiply(b2).setScale(0, RoundingMode.DOWN).toString();
    }

    /**
     * 支付宝 - 支付完成回调
     *
     * @param request
     * @return
     */
    @PostMapping("/aliPaySuccess/callback")
    public Map<String, String> aliPayCallback(@RequestBody Map<String,Object> map, HttpServletRequest request) {
        log.info("支付宝, 支付完成回调");
        log.info(map.toString());
        log.info(request.toString());
        
        return null;
    }

    @PostMapping("/wxPaySuccess/callback")
    public Map<String, String> wxPayCallback(@RequestBody Map<String,Object> map, HttpServletRequest request) {
        log.info("微信, 支付完成回调");
        log.info(map.toString());
        log.info(request.toString());

        return null;
    }
    
    /**
     * 支付完成回调
     *
     * @param request
     * @return
     */
    @PostMapping("/paySuccess/callback")
    public Map<String, String> payCallback(@RequestBody PsyConsultantOrder consultantOrder, HttpServletRequest request) {
        Map<String, String> result = new HashMap<>(2);
        /*JSONObject res = wechatPayV3Utils.getCallbackData(request);

        if (res == null) {
            result.put("code", "FAIL");
            result.put("message", "失败");
            return result;
        }
        logger.info("最终拿到的微信支付通知数据：" + res);*/
        //@TODO 处理支付成功后的业务 例如 将订单状态修改为已支付 具体参数键值可参考文档 注意！！！ 微信可能会多次发送重复的通知 因此要判断业务是否已经处理过了 避免重复处理

        //psyConsultantOrderService.wechatConsultantPayNotify(res.getString("out_trade_no"), res.getString("transaction_id"));
        psyConsultantOrderService.paySuccessCallback(consultantOrder.getOrderNo(), null);
        result.put("code", "SUCCESS");
        result.put("message", "OK");
        return result;
    }

    /**
     * 批量预约服务 (个督/体验)
     * @return
     */
    @PostMapping("/reservationServerBatch")
    @RateLimiter(limitType = LimitType.IP)
    public AjaxResult reservation(@RequestBody List<PsyConsultantSchedule> consultantScheduleList, HttpServletRequest request) {
        Long consultId = consultantTokenService.getConsultId(request);
        String payConsultId = consultId + ""; //付费咨询师id
        consultantScheduleList.forEach(c -> {
            c.setCreateBy(payConsultId);
            c.setUpdateBy(payConsultId);
        });
        consultantScheduleService.reservationServerBatch(consultantScheduleList);
        return AjaxResult.success();
    }
    
    /**
     * 咨询
     */
    @PostMapping(value = "/doConsult/{id}/{workId}/{time}")
    @RateLimiter
    public AjaxResult doConsult(@PathVariable("id") Long id, @PathVariable("workId") Long workId, @PathVariable("time") Integer time)
    {
        return AjaxResult.success(psyConsultOrderService.doConsult(id, workId, time));
    }

    /**
     * 取消咨询师端订单
     *
     * 用于换取openid 正式使用时openid可以直接从用户信息中获取 不需要在此接口中获取
     * @return 小程序支付所需参数
     */
    @PostMapping("/cancelOrder")
    @RateLimiter(limitType = LimitType.IP)
    public AjaxResult cancelOrder(@RequestBody PsyConsultantOrder req, HttpServletRequest request) {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setPayConsultantId(consultId);
        if (consultId == -1) {
            return error("用户信息异常,请登录后重试");
        }
        
        psyConsultantOrderService.cancelOrder(req);
        return AjaxResult.success();
    }


    /**
     * 支付咨询师端指定订单
     */
    @PostMapping("/payOrderWx")
    @RateLimiter(limitType = LimitType.IP)
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult payOrderWx(@RequestBody WechatPayDTO req, HttpServletRequest request) {
        Long consultId = consultantTokenService.getConsultId(request);

        //查询订单应付金额
        BigDecimal amount = BigDecimal.valueOf(9999);
        PsyConsultantOrder consultantOrder = psyConsultantOrderService.selectPsyConsultantOrderByOrderNo(req.getOutTradeNo());

        // 根据用户ID从用户表中查询openid
        PsyConsultVO consultant = consultService.getOne(consultId);
        String openid = consultant.getOpenId();

        String content = "咨询师端订单demo"; //先写死一个商品描述 // 将订单、支付单放入事务中
        String attach = "订单号: " + req.getOutTradeNo(); //先写死一个附加数据 这是可选的 可以用来判断支付内容做支付成功后的处理
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);// 1天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        JSONObject params = new JSONObject();
        params.put("appid", WECHAT_MP_APPID); //小程序appid
        params.put("mchid", WechatMCHConstants.WECHAT_MCH_ID); //商户号
        params.put("description", content); //商品描述
        params.put("out_trade_no", req.getOutTradeNo()); //商户订单号
        params.put("time_expire", sdf.format(calendar.getTime())); //交易结束时间 选填 时间到了之后将不能再支付 遵循rfc3339标准格式
        params.put("attach", attach); //附加数据 选填 在查询API和支付通知中原样返回 可作为自定义参数使用
        params.put("notify_url", WechatUrlConstants.PAY_V3_NOTIFY); //支付结果异步通知接口
        JSONObject amount_json = new JSONObject();
        amount_json.put("total", Integer.parseInt(amount_fee(amount))); //支付金额 单位：分
        params.put("amount", amount_json); //订单金额信息
        JSONObject payer = new JSONObject();
        payer.put("openid", openid); //用户在小程序侧的openid
        params.put("payer", payer); //支付者信息
        JSONObject res = wechatPayV3Utils.sendPost(WechatUrlConstants.PAY_V3_JSAPI, params); //发起请求
        if (res == null || StringUtils.isEmpty(res.getString("prepay_id"))) {
            //@TODO 支付发起失败可以将订单数据回滚
            return error("支付发起失败");
        }
        StringBuilder sb = new StringBuilder();
        //返回给小程序拉起微信支付的参数
        Map<String, String> result = new HashMap<>();
        result.put("appId", WECHAT_MP_APPID); //小程序appid
        sb.append(result.get("appId")).append("\n");
        result.put("timeStamp", (new Date().getTime() / 1000) + ""); //时间戳
        sb.append(result.get("timeStamp")).append("\n");
        result.put("nonceStr", RandomStringUtils.randomAlphanumeric(32)); //32位随机字符串
        sb.append(result.get("nonceStr")).append("\n");
        result.put("package", "prepay_id=" + res.getString("prepay_id")); //预支付id 格式为 prepay_id=xxx
        sb.append(result.get("package")).append("\n");
        result.put("paySign", wechatPayV3Utils.signRSA(sb.toString())); //签名
        result.put("signType", "RSA"); //加密方式 固定RSA
        result.put("out_trade_no", req.getOutTradeNo()); //商户订单号 此参数不是小程序拉起支付所需的参数 因此不参与签名

        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS, result);
    }



    /**
     * 支付咨询师端指定订单
     */
    @PostMapping("/payOrder")
    @RateLimiter(limitType = LimitType.IP)
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult payOrder(@RequestBody WechatPayDTO req, HttpServletRequest request) {
        Long consultId = consultantTokenService.getConsultId(request);
        //查询订单
        PsyConsultantOrder order = psyConsultantOrderService.selectPsyConsultantOrderByOrderNo(req.getOutTradeNo());
        PsyConsultantOrder result = new PsyConsultantOrder();

        ////若已发起过该渠道的支付, 则直接返回相关参数
        if ("aliPay".equals(req.getPaymentChannel())){
            result.setOrderNo(order.getOrderNo());
            result.setPayAmount(order.getPayAmount());
            result.setAliPayParam(order.getAliPayParam());
        }
        
        return AjaxResult.success(result);
     
    }

    //String 转 map
    private Map<String, String> parseQueryString(String queryString) {
        Map<String, String> queryPairs = new HashMap<>();
        String[] pairs = queryString.split("&");
        try {
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
                queryPairs.put(key, value);
            }
            return queryPairs;
        } catch (Exception e) {
            log.error("发起支付返回结果解析异常, queryString: " + queryString);
            throw new ServiceException("发起支付返回结果解析异常");
        }
    }
}
