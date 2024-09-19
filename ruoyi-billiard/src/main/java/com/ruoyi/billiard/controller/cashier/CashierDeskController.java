package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.LightTimer;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.domain.vo.CashierDeskDashboardResVo;
import com.ruoyi.billiard.domain.vo.DeskQueryResVo;
import com.ruoyi.billiard.domain.vo.LineUpVo;
import com.ruoyi.billiard.service.ILightTimerService;
import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
import com.ruoyi.common.utils.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cashier/desk")
public class CashierDeskController extends BaseController {
    @Autowired
    private IStoreDeskService storeDeskService;

    @Autowired
    private ILightTimerService lightTimerService;

    /**
     * 查询球桌列表
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/list")
    public ResultVo<List<StoreDesk>> list(@Validated StoreDesk storeDesk) {

        storeDesk.setStoreId(getStoreIdWithThrow());
        storeDesk.setEnable(Boolean.TRUE);
        List<StoreDesk> list = storeDeskService.selectStoreDeskList(storeDesk);
        list = ArrayUtil.groupBy(list, StoreDesk::getPlaceType).values().stream()
                .flatMap(p -> {
                    return p.stream().sorted(Comparator.comparing(StoreDesk::getDeskNum));
                }).collect(Collectors.toList());
        return ResultVo.success(list);
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/dashboard")
    public ResultVo<CashierDeskDashboardResVo> deskDashboard() {

        CashierDeskDashboardResVo res = storeDeskService.getDeskDashboard(getStoreIdWithThrow());

        return ResultVo.success(res);
    }

    /**
     * 查询球桌当前状态
     *
     * @param deskId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/{deskId}")
    public ResultVo<DeskQueryResVo> getDestLastInfo(@PathVariable Long deskId) {
        DeskQueryResVo desk = storeDeskService.queryDestCurrentInfo(deskId, getStoreIdWithThrow());
        return ResultVo.success(desk);
    }

    /**
     * 开台
     *
     * @param deskId 球桌id
     * @return 球桌基本信息
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{deskId}/pause")
    public ResultVo<DeskQueryResVo> startPause(@PathVariable Long deskId) {
        DeskQueryResVo desk = storeDeskService.pauseCalcFee(deskId, getStoreIdWithThrow());
        return ResultVo.success(desk);
    }

    /**
     * 开台
     *
     * @param deskId 球桌id
     * @return 球桌基本信息
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{deskId}/resume")
    public ResultVo<DeskQueryResVo> resumeDesk(@PathVariable Long deskId) {
        DeskQueryResVo desk = storeDeskService.resumeDesk(deskId, getStoreIdWithThrow());
        return ResultVo.success(desk);
    }

    /**
     * 开台
     *
     * @param deskId 球桌id
     * @return 球桌基本信息
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{deskId}/start")
    public ResultVo<DeskQueryResVo> startCalcFee(@PathVariable Long deskId) {
        DeskQueryResVo desk = storeDeskService.startCalcFee(deskId, getStoreIdWithThrow());
        return ResultVo.success(desk);
    }

    /**
     * 换台
     *
     * @param deskId 球桌id
     * @return 球桌基本信息
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{deskId}/swap")
    public ResultVo<DeskQueryResVo> swapDesk(@PathVariable Long deskId, @RequestParam Long orderId, @RequestParam Long newDeskId) {
        DeskQueryResVo desk = storeDeskService.swapToNewDesk(deskId, getStoreIdWithThrow(), orderId, newDeskId);
        return ResultVo.success(desk);
    }

    /*
    获取排队信息
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/line-up")
    public ResultVo<Map<Integer, LineUpVo>> lineUp() {
        Map<Integer, LineUpVo> res = storeDeskService.getLineUpInfo(getStoreIdWithThrow());
        return ResultVo.success(res);
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/line-up")
    public ResultVo<Boolean> lineUp(@RequestBody Map<Integer, LineUpVo> reqVo) {

        return ResultVo.success(storeDeskService.saveLineUpInfo(getStoreIdWithThrow(), reqVo));
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/light-timer")
    public ResultVo<Integer> creatTimer(@RequestBody LightTimer lightTimer) {
        lightTimer.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(lightTimerService.insertLightTimer(lightTimer));
    }
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/light-timer/remove")
    public ResultVo<Boolean> removeTimer(@RequestParam Date time) {
        return ResultVo.success(lightTimerService.removeByTime(time,getStoreIdWithThrow()));
    }
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/light-timer/list")
    public ResultVo<List<LightTimer>> list(  @RequestParam Date time) {
        List<LightTimer> list = lightTimerService.selectLightTimerList(LightTimer.builder().storeId(getStoreIdWithThrow())
                .endTime(time).build());
        return ResultVo.success(list);
    }
}
