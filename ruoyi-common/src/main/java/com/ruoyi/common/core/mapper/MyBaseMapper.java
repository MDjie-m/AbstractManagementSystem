package com.ruoyi.common.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.BaseEntityDel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Objects;

public interface MyBaseMapper<T> extends BaseMapper<T> {
    /**
     * 自定义批量插入
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int insertBatch(@Param("list") List<T> list);

    default  LambdaQueryWrapper<T> query(){
        return  new LambdaQueryWrapper<>();
    }
    default   QueryWrapper<T> normalQuery(){
        return  new  QueryWrapper<>();
    }
    default  LambdaUpdateWrapper<T> updateWrapper(){
        return  new LambdaUpdateWrapper<>();
    }


    /**
     * 自定义批量更新，条件为主键
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int updateBatch(@Param("list") List<T> list);
    int updateWithNull(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);

    int updateAllWithId(  T entity );


    default <V> boolean   exists(SFunction<T ,?> condition, V val){
        LambdaQueryWrapper<T> queryWrapper=   new LambdaQueryWrapper<>();
        queryWrapper.eq(  condition,val);
      return this.exists( queryWrapper);
    }

    default <V> boolean   existsIn(SFunction<T ,?> condition, List<V> val){
        LambdaQueryWrapper<T> queryWrapper=   new LambdaQueryWrapper<>();
        queryWrapper.in(  condition,val);
        return this.exists( queryWrapper);
    }
    default <V> boolean   existsWithDelFlag(SFunction< T, ?> condition, V val){
        LambdaQueryWrapper<T> queryWrapper=   new LambdaQueryWrapper<>();
        queryWrapper.eq(  condition,val);
        queryWrapper.last("and del_flag='0'");
        return this.exists( queryWrapper);
    }

    default <V,ID> boolean   existsExcludeId(SFunction< T, ?> condition, V conditionVal,SFunction< T, ID> idCondition,ID id){
        LambdaQueryWrapper<T> queryWrapper=   new LambdaQueryWrapper<>();
        queryWrapper.eq(  condition,conditionVal);
        queryWrapper.notIn(idCondition,id);
        return this.exists( queryWrapper);
    }
    default <V,ID> boolean   existsWithDelFlagExcludeId(SFunction< T, ?> condition, V conditionVal,SFunction< T, ID> idCondition,ID id){
        LambdaQueryWrapper<T> queryWrapper=   new LambdaQueryWrapper<>();
        queryWrapper.eq(  condition,conditionVal);
        queryWrapper.notIn(idCondition,id);
        queryWrapper.last("and del_flag='0'");
        return this.exists( queryWrapper);
    }
}
