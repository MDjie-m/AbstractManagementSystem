package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.service.IGoodsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("cashier/goods")
@PreAuthorize("@ss.hasRole('cashier')")
public class CashierGoodsController extends BaseController {

    @Resource
    private IGoodsService goodsService;

    @GetMapping("list")
    public ResultVo<List<Goods>> list() {
        return ResultVo.success(goodsService.selectGoodsList(Goods.builder()
                        .sell(Boolean.TRUE)
                .storeId(getStoreIdWithThrow()).build()));
    }
}
