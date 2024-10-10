package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.domain.GoodsCategory;
import com.ruoyi.billiard.service.IGoodsCategoryService;
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

    @Resource
    private IGoodsCategoryService goodsCategoryService;

    @GetMapping("list")
    public ResultVo<List<Goods>> list(Goods goods) {
        goods.setStoreId(getStoreId());
        goods.setSell(Boolean.TRUE);
        return ResultVo.success(goodsService.selectGoodsList(goods));
    }

    @GetMapping("category/list")
    public ResultVo<List<GoodsCategory>> listCategories() {
        return ResultVo.success(goodsCategoryService.selectGoodsCategoryList(GoodsCategory.builder()
                .storeId(getStoreIdWithThrow()).build()));
    }
}
