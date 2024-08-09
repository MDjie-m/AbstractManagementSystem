package com.renxin.consultant.controller;


import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyCoupon;
import com.renxin.psychology.request.ReceiveFreeCouponReq;
import com.renxin.psychology.service.IPsyCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户-优惠券发行Controller
 * 
 * @author renxin
 * @date 2024-08-02
 */
@RestController
@RequestMapping("/consultant/coupon")
public class ConsultantCouponController extends BaseController
{
    @Autowired
    private IPsyCouponService psyCouponService;
    
    @Resource
    private ConsultantTokenService consultantTokenService;
    
    /**
     * 查询用户-优惠券发行列表
     */
    //@PreAuthorize("@ss.hasPermi('system:coupon:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyCoupon psyCoupon, HttpServletRequest request) {
        Long consultId = consultantTokenService.getConsultId(request);
        psyCoupon.setConsultantId(consultId);
        startPage();
        List<PsyCoupon> list = psyCouponService.selectPsyCouponList(psyCoupon);
        return getDataTable(list);
    }

    /**
     * 领取免费优惠券
     */
    //@PreAuthorize("@ss.hasPermi('marketing:couponTemplate:list')")
    @PostMapping("/receiveFreeCoupon")
    public AjaxResult receiveFreeCoupon(@RequestBody ReceiveFreeCouponReq req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setConsultId(consultId);
        psyCouponService.receiveFreeCoupon(req);
        return AjaxResult.success();
    }

//    /**
//     * 导出用户-优惠券发行列表
//     */
//    //@PreAuthorize("@ss.hasPermi('system:coupon:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, PsyCoupon psyCoupon)
//    {
//        List<PsyCoupon> list = psyCouponService.selectPsyCouponList(psyCoupon);
//        ExcelUtil<PsyCoupon> util = new ExcelUtil<PsyCoupon>(PsyCoupon.class);
//        util.exportExcel(response, list, "用户-优惠券发行数据");
//    }
//
//    /**
//     * 获取用户-优惠券发行详细信息
//     */
//    //@PreAuthorize("@ss.hasPermi('system:coupon:query')")
//    @GetMapping(value = "/{couponNo}")
//    public AjaxResult getInfo(@PathVariable("couponNo") String couponNo)
//    {
//        return AjaxResult.success(psyCouponService.selectPsyCouponByCouponNo(couponNo));
//    }
//
//    /**
//     * 新增用户-优惠券发行
//     */
//    //@PreAuthorize("@ss.hasPermi('system:coupon:add')")
//    @PostMapping
//    public AjaxResult add(@RequestBody PsyCoupon psyCoupon)
//    {
//        return toAjax(psyCouponService.insertPsyCoupon(psyCoupon));
//    }
//
//    /**
//     * 修改用户-优惠券发行
//     */
//    //@PreAuthorize("@ss.hasPermi('system:coupon:edit')")
//    @PutMapping
//    public AjaxResult edit(@RequestBody PsyCoupon psyCoupon)
//    {
//        return toAjax(psyCouponService.updatePsyCoupon(psyCoupon));
//    }
//
//    /**
//     * 删除用户-优惠券发行
//     */
//    //@PreAuthorize("@ss.hasPermi('system:coupon:remove')")
//	@DeleteMapping("/{couponNos}")
//    public AjaxResult remove(@PathVariable Long[] couponNos)
//    {
//        return toAjax(psyCouponService.deletePsyCouponByCouponNos(couponNos));
//    }
}
