package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.domain.StoreUser;
import com.ruoyi.billiard.enums.EmployeeStatus;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.billiard.service.IStoreTutorService;
import com.ruoyi.billiard.service.IStoreUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cashier/store")
public class CashierStoreController  extends BaseController {

    @Autowired
    private IStoreService storeService;
    @Autowired
    private IStoreUserService storeUserService   ;
    @Autowired
    private IStoreTutorService  storeTutorService;
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/info")
    public ResultVo<Store> getStoreInfo(@RequestParam(required = false) boolean needEmployees) {

        Store res = storeService.selectStoreByStoreId(getStoreIdWithThrow());
        if(!Boolean.TRUE.equals(needEmployees)){
            return ResultVo.success(res);
        }
        res.setUserList(storeUserService.selectStoreUserList(StoreUser.builder().storeId(res.getStoreId())
                .status(EmployeeStatus.WORK.getValue()).build()));
        res.setTutorList(storeTutorService.selectStoreTutorList(StoreTutor.builder()
                .storeId(res.getStoreId()).status(EmployeeStatus.WORK.getValue()).build()));
        return ResultVo.success(res);
    }
}
