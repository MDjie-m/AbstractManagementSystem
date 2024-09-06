package com.renxin.pocket.controller.wechat;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.constant.PsyConstants;
import com.renxin.common.constant.RespMessageConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.enums.LimitType;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.OrderIdUtils;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.service.ICourOrderService;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.gauge.constant.GaugeConstant;
import com.renxin.gauge.domain.PsyGauge;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.gauge.service.IPsyGaugeService;
import com.renxin.gauge.service.IPsyOrderService;
import com.renxin.pocket.controller.wechat.constant.WechatMCHConstants;
import com.renxin.pocket.controller.wechat.constant.WechatUrlConstants;
import com.renxin.pocket.controller.wechat.dto.WechatPayDTO;
import com.renxin.pocket.controller.wechat.utils.WechatPayV3Utils;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.PsyConsultServeConfig;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.domain.PsyCoupon;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.dto.OrderDTO;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.wechat.service.WechatPayV3ApiService;
import com.renxin.wechat.vo.WechatPayVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付相关接口demo v3版本
 * 文档地址 https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_1.shtml
 */
@RestController
@RequestMapping("/pocket/wechatProgram")
public class WechatProgramPayController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    public WechatPayV3Utils wechatPayV3Utils;

    @Resource
    private PocketTokenService pocketTokenService;

    @Resource
    private IPsyUserService psyUserService;

    @Resource
    private IPsyConsultWorkService workService;

    @Resource
    private IPsyConsultOrderService psyConsultOrderService;

    @Resource
    private WechatPayV3ApiService wechatPayV3ApiService;

    @Resource
    private ICourCourseService courCourseService;

    @Resource
    private IPsyConsultServeService consultServeService;

    @Resource
    private IPsyGaugeService gaugeService;

    @Autowired
    private IPsyCouponService couponService;

    @Resource
    private ICourOrderService courOrderService;

    @Resource
    private IPsyOrderService gaugeOrderService;


    @Value("${wechat.appid}")
    private String WECHAT_MP_APPID;

    @Value("${wechat.secret}")
    private String secret;


    /**
     * 发起微信小程序支付
     * <p>
     * 用于换取openid 正式使用时openid可以直接从用户信息中获取 不需要在此接口中获取
     *
     * @return 小程序支付所需参数
     */
    @PostMapping("/wechatPay")
    @RateLimiter(limitType = LimitType.IP)
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult wechatPay(@RequestBody WechatPayDTO wechatPayDTO, HttpServletRequest request) {
        //@TODO demo中先写死的一些参数

        LoginDTO loginUser = pocketTokenService.getLoginUser(request);
        Long userId = loginUser.getUserId();//用户id
        wechatPayDTO.setUserId(userId);

        String out_trade_no = null;
        // BigDecimal amount = wechatPayDTO.getAmount(); //单位：元
        BigDecimal amount = calcAmount(wechatPayDTO);//计算优惠券后金额
        String content = "支付demo-课程金"; //先写死一个商品描述

        switch (wechatPayDTO.getModule()) {
            case CourConstant.MODULE_COURSE:
                out_trade_no = OrderIdUtils.createOrderNo(PsyConstants.ORDER_COURSE, userId); //创建商户订单号
                CourCourse courCourse = courCourseService.selectCourCourseById(wechatPayDTO.getCourseId());
                content = courCourse.getName() + "-" + courCourse.getAuthor();
                break;
            case GaugeConstant.MODULE_GAUGE:
                out_trade_no = OrderIdUtils.createOrderNo(PsyConstants.ORDER_GAUGE, userId); //创建商户订单号
                PsyGauge psyGauge = gaugeService.selectPsyGaugeById(wechatPayDTO.getGaugeId());
                content = psyGauge.getTitle();
                break;
            case ConsultConstant.MODULE_CONSULT:
                out_trade_no = StringUtils.isNoneBlank(wechatPayDTO.getOutTradeNo()) ? wechatPayDTO.getOutTradeNo() : OrderIdUtils.createOrderNo(PsyConstants.ORDER_CONSULT, userId); //创建商户订单号
                content = "预约心理咨询服务";

                // 支付单时需要校验服务库存
                if (wechatPayDTO.getWorkId() > 0) {
                    if (!workService.checkWork(wechatPayDTO.getWorkId(), wechatPayDTO.getConsultId(), wechatPayDTO.getTime())) {
                        return error("当前班次已经约满");
                    }
                }

                if (wechatPayDTO.getOrderId() != null) {
                    PsyConsultOrderVO one = psyConsultOrderService.getOne(wechatPayDTO.getOrderId());
                    if (!ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(one.getStatus())) {
                        return error("订单状态异常,无法支付");
                    }
                }

                if (psyConsultOrderService.checkNewByServe(wechatPayDTO.getOrderId(), wechatPayDTO.getServeId(), wechatPayDTO.getUserId())) {
                    return error("仅新用户可购买");
                }
                break;
        }

        // 将订单、支付单放入事务中
        String attach = "订单号: " + out_trade_no; //先写死一个附加数据 这是可选的 可以用来判断支付内容做支付成功后的处理
        WechatPayVO vo = BeanUtil.toBean(wechatPayDTO, WechatPayVO.class);
        vo.setOutTradeNo(out_trade_no);
        vo.setUserId(userId);
        wechatPayV3ApiService.wechatPay(vo);//创建订单
        //若应付金额为0, 无需发起支付, 直接执行回调
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            wechatPayV3ApiService.wechatPayNotify(out_trade_no, null);
            return AjaxResult.success("订单应付金额为0, 无需发起支付");
        }

        // 根据用户ID从用户表中查询openid
        PsyUser user = psyUserService.selectPsyUserById(userId);
        String openid = user.getWxOpenid();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);// 1天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        JSONObject params = new JSONObject();
        params.put("appid", WECHAT_MP_APPID); //小程序appid
        params.put("mchid", WechatMCHConstants.WECHAT_MCH_ID); //商户号
        params.put("description", content); //商品描述
        params.put("out_trade_no", out_trade_no); //商户订单号
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
        result.put("out_trade_no", out_trade_no); //商户订单号 此参数不是小程序拉起支付所需的参数 因此不参与签名
        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS, result);
    }

    //计算优惠后金额
    private BigDecimal calcAmount(WechatPayDTO req) {
        String module = req.getModule();
        String orderServerType = "";
        String orderServerId = "";
        if (module.equals(ConsultConstant.MODULE_CONSULT)) {
            orderServerType = "1" + PsyConstants.POCKET_ORDER_CONSULT_NUM;
            orderServerId = req.getOrderServerId();
        }
        if (module.equals(GaugeConstant.MODULE_GAUGE)) {
            orderServerType = "1" + PsyConstants.POCKET_ORDER_GAUGE_NUM;
            orderServerId = req.getGaugeId() + "";
        }
        if (module.equals(CourConstant.MODULE_COURSE)) {
            orderServerType = "1" + PsyConstants.POCKET_ORDER_COURSE_NUM;
            orderServerId = req.getCourseId() + "";
        }

        BigDecimal originalPrice = new BigDecimal(0); //原价格
        BigDecimal analysePrice = new BigDecimal(0); //测评解析服务价格

        //根据不同[服务类型和id]获取服务原价格
        switch (orderServerType) {
            // 11.倾诉
            // 12.咨询  
            case "1" + PsyConstants.POCKET_ORDER_CONSULT_NUM:
                PsyConsultServeConfig serverDetailConsult = consultServeService.getServerDetailByRelationId(orderServerId);
                originalPrice = serverDetailConsult.getPrice();
                break;
            // 13.测评
            case "1" + PsyConstants.POCKET_ORDER_GAUGE_NUM:
                PsyGauge psyGauge = gaugeService.selectPsyGaugeById(Long.valueOf(orderServerId));
                originalPrice = psyGauge.getPrice();
                if ("Y".equals(req.getIsUseGaugeAnalyse())) {//购买测评解析服务
                    analysePrice = psyGauge.getAnalysePrice();
                }
                break;
            // 14.来访者课程
            case "1" + PsyConstants.POCKET_ORDER_COURSE_NUM:
                CourCourse pocketCourse = courCourseService.selectCourCourseById(Long.valueOf(orderServerId));
                originalPrice = pocketCourse.getPrice();
                break;
            default:
                throw new ServiceException("没有相符的模块, 请检查module");
        }

        //若不使用优惠券
        if (ObjectUtils.isEmpty(req.getCouponNo())) {
            req.setOriginalPrice(originalPrice.add(analysePrice));
            req.setAmount(originalPrice.add(analysePrice));
            return originalPrice;
        }


        PsyCoupon coupon = couponService.selectPsyCouponByCouponNo(req.getCouponNo());
        if (coupon.getIsExpire() != 0 || coupon.getIsUsable() != 0) {
            throw new ServiceException("优惠券已过期或已使用");
        }

        //原价达到了优惠券门槛
        BigDecimal payAmount = BigDecimal.ZERO;
        if (originalPrice.compareTo(coupon.getUseThresholdPrice()) > 0) {
            coupon.setIsQualify(true);
            if (coupon.getCouponType() == 1) {//抵扣券
                payAmount = originalPrice.subtract(coupon.getMaxDeductionPrice());
                if (payAmount.compareTo(BigDecimal.ZERO) <= 0) {
                    payAmount = BigDecimal.ZERO;
                    //throw new ServiceException("优惠后无需付款",300);
                }
            } else if (coupon.getCouponType() == 2) {//折扣券
                payAmount = originalPrice.multiply(coupon.getDiscountRate());
            }
        } else {
            throw new ServiceException("未达到该优惠券的使用门槛");
        }

        BigDecimal totalAmount = payAmount.add(analysePrice);
        /*if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ServiceException("优惠后无需付款",300);
        }*/
        req.setOriginalPrice(originalPrice.add(analysePrice));
        req.setAmount(totalAmount);
        return totalAmount;
    }

    /**
     * 支付成功后查询订单状态
     *
     * @param out_trade_no 商户订单号
     * @return null代表查询失败 SUCCESS-成功 USERPAYING（用户支付中）和ACCEPT（已接收，等待扣款）为中间态 需要重新查询 其他为支付失败
     */
    @PostMapping("/checkPay")
    public Map<String, Object> checkPay(String out_trade_no) {
        //@TODO 先查询自己数据库中的订单状态是否支付成功 若成功 则直接返回SUCCESS 若未成功 则需调用查询支付接口
        String status = orderQueryByOutTradeNo(out_trade_no);
        return AjaxResult.success("请求成功", status);
    }

    /**
     * 申请微信退款
     * 交易时间超过一年的订单无法提交退款
     * 微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。
     * 申请退款总金额不能超过订单金额。
     * 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号
     * 每个支付订单的部分退款次数不能超过50次
     * 同一笔订单多次退款的请求需相隔1分钟
     *
     * @param transaction_id 微信支付订单号
     * @return
     */
    @PostMapping("/wechatRefund")
    public Map<String, Object> wechatRefund(String transaction_id) {
        //@TODO demo中先写死的一些参数
        Long userId = 1L; //先写死一个用户id
        BigDecimal amount = new BigDecimal("0.01"); //先写死一个退款金额 单位：元
        String reason = "支付demo-退还订金"; //先写死一个退款原因 这是可选的

        //@TODO 先查询订单是否可退款 将订单修改为退款中等业务处理

        String out_refund_no = OrderIdUtils.createOrderNo(PsyConstants.ORDER_REFUND, userId); //创建商户退款单号
        JSONObject params = new JSONObject();
        params.put("transaction_id", transaction_id); //微信支付订单号 也可以传out_trade_no 即发起支付时创建的商户订单号 二选一 transaction_id>out_trade_no
        params.put("out_refund_no", out_refund_no); //商户退款单号
        params.put("reason", reason); //退款原因 选填 若填写 会在退款消息中显示给用户
        params.put("notify_url", WechatUrlConstants.PAY_V3_REFUND_NOTIFY); //退款结果异步通知接口
        JSONObject amountJson = new JSONObject();
        amountJson.put("refund", Integer.parseInt(amount_fee(amount))); //退款金额 单位：分
        amountJson.put("total", Integer.parseInt(amount_fee(amount))); //原订单金额 单位：分
        amountJson.put("currency", "CNY"); //退款币种
        params.put("amount", amountJson); //订单金额信息
        JSONObject res = wechatPayV3Utils.sendPost(WechatUrlConstants.PAY_V3_REFUND, params); //发起请求
        if (res == null) {
            //@TODO 退款失败时回滚订单状态
            return error("退款申请失败");
        }
        logger.info("微信退款单号：" + res.getString("refund_id"));
        //@TODO 可以在此更新订单微信退款单号等信息
        return success();
    }

    /**
     * 微信支付异步通知
     *
     * @param request
     * @return
     */
    @PostMapping("/wechatPayNotify")
    public Map<String, String> wechatPayNotify(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>(2);
        JSONObject res = wechatPayV3Utils.getCallbackData(request);

        if (res == null) {
            result.put("code", "FAIL");
            result.put("message", "失败");
            return result;
        }
        logger.info("最终拿到的微信支付通知数据：" + res);
        //@TODO 处理支付成功后的业务 例如 将订单状态修改为已支付 具体参数键值可参考文档 注意！！！ 微信可能会多次发送重复的通知 因此要判断业务是否已经处理过了 避免重复处理

        wechatPayV3ApiService.wechatPayNotify(res.getString("out_trade_no"), res.getString("transaction_id"));
        result.put("code", "SUCCESS");
        result.put("message", "OK");
        return result;
    }

    /**
     * 微信退款异步通知
     *
     * @param request
     * @return
     */
    @PostMapping("/wechatRefundNotify")
    public Map<String, String> wechatRefundNotify(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>(2);
        JSONObject res = wechatPayV3Utils.getCallbackData(request);
        if (res == null) {
            result.put("code", "FAIL");
            result.put("message", "失败");
            return result;
        }
        logger.info("最终拿到的微信退款通知数据：" + res);
        //@TODO 处理退款成功后的业务 例如 将订单状态修改位已退款 具体参数键值可参考文档 注意！！！ 微信可能会多次发送重复的通知 因此要判断业务是否已经处理过了 避免重复处理
        result.put("code", "SUCCESS");
        result.put("message", "OK");
        return result;
    }

    /**
     * 通过商户订单号查询订单在微信侧支付状态
     *
     * @param out_trade_no 发起支付时创建的商户订单号
     * @return null代表查询失败 SUCCESS-成功 USERPAYING和ACCEPT为中间态 其他为支付失败
     */
    public String orderQueryByOutTradeNo(String out_trade_no) {
        JSONObject res = wechatPayV3Utils.sendGet(String.format(WechatUrlConstants.PAY_V3_QUERY_OUT, out_trade_no, WechatMCHConstants.WECHAT_MCH_ID));
        return res == null ? null : res.getString("trade_state");
    }

    /**
     * 查询单笔退款
     *
     * @param out_refund_no 申请退款时创建的商户退款单号
     * @return
     */
    public JSONObject refundQuery(String out_refund_no) {
        return wechatPayV3Utils.sendGet(String.format(WechatUrlConstants.PAY_V3_QUERY_REFUND, out_refund_no));
    }

    /**
     * 金额元转分字符串
     *
     * @param cny 元
     * @return
     */
    public String amount_fee(BigDecimal cny) {
        BigDecimal b2 = new BigDecimal(100);
        return cny.multiply(b2).setScale(0, RoundingMode.DOWN).toString();
    }

    /**
     * 取消来访端订单
     * <p>
     * 用于换取openid 正式使用时openid可以直接从用户信息中获取 不需要在此接口中获取
     *
     * @return 小程序支付所需参数
     */
    @PostMapping("/cancelOrder")
    @RateLimiter(limitType = LimitType.IP)
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult cancelOrder(@RequestBody WechatPayDTO req, HttpServletRequest request) {
        Long userId = pocketTokenService.getUserId(request);
        req.setUserId(userId);
        
        switch (req.getModule()) {
            case CourConstant.MODULE_COURSE:
                CourOrder courOrder = courOrderService.selectCourOrderByOrderId(req.getOutTradeNo());
                if (!userId.equals(courOrder.getUserId())){
                    throw new ServiceException("并非该订单的付款人, 无法取消订单");
                }
                if (!courOrder.getStatus().equals("0")){
                    throw new ServiceException("该订单已付款或已取消, 无法再次取消");
                }
                courOrder.setStatus(2);//已取消
                //courOrder.setUpdateBy(req.getPayConsultantId()+"");
                //courOrder.setUpdateTime(new Date());
                //取消订单
                courOrderService.updateCourOrder(courOrder);
                couponService.returnCoupon(courOrder.getCouponNo());//归还优惠券
                break;
                
            case GaugeConstant.MODULE_GAUGE:
                PsyOrder psyOrder = gaugeOrderService.getBaseMapper().selectOne(new LambdaQueryWrapper<PsyOrder>()
                        .eq(PsyOrder::getOrderId, req.getOutTradeNo()));
                if (!userId.equals(psyOrder.getUserId())){
                    throw new ServiceException("并非该订单的付款人, 无法取消订单");
                }
                if (!psyOrder.getOrderStatus().equals("1")){
                    throw new ServiceException("该订单已付款或已取消, 无法再次取消");
                }
                psyOrder.setOrderStatus(3);//已取消
                //courOrder.setUpdateBy(req.getPayConsultantId()+"");
                //courOrder.setUpdateTime(new Date());
                //取消订单
                gaugeOrderService.updatePsyOrder(psyOrder);
                couponService.returnCoupon(psyOrder.getCouponNo());//归还优惠券
                break;
                
       /*     case ConsultConstant.MODULE_CONSULT:
                OrderDTO consultOrder = psyConsultOrderService.getOrderDetailByNo(req.getOutTradeNo());
                if (!userId.equals(consultOrder.getUserId())){
                    throw new ServiceException("并非该订单的付款人, 无法取消订单");
                }
                if (!consultOrder.getStatus().equals("0")){
                    throw new ServiceException("该订单已付款或已取消, 无法再次取消");
                }
                consultOrder.setStatus("3");//已取消
                //courOrder.setUpdateBy(req.getPayConsultantId()+"");
                //courOrder.setUpdateTime(new Date());
                //取消订单
                psyConsultOrderService.update(psyOrder);
                couponService.returnCoupon(consultOrder.getCouponNo());//归还优惠券
                
                break;*/
            
        }
        return AjaxResult.success();
    }
}