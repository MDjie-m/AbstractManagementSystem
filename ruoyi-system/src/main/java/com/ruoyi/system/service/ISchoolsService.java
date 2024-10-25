package com.ruoyi.system.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.system.domain.bo.SchoolsBo;
import com.ruoyi.system.domain.vo.SchoolsVo;

import java.util.Collection;
import java.util.List;

/**
 * 学校Service接口
 *
 * @author nbacheng
 * @date 2024-10-25
 */
public interface ISchoolsService {

    /**
     * 查询学校
     */
    SchoolsVo queryById(Long id);

    /**
     * 查询学校列表
     */
    TableDataInfo<SchoolsVo> queryPageList(SchoolsBo bo, PageQuery pageQuery);

    /**
     * 查询学校列表
     */
    List<SchoolsVo> queryList(SchoolsBo bo);

    /**
     * 新增学校
     */
    Boolean insertByBo(SchoolsBo bo);

    /**
     * 修改学校
     */
    Boolean updateByBo(SchoolsBo bo);

    /**
     * 校验并批量删除学校信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
