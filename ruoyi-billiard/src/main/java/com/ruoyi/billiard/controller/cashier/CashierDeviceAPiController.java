package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.AddDeskScoreReqVo;
import com.ruoyi.billiard.domain.BaseDeviceReqVo;
import com.ruoyi.billiard.domain.DeskCaptureReqVo;
import com.ruoyi.billiard.service.IStockService;
import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.AESUtils;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/desk/score")
    @Anonymous
    public ResultVo<Boolean> addScore(@RequestBody AddDeskScoreReqVo reqVo) {
        fillStoreId(reqVo);
        return ResultVo.success(storeDeskService.addScore(reqVo));
    }
    @PostMapping("/desk/capture")
    public ResultVo<Boolean> addCapture(@RequestBody DeskCaptureReqVo reqVo) {
        fillStoreId(reqVo);
        return ResultVo.success(storeDeskService.addCapture(reqVo));
    }

    private void fillStoreId(BaseDeviceReqVo reqVo) {
        AssertUtil.notNullOrEmpty(reqVo.getApiKey(), "非法请求");
        try {
            reqVo.setStoreId(Long.parseLong(AESUtils.decryptECB(reqVo.getApiKey(), aesKey)));
        } catch (Exception e) {
            throw new ServiceException("非法请求");
        }
    }
}
