package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.vo.StoreDashboardResVo;
import com.ruoyi.billiard.enums.EmployeeStatus;
import com.ruoyi.billiard.service.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.domain.model.Tuple3;
import com.ruoyi.common.core.page.PageResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("cashier/store")
public class CashierStoreController extends BaseController {

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IStoreSwapRecordService storeSwapRecordService;

    @Autowired
    private IStoreScheduleService storeScheduleService;
    @Autowired
    private IStoreUserService storeUserService;
    @Autowired
    private IStoreTutorService storeTutorService;

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/info")
    public ResultVo<Store> getStoreInfo(@RequestParam(required = false) boolean needEmployees) {

        Store res = storeService.selectStoreByStoreId(getStoreIdWithThrow());
        if (!Boolean.TRUE.equals(needEmployees)) {
            return ResultVo.success(res);
        }
        res.setUserList(storeUserService.selectStoreUserList(StoreUser.builder().storeId(res.getStoreId())
                .status(EmployeeStatus.WORK.getValue()).build()));
        res.setTutorList(storeTutorService.selectStoreTutorList(StoreTutor.builder()
                .storeId(res.getStoreId()).status(EmployeeStatus.WORK.getValue()).build()));
        return ResultVo.success(res);
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/dashboard")
    public ResultVo<StoreDashboardResVo> dashboard(@RequestParam Date startTime, @RequestParam Date endTime) {


        return ResultVo.success(storeService.queryStoreDashboard(getStoreIdWithThrow(), startTime, endTime));
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/swap/list")
    public PageResVo<StoreSwapRecord> dashboard(StoreSwapRecord reqVo) {
        reqVo.setStoreId(getStoreIdWithThrow());
        startPage();
        return PageResVo.success(storeSwapRecordService.selectStoreSwapRecordList(reqVo));
    }

    @GetMapping("/swap")
    public ResultVo<Integer> storeSwap(StoreSwapRecord reqVo) {

        return ResultVo.success(storeSwapRecordService.insertStoreSwapRecord(reqVo));
    }

    @GetMapping("/schedule")
    public ResultVo<Tuple3<Date, Date, Date>> getSchedule(@RequestParam(required = false) Date date) {
        return ResultVo.success(storeScheduleService.getDaySchedule(getStoreIdWithThrow(), Optional.ofNullable(date).orElse(new Date())));
    }
}
