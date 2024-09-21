package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.enums.EmployeeStatus;
import com.ruoyi.billiard.service.IGoodsService;
import com.ruoyi.billiard.service.IStoreTutorService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("cashier/tutor")
@PreAuthorize("@ss.hasRole('cashier')")
public class CashierTutorController extends BaseController {
    @Resource
    private IStoreTutorService storeTutorService;

    @GetMapping("list")
    public ResultVo<List<StoreTutor>> list(StoreTutor tutor) {
        tutor.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(storeTutorService.selectStoreTutorList(tutor));
    }
}
