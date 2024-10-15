package com.renxin.pocket.controller.coupon;


import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.psychology.domain.PsyCoupon;
import com.renxin.psychology.request.ReceiveFreeCouponReq;
import com.renxin.psychology.service.IPsyCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户-优惠券发行Controller
 * 
 * @author renxin
 * @date 2024-08-02
 */
@RestController
@RequestMapping("/pocket/coupon")
public class PocketCouponController extends BaseController
{
    @Autowired
    private IPsyCouponService psyCouponService;
    
    @Resource
    private ConsultantTokenService consultantTokenService;

    @Autowired
    private PocketTokenService pocketTokenService;
    
    /**
     * 查询用户-优惠券发行列表
     */
    //@PreAuthorize("@ss.hasPermi('system:coupon:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyCoupon psyCoupon, HttpServletRequest request) {
        Long userId = pocketTokenService.getUserId(request);
        psyCoupon.setUserId(userId);
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
        Long userId = pocketTokenService.getUserId(request);
        req.setUserId(userId);
        req.setIsCanGetChargeCoupon("N");
        psyCouponService.receiveFreeCoupon(req);
        return AjaxResult.success("领取成功，可以在我的卡券查看");
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
