package com.ruoyi.system.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysProductTypeMapper;
import com.ruoyi.system.domain.SysProductType;
import com.ruoyi.system.service.ISysProductTypeService;

/**
 * 产品分类Service业务层处理
 * 
 * @author xgg
 * @date 2024-07-22
 */
@Service
public class SysProductTypeServiceImpl implements ISysProductTypeService 
{
    @Autowired
    private SysProductTypeMapper sysProductTypeMapper;

//    默认产品分类树形的深度为5
    private static int TREE_DEPTH=5;

    /**
     * 查询产品分类
     * 
     * @param productCode 产品分类主键
     * @return 产品分类
     */
    @Override
    public SysProductType selectSysProductTypeByProductCode(String productCode)
    {
        return sysProductTypeMapper.selectSysProductTypeByProductCode(productCode);
    }

    /**
     * 查询产品分类列表
     * 
     * @param sysProductType 产品分类
     * @return 产品分类
     */
    @Override
    public List<SysProductType> selectSysProductTypeList(SysProductType sysProductType)
    {
        return sysProductTypeMapper.selectSysProductTypeList(sysProductType);
    }

    /**
     * 新增产品分类
     * 
     * @param sysProductType 产品分类
     * @return 结果
     */
    @Override
    public int insertSysProductType(SysProductType sysProductType)
    {
        return sysProductTypeMapper.insertSysProductType(sysProductType);
    }

    /**
     * 修改产品分类
     * 
     * @param sysProductType 产品分类
     * @return 结果
     */
    @Override
    public int updateSysProductType(SysProductType sysProductType)
    {
        return sysProductTypeMapper.updateSysProductType(sysProductType);
    }

    /**
     * 批量删除产品分类
     * 
     * @param productCodes 需要删除的产品分类主键
     * @return 结果
     */
    @Override
    public int deleteSysProductTypeByProductCodes(String[] productCodes)
    {
        return sysProductTypeMapper.deleteSysProductTypeByProductCodes(productCodes);
    }

    /**
     * 删除产品分类信息
     * 
     * @param productCode 产品分类主键
     * @return 结果
     */
    @Override
    public int deleteSysProductTypeByProductCode(String productCode)
    {
        return sysProductTypeMapper.deleteSysProductTypeByProductCode(productCode);
    }

    /**
     *
     * @return 返回一个List,里面按照层级组装成一个TreeList
     */
    @Override
    public List<Map<String,Object>> selectSysProductTypeTreeList(Integer depth , Integer flag) {
        if (flag == 0){
            //        如果前端传入的depth为null，就默认深度为5
            if(Objects.isNull(depth))
                return listTreeByMap("cn0",TREE_DEPTH);
//        如果不为null就把depth作为参数传递，另外默认0为国产进口的父级编码，只有这俩的父级编码才是0
            return listTreeByMap("cn0",depth);
        }
        //        如果前端传入的depth为null，就默认深度为5
        if(Objects.isNull(depth))
            return listTreeByMap("0",TREE_DEPTH);
//        如果不为null就把depth作为参数传递，另外默认0为国产进口的父级编码，只有这俩的父级编码才是0
        return listTreeByMap("0",depth);
    }

    private List<Map<String,Object>> listTreeByMap(String parentCode,Integer depth){
//        创建一个存放map的list用来保存组装好的数据
        List<Map<String,Object>> mapList = new ArrayList<>();
        //1、按父级编码等于多少查一级分类（国产/进口）,国产进口的父编码为0，返回的是一个列表
        List<SysProductType> list = sysProductTypeMapper.selectChildren(parentCode);
        //如果查出来数据为0说明没有子产品，返回为null
        if(list.size()==0){
            return null;
        }
        // 如果深度大于1，则继续查询子产品
        if (depth > 1) {
//            循环遍历上面第一点查找出来的数据，组装成map格式，并根据深度需求继续查找他们对应的子编码，
            for (SysProductType sysProductType : list) {
//                创建一个map组装数据
                Map<String, Object> map = new HashMap<>();
                // 2、组装数据、value、label
                map.put("value", sysProductType.getProductCode());
                map.put("label", sysProductType.getProductName());

                // 3、递归查询编这个产品分类这个编码下的子产品,递归的时候深度相应减1
                List<Map<String, Object>> mapListChildren = listTreeByMap(sysProductType.getProductCode(), depth - 1);
                // 如果不为null，就说明有子产品编码，就拼接children
                if (!Objects.isNull(mapListChildren)) {
//                    mapListChildren不为null就组装其children
                    map.put("children", mapListChildren);
                }
                // 把上面组装的这个map放到mapList里去代表同一级别的循环到的这个数据组装完成。
                mapList.add(map);
            }
        } else {
//            这里是深度<=1时，说明不需要再进行递归查询，直接循环查找到的产品分类的list组装好数据就行，不需要再查找对应的children
            for (SysProductType sysProductType : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("value", sysProductType.getProductCode());
                map.put("label", sysProductType.getProductName());
                mapList.add(map);
            }
        }
//        返回本次组装好的mapList
        return mapList;
    }

    @Override
    public List<SysProductType> selectType(String[] newCodes) {
        return sysProductTypeMapper.selectType(newCodes);
    }

    @Override
    public SysProductType selectTag(String code) {
        return sysProductTypeMapper.selectTag(code);
    }
}
