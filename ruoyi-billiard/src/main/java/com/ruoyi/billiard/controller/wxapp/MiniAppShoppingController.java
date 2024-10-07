package com.ruoyi.billiard.controller.wxapp;

import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.domain.GoodsCategory;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.enums.DeskStatus;
import com.ruoyi.billiard.service.IGoodsCategoryService;
import com.ruoyi.billiard.service.IGoodsService;
import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.common.core.domain.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-22:17
 * @className: MiniAppShoppingController
 */
@RestController
@RequestMapping("api/mini-app/shopping")
public class MiniAppShoppingController {

    @Autowired
    private IGoodsCategoryService goodsCategoryService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IStoreDeskService storeDeskService;

    /**
     * 根据门店id查询门店台桌使用情况列表
     * @param storeId
     * @return 台桌使用情况列表
     */
    @PreAuthorize("@ss.hasPermi('miniapp:shopping:list')")
    @GetMapping("/table-list/{storeId}")
    public ResultVo<List<StoreDesk>> getTableList(@PathVariable("storeId") Long storeId) {
        StoreDesk storeDesk = new StoreDesk();
        storeDesk.setStoreId(storeId);
        List<StoreDesk> storeDesks = storeDeskService.selectStoreDeskList(storeDesk);
        storeDesks.forEach(desk -> {
            Integer status = desk.getStatus();
            // 计费中查询是否有订单
            if (!Objects.equals(status, DeskStatus.WAIT.getValue())) {
                System.out.println("desk = " + desk);
            }
        });
        return ResultVo.success(storeDesks);
    }

    /**
     * 获取商品分类列表
     * @param storeId
     * @return 商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('miniapp:shopping:list')")
    @GetMapping("/goods-category-list/{storeId}")
    public ResultVo<List<GoodsCategory>> getGoodsCategoryList(@PathVariable("storeId") Long storeId) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setStoreId(storeId);
        return ResultVo.success(goodsCategoryService.selectGoodsCategoryList(goodsCategory));
    }

    /**
     * 根据商品分类Id获取和门店id获取商品列表
     */
    @PreAuthorize("@ss.hasPermi('miniapp:shopping:list')")
    @PostMapping("/goods-list")
    public ResultVo<List<Goods>> getGoodsList(@RequestBody Goods goods) {
        return ResultVo.success(goodsService.selectGoodsList(goods));
    }

    /**
     * 门店台桌商品订单上传
     */
    @PreAuthorize("@ss.hasPermi('miniapp:shopping:list')")
    @PostMapping("/upload-order")
    public ResultVo uploadOrder(@RequestBody Goods goods) {
        return ResultVo.success();
    }
}
