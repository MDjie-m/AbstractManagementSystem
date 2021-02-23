package com.stdiet.custom.service.impl;

import com.stdiet.common.utils.DateUtils;
import com.stdiet.common.utils.StringUtils;
import com.stdiet.custom.domain.SysIngredient;
import com.stdiet.custom.domain.SysIngredientNotRec;
import com.stdiet.custom.domain.SysIngredientRec;
import com.stdiet.custom.mapper.SysIngredientMapper;
import com.stdiet.custom.service.ISysIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 食材Service业务层处理
 *
 * @author wonder
 * @date 2020-12-15
 */
@Service
public class SysIngredientServiceImpl implements ISysIngredientService {
    @Autowired
    private SysIngredientMapper sysIngredientMapper;

    /**
     * 查询食材
     *
     * @param id 食材ID
     * @return 食材
     */
    @Override
    public SysIngredient selectSysIngredientById(Long id) {
        return sysIngredientMapper.selectSysIngredientById(id);
    }

    /**
     * 查询食材列表
     *
     * @param sysIngredient 食材
     * @return 食材
     */
    @Override
    public List<SysIngredient> selectSysIngredientList(SysIngredient sysIngredient) {
        return sysIngredientMapper.selectSysIngredientList(sysIngredient);
    }

    /**
     * 新增食材
     *
     * @param sysIngredient 食材
     * @return 结果
     */
    @Override
    public int insertSysIngredient(SysIngredient sysIngredient) {
        sysIngredient.setCreateTime(DateUtils.getNowDate());
        int rows = sysIngredientMapper.insertSysIngredient(sysIngredient);
        //
        insertRecommand(sysIngredient);
        //
        insertNotRecommand(sysIngredient);
        return rows;
    }

    /**
     * 新增推荐标签
     * @param ingredient
     */
    public void insertRecommand(SysIngredient ingredient) {
        Long[] recIds = ingredient.getRecIds();
        if(StringUtils.isNotNull(recIds)) {
            List<SysIngredientRec> list = new ArrayList<SysIngredientRec>();
            for(Long recId: recIds) {
                SysIngredientRec rec = new SysIngredientRec();
                rec.setIngredientId(ingredient.getId());
                rec.setRecommandId(recId);
                list.add(rec);
            }
            if(list.size() > 0) {
                sysIngredientMapper.batchIngredientRec(list);
            }
        }
    }

    /**
     * 新增不推荐标签
     * @param ingredient
     */
    public void insertNotRecommand(SysIngredient ingredient) {
        Long[] notRecIds = ingredient.getNotRecIds();
        if(StringUtils.isNotNull(notRecIds)) {
            List<SysIngredientNotRec> list = new ArrayList<SysIngredientNotRec>();
            for(Long recId: notRecIds) {
                SysIngredientNotRec notRec = new SysIngredientNotRec();
                notRec.setIngredientId(ingredient.getId());
                notRec.setRecommandId(recId);
                list.add(notRec);
            }
            if(list.size() > 0) {
                sysIngredientMapper.batchIngredientNotRec(list);
            }
        }
    }

    /**
     * 修改食材
     *
     * @param sysIngredient 食材
     * @return 结果
     */
    @Override
    public int updateSysIngredient(SysIngredient sysIngredient) {
        sysIngredient.setUpdateTime(DateUtils.getNowDate());
        Long ingredientId = sysIngredient.getId();
        sysIngredientMapper.deleteIngredentNotRecByIngredientId(ingredientId);
        insertNotRecommand(sysIngredient);
        sysIngredientMapper.deleteIngredentRecByIngredientId(ingredientId);
        insertRecommand(sysIngredient);
        return sysIngredientMapper.updateSysIngredient(sysIngredient);
    }

    /**
     * 批量删除食材
     *
     * @param ids 需要删除的食材ID
     * @return 结果
     */
    @Override
    public int deleteSysIngredientByIds(Long[] ids) {
        sysIngredientMapper.deleteIngredentRecByIngredientIds(ids);
        sysIngredientMapper.deleteIngredentNotRecByIngredientIds(ids);
        return sysIngredientMapper.deleteSysIngredientByIds(ids);
    }

    /**
     * 删除食材信息
     *
     * @param id 食材ID
     * @return 结果
     */
    @Override
    public int deleteSysIngredientById(Long id) {
        sysIngredientMapper.deleteIngredentRecByIngredientId(id);
        sysIngredientMapper.deleteIngredentNotRecByIngredientId(id);
        return sysIngredientMapper.deleteSysIngredientById(id);
    }

    /**
     * 根据食材名称查询食材信息
     * @param name
     * @return
     */
    @Override
    public SysIngredient selectSysIngredientByName(String name){
        return sysIngredientMapper.selectSysIngredientByName(name);
    }
}