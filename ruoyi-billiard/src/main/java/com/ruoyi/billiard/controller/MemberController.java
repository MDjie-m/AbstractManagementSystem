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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.billiard.domain.Member;
import com.ruoyi.billiard.service.IMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 门店会员Controller
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@RestController
@RequestMapping("/billiard/member")
public class MemberController extends BaseController
{
    @Autowired
    private IMemberService memberService;

    /**
     * 查询门店会员列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:member:list')")
    @GetMapping("/list")
    public PageResVo<Member> list(Member member)
    {
        startPage();
        List<Member> list = memberService.selectMemberList(member);
        return PageResVo.success(list);
    }

    /**
     * 导出门店会员列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:member:export')")
    @Log(title = "门店会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Member member)
    {
        List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        util.exportExcel(response, list, "门店会员数据");
    }

    /**
     * 获取门店会员详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:member:query')")
    @GetMapping(value = "/{memberId}")
    public ResultVo<Member> getInfo(@PathVariable("memberId") Long memberId)
    {
        return ResultVo.success(memberService.selectMemberByMemberId(memberId));
    }

    /**
     * 新增门店会员
     */
    @PreAuthorize("@ss.hasPermi('billiard:member:add')")
    @Log(title = "门店会员", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody Member member)
    {
        return ResultVo.success(memberService.insertMember(member));
    }

    /**
     * 修改门店会员
     */
    @PreAuthorize("@ss.hasPermi('billiard:member:edit')")
    @Log(title = "门店会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody Member member)
    {
        return ResultVo.success(memberService.updateMember(member));
    }

    /**
     * 删除门店会员
     */
    @PreAuthorize("@ss.hasPermi('billiard:member:remove')")
    @Log(title = "门店会员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{memberIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] memberIds)
    {
        return  ResultVo.success(memberService.deleteMemberByMemberIds(memberIds));
    }
}
