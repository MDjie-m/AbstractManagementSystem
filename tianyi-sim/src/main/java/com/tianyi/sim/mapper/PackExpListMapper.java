package com.tianyi.sim.mapper;

import java.util.List;

import com.tianyi.sim.domain.PackExpList;

/**
 * 套餐到期预警Mapper接口
 *
 * @author tianyi
 * @date 2024-11-06
 */
public interface PackExpListMapper {

    /**
     * 查询套餐到期预警列表
     *
     * @param packExpList 套餐到期预警
     * @return 套餐到期预警集合
     */
    public List<PackExpList> selectPackExpListList(PackExpList packExpList);


    /**
     * 查询港华套餐到期预警列表
     *
     * @param packExpList 套餐到期预警
     * @return 套餐到期预警集合
     */
    public List<PackExpList> selectGhPackExpListList(PackExpList packExpList);

}
