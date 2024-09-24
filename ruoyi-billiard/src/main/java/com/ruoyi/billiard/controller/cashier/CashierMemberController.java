package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.Member;
import com.ruoyi.billiard.domain.vo.MemberPwdReqVo;
import com.ruoyi.billiard.mapper.MemberMapper;
import com.ruoyi.billiard.service.IMemberService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("cashier/member")
public class CashierMemberController extends BaseController {
    @Resource
    private IMemberService memberService;

    @PreAuthorize("@ss.hasRole('cashier')")
    @GetMapping("/list")
    public PageResVo<Member> queryMember(Member member) {
        startPage();
        member.setStoreId(getStoreId());
        member.setStatus(0);
        List<Member> res = memberService.selectMemberList(member);
        return PageResVo.success(res);
    }
    @PreAuthorize("@ss.hasRole('cashier')")
    @PostMapping("/pwd")
    public ResultVo<Boolean> pwd(@RequestBody @Validated MemberPwdReqVo reqVo) {

        reqVo.setStoreId(getStoreId());
        return ResultVo.success(memberService.updatePayPwd(reqVo));
    }
}
