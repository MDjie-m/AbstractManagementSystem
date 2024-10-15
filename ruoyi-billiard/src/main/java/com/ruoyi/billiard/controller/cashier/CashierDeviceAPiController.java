package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.vo.AddDeskScoreReqVo;
import com.ruoyi.billiard.domain.vo.BaseDeviceReqVo;
import com.ruoyi.billiard.domain.vo.DeskCaptureReqVo;
import com.ruoyi.billiard.domain.vo.LightSwitchReqVo;
import com.ruoyi.billiard.service.IDeskLightService;
import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.AESUtils;
import com.ruoyi.common.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 设备直接调用api
 */
@RestController
@RequestMapping("cashier/device/api")
@Anonymous
public class CashierDeviceAPiController {
    @Value("${cashier.aes-key}")
    private String aesKey;
    @Resource
    private IStoreDeskService storeDeskService;

    @Resource
    private IDeskLightService deskLightService;

    @PostMapping("/desk/score")
    @Anonymous
    public ResultVo<Boolean> addScore(@RequestBody @Validated AddDeskScoreReqVo reqVo) {
        return ResultVo.success(storeDeskService.addScore(reqVo));
    }

    @PostMapping("/desk/light")
    @Anonymous
    public ResultVo<Boolean> lightSwitch(@RequestBody @Validated LightSwitchReqVo reqVo) {

        return ResultVo.success(deskLightService.switchLight(reqVo));
    }
    @PostMapping("/desk/capture")
    public ResultVo<Boolean> addCapture(@RequestBody @Validated DeskCaptureReqVo reqVo) {

        return ResultVo.success(storeDeskService.addCapture(reqVo));
    }


}
