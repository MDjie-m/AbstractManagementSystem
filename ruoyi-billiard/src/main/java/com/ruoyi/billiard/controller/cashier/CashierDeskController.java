package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.domain.vo.CashierDeskDashboardResVo;
import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        storeDesk.setStoreId(getStoreId());
        storeDesk.setEnable(Boolean.TRUE);
        List<StoreDesk> list = storeDeskService.selectStoreDeskList(storeDesk);
        return ResultVo.success(list);
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/dashboard")
    public ResultVo<CashierDeskDashboardResVo> deskDashboard() {

        CashierDeskDashboardResVo res = storeDeskService.getDeskDashboard(getStoreId());
        ;
        return ResultVo.success(res);
    }


}
