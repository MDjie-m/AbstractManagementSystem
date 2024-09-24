package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.Member;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.OrderMemberDeduct;
import com.ruoyi.billiard.domain.OrderRecharge;
import com.ruoyi.billiard.domain.vo.MemberPwdReqVo;
import com.ruoyi.billiard.mapper.MemberMapper;
import com.ruoyi.billiard.service.IMemberService;
import com.ruoyi.billiard.service.IOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
import com.ruoyi.common.utils.AssertUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("cashier/member")
public class CashierMemberController extends BaseController {
    @Resource
    private IMemberService memberService;

    @Resource
    private IOrderService orderService;

    @PreAuthorize("@ss.hasRole('cashier')")
    @GetMapping("/{memberId}/recharge/list")
    public PageResVo<OrderRecharge> queryRechargeList(@PathVariable Long memberId) {
        startPage();
        List<OrderRecharge> res = orderService.selectRechargeOrderList(Order.builder().storeId(getStoreIdWithThrow())
                .memberId(memberId).build());
        return PageResVo.success(res);
    }

    @PreAuthorize("@ss.hasRole('cashier')")
    @GetMapping("/{memberId}/deduct/list")
    public PageResVo<Order> queryDeductList(@PathVariable Long memberId) {
        startPage();
        List<Order> res =
                orderService.selectDeductOrderList(Order.builder().storeId(getStoreIdWithThrow())
                        .memberId(memberId).build());
        return PageResVo.success(res);
    }

    @PreAuthorize("@ss.hasRole('cashier')")
    @PostMapping("/recharge")
    public ResultVo<Boolean> recharge(@RequestBody @Validated(OrderRecharge.IRecharge.class)
                                      OrderRecharge recharge) {
        startPage();
        recharge.setStoreId(getStoreIdWithThrow());
        orderService.memberRecharge(recharge);
        return ResultVo.success(true);
    }

    @PreAuthorize("@ss.hasRole('cashier')")
    @GetMapping("/{memberId}/recharge")
    public ResultVo<OrderRecharge> recharge(@PathVariable Long memberId,
                                            @RequestParam @Validated @DecimalMin(value = "1.00", message = "充值最小金额为1")
                                            BigDecimal recharge) {
        OrderRecharge res = orderService.getPreRecharge(getStoreIdWithThrow(),memberId,recharge);
        return ResultVo.success(res);
    }

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
    @GetMapping("/{memberId}")
    public ResultVo<Member> queryMemberById(@PathVariable Long memberId) {
        Member member = memberService.selectMemberByMemberId(memberId);
        if (Objects.isNull(member)) {
            return ResultVo.success(member);
        }
        AssertUtil.equal(getStoreIdWithThrow(), member.getStoreId(), "非法请求");
        return ResultVo.success(member);
    }


    @PreAuthorize("@ss.hasRole('cashier')")
    @PostMapping("/pwd")
    public ResultVo<Boolean> pwd(@RequestBody @Validated MemberPwdReqVo reqVo) {

        reqVo.setStoreId(getStoreId());
        return ResultVo.success(memberService.updatePayPwd(reqVo));
    }
}
