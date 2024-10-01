package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.enums.EmployeeStatus;
import com.ruoyi.billiard.service.IGoodsService;
import com.ruoyi.billiard.service.IStoreTutorService;
import com.ruoyi.billiard.service.ITutorPunchInService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("cashier/tutor")
@PreAuthorize("@ss.hasRole('cashier')")
public class CashierTutorController extends BaseController {
    @Resource
    private IStoreTutorService storeTutorService;

    @Resource
    private ITutorPunchInService tutorPunchInService;

    @GetMapping("list")
    public ResultVo<List<StoreTutor>> list(StoreTutor tutor) {
        tutor.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(storeTutorService.selectStoreTutorList(tutor));
    }

    /***
     * 打卡
     * @param tutorId id
     * @param scheduleTime  班次时间
     * @param time time 打卡时间
     * @return bool
     */
    @GetMapping("/{tutorId}/punch-in")
    public ResultVo<Boolean> punchIn(@PathVariable Long tutorId,@RequestParam LocalDateTime scheduleTime, @RequestParam LocalDateTime time) {

        return ResultVo.success(tutorPunchInService.punchIn(getStoreIdWithThrow(),tutorId,scheduleTime,time));
    }
}
