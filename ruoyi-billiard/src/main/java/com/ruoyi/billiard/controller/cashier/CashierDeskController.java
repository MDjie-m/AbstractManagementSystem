package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.domain.vo.CashierDeskDashboardResVo;
import com.ruoyi.billiard.domain.vo.LineUpVo;
import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cashier/desk")
public class CashierDeskController extends BaseController {
    @Autowired
    private IStoreDeskService storeDeskService;

    /**
     * 查询球桌列表
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/list")
    public ResultVo<List<StoreDesk>> list(@Validated StoreDesk storeDesk) {

        storeDesk.setStoreId(getStoreIdWithThrow());
        storeDesk.setEnable(Boolean.TRUE);
        List<StoreDesk> list = storeDeskService.selectStoreDeskList(storeDesk);
        return ResultVo.success(list);
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/dashboard")
    public ResultVo<CashierDeskDashboardResVo> deskDashboard() {

        CashierDeskDashboardResVo res = storeDeskService.getDeskDashboard(getStoreIdWithThrow());
        return ResultVo.success(res);
    }

    /*
    获取排队信息
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/line-up")
    public ResultVo<Map<Integer,LineUpVo>> lineUp() {
        Map<Integer,LineUpVo>res = storeDeskService.getLineUpInfo(getStoreIdWithThrow());
        return ResultVo.success(res);
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/line-up")
    public ResultVo<Boolean> lineUp( @RequestBody  Map<Integer,LineUpVo> reqVo) {

        return ResultVo.success(storeDeskService.saveLineUpInfo(getStoreIdWithThrow(), reqVo));
    }
}
