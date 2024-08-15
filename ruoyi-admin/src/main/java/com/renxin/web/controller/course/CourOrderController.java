package com.renxin.web.controller.course;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.renxin.course.domain.dto.OrderQueryDTO;
import com.renxin.course.vo.OrderQueryVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.BusinessType;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.service.ICourOrderService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 课程订单Controller
 * 
 * @author renxin
 * @date 2023-03-17
 */
@RestController
@RequestMapping("/course/order")
public class CourOrderController extends BaseController
{
    @Autowired
    private ICourOrderService courOrderService;

    /**
     * 查询课程订单列表
     */
    @PreAuthorize("@ss.hasPermi('course:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourOrder courOrder)
    {
        startPage();
        List<CourOrder> list = courOrderService.selectCourOrderList(courOrder);
        return getDataTable(list);
    }

    /**
     * 根据条件查询课程订单列表
     */
    @PreAuthorize("@ss.hasPermi('course:order:list')")
    @GetMapping("/query")
    public TableDataInfo queryOrderList(OrderQueryDTO orderQueryDTO) {
        startPage();
        return getDataTable(courOrderService.queryOrderList(orderQueryDTO));
    }

    /**
     * 导出课程订单列表
     */
    @PreAuthorize("@ss.hasPermi('course:order:export')")
    @Log(title = "课程订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderQueryDTO orderQueryDTO)
    {
        List<OrderQueryVO> list = courOrderService.queryOrderList(orderQueryDTO);

        list = list.stream().filter(item ->
                !(orderQueryDTO.getLowAmount() != null
                        && item.getAmount().compareTo(orderQueryDTO.getLowAmount()) < 0
                        || orderQueryDTO.getHighAmount() != null
                        && item.getAmount().compareTo(orderQueryDTO.getHighAmount()) > 0)).collect(Collectors.toList());
        ExcelUtil<OrderQueryVO> util = new ExcelUtil<OrderQueryVO>(OrderQueryVO.class);
        util.exportExcel(response, list, "课程订单数据");
    }

    /**
     * 获取课程订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('course:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(courOrderService.selectCourOrderById(id));
    }

    /**
     * 新增课程订单
     */
    @PreAuthorize("@ss.hasPermi('course:order:add')")
    @Log(title = "课程订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CourOrder courOrder)
    {
        try {
            Long res = courOrderService.insertCourOrder(courOrder);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "新增课程订单失败");
        }
    }

    /**
     * 修改课程订单
     */
    @PreAuthorize("@ss.hasPermi('course:order:edit')")
    @Log(title = "课程订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CourOrder courOrder)
    {
        try {
            int res = courOrderService.updateCourOrder(courOrder);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "修改课程订单失败");
        }
    }

    /**
     * 删除课程订单
     */
    @PreAuthorize("@ss.hasPermi('course:order:remove')")
    @Log(title = "课程订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        try {
            int res = courOrderService.deleteCourOrderByIds(ids);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "删除课程订单失败");
        }
    }
}
