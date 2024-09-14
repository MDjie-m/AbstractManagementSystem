package com.ruoyi.billiard.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.billiard.domain.MemberLevel;
import com.ruoyi.billiard.service.IMemberLevelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门店会员等级Controller
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@RestController
@RequestMapping("/billiard/memberLevel")
public class MemberLevelController extends BaseController
{
    @Autowired
    private IMemberLevelService memberLevelService;

    /**
     * 查询门店会员等级列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:memberLevel:list')")
    @GetMapping("/list")
    public PageResVo<MemberLevel> list(MemberLevel memberLevel)
    {
        startPage();
        List<MemberLevel> list = memberLevelService.selectMemberLevelList(memberLevel);
        return PageResVo.success(list);
    }

    /**
     * 导出门店会员等级列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:memberLevel:export')")
    @Log(title = "门店会员等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberLevel memberLevel)
    {
        List<MemberLevel> list = memberLevelService.selectMemberLevelList(memberLevel);
        ExcelUtil<MemberLevel> util = new ExcelUtil<MemberLevel>(MemberLevel.class);
        util.exportExcel(response, list, "门店会员等级数据");
    }

    /**
     * 获取门店会员等级详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:memberLevel:query')")
    @GetMapping(value = "/{memberLevelId}")
    public ResultVo<MemberLevel> getInfo(@PathVariable("memberLevelId") Long memberLevelId)
    {
        return ResultVo.success(memberLevelService.selectMemberLevelByMemberLevelId(memberLevelId));
    }

    /**
     * 新增门店会员等级
     */
    @PreAuthorize("@ss.hasPermi('billiard:memberLevel:add')")
    @Log(title = "门店会员等级", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody MemberLevel memberLevel)
    {
        return ResultVo.success(memberLevelService.insertMemberLevel(memberLevel));
    }

    /**
     * 修改门店会员等级
     */
    @PreAuthorize("@ss.hasPermi('billiard:memberLevel:edit')")
    @Log(title = "门店会员等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody MemberLevel memberLevel)
    {
        return ResultVo.success(memberLevelService.updateMemberLevel(memberLevel));
    }

    /**
     * 删除门店会员等级
     */
    @PreAuthorize("@ss.hasPermi('billiard:memberLevel:remove')")
    @Log(title = "门店会员等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{memberLevelIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] memberLevelIds)
    {
        return  ResultVo.success(memberLevelService.deleteMemberLevelByMemberLevelIds(memberLevelIds));
    }


    /**
     * 查询会员等级是否被使用
     */
    @GetMapping("/isUsed/{memberLevelId}")
    public ResultVo<Boolean> isUsed(@PathVariable Long memberLevelId) {
        return ResultVo.success(memberLevelService.isUsed(memberLevelId));
    }
}
