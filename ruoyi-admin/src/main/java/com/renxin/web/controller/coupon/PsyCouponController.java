package com.renxin.web.controller.coupon;


import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyCoupon;
import com.renxin.psychology.service.IPsyCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户-优惠券发行Controller
 * 
 * @author renxin
 * @date 2024-08-02
 */
@RestController
@RequestMapping("/system/coupon")
public class PsyCouponController extends BaseController
{
    @Autowired
    private IPsyCouponService psyCouponService;

    /**
     * 查询用户-优惠券发行列表
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyCoupon psyCoupon)
    {
        startPage();
        List<PsyCoupon> list = psyCouponService.selectPsyCouponList(psyCoupon);
        return getDataTable(list);
    }

    /**
     * 导出用户-优惠券发行列表
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyCoupon psyCoupon)
    {
        List<PsyCoupon> list = psyCouponService.selectPsyCouponList(psyCoupon);
        ExcelUtil<PsyCoupon> util = new ExcelUtil<PsyCoupon>(PsyCoupon.class);
        util.exportExcel(response, list, "用户-优惠券发行数据");
    }

    /**
     * 获取用户-优惠券发行详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:query')")
    @GetMapping(value = "/{couponNo}")
    public AjaxResult getInfo(@PathVariable("couponNo") String couponNo)
    {
        return AjaxResult.success(psyCouponService.selectPsyCouponByCouponNo(couponNo));
    }

    /**
     * 新增用户-优惠券发行
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PsyCoupon psyCoupon)
    {
        return toAjax(psyCouponService.insertPsyCoupon(psyCoupon));
    }

//    /**
//     * 发放优惠券
//     */
//    @PreAuthorize("@ss.hasPermi('system:coupon:add')")
//    @PostMapping
//    public AjaxResult grant(@RequestBody PsyCoupon psyCoupon)
//    {
//        return toAjax(psyCouponService.insertPsyCoupon(psyCoupon));
//    }

    /**
     * 修改用户-优惠券发行
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PsyCoupon psyCoupon)
    {
        return toAjax(psyCouponService.updatePsyCoupon(psyCoupon));
    }

    /**
     * 删除用户-优惠券发行
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:remove')")
	@DeleteMapping("/{couponNos}")
    public AjaxResult remove(@PathVariable Long[] couponNos)
    {
        return toAjax(psyCouponService.deletePsyCouponByCouponNos(couponNos));
    }
}
