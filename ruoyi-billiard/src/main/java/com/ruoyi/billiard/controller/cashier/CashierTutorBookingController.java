package com.ruoyi.billiard.controller.cashier;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.billiard.domain.TutorBooking;
import com.ruoyi.billiard.domain.TutorBooking;
import com.ruoyi.billiard.domain.vo.IAdd;
import com.ruoyi.billiard.domain.vo.IQuery;
import com.ruoyi.billiard.service.ITutorBookingService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cashier/tutor/booking")
@PreAuthorize("@ss.hasRole('cashier')")
public class CashierTutorBookingController extends BaseController {

    @Autowired
    private ITutorBookingService tutorBookingService;

    @GetMapping("/map")
    public ResultVo<Map<String, List<TutorBooking>>> bookingMap(@Validated(IQuery.class) TutorBooking reqVo) {

        reqVo.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(tutorBookingService.selectBookingDayMap(reqVo));
    }

    @GetMapping("/list")
    public PageResVo<TutorBooking> bookingList(TutorBooking reqVo) {
        startPage();
        reqVo.setStoreId(getStoreIdWithThrow());
        return PageResVo.success(tutorBookingService.selectTutorBookingList(reqVo));
    }

    @PostMapping("")
    public ResultVo<TutorBooking> addBooking(@Validated(IAdd.class) @RequestBody TutorBooking reqVo) {

        reqVo.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(tutorBookingService.insertTutorBooking(reqVo));
    }

    @DeleteMapping("/{bookingId}")
    public ResultVo<Boolean> remove(@PathVariable Long bookingId) {

        return ResultVo.success(tutorBookingService.getBaseMapper().delete(Wrappers.<TutorBooking>lambdaQuery()
                .eq(TutorBooking::getTutorBookingId, bookingId).eq(TutorBooking::getStoreId, getStoreIdWithThrow())) > 0);
    }
}
