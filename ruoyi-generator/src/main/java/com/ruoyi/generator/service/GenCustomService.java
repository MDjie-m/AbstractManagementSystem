package com.ruoyi.generator.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.generator.constants.CustomConstants;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.domain.bo.GenCustomBo;
import com.ruoyi.generator.domain.bo.GenSqlBo;
import com.ruoyi.generator.filter.DataFilterChain;
import com.ruoyi.generator.mapper.GenCustomMapper;
import com.ruoyi.generator.mapper.GenTableColumnMapper;
import com.ruoyi.generator.mapper.GenTableMapper;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author nuoqin
 * @DATA 2024/8/15 16:32
 */
@Service
@Slf4j
public class GenCustomService {

    private final GenTableColumnMapper genTableColumnMapper;

    private final GenCustomMapper customMapper;

    private final GenTableMapper genTableMapper;

    private final DataFilterChain dataFilterChain;



    public GenCustomService(GenTableColumnMapper genTableColumnMapper,
                            GenCustomMapper customMapper,
                            GenTableMapper genTableMapper,
                            DataFilterChain dataFilterChain) {
        this.genTableColumnMapper = genTableColumnMapper;
        this.customMapper=customMapper;
        this.genTableMapper=genTableMapper;
        this.dataFilterChain = dataFilterChain;
    }

    public List<GenTableColumn> columns(Long tableId) {
        List<GenTableColumn> tableColumns = getGenTableColumns(tableId);

        if (CollectionUtils.isEmpty(tableColumns)){
            throw new ServiceException(String.format("未定义的模板数据，tableId: %d",tableId));
        }
        tableColumns.sort(Comparator.comparingInt(GenTableColumn::getSort));
        //对字段数据进行赋值
        tableColumns.forEach(column->{
            if (ReUtil.isMatch(CustomConstants.RE_DICT,column.getHtmlType())) {
                String dictType = column.getDictType();
                List<SysDictData> cache = DictUtils.getDictCache(dictType);
                column.setDicts(cache);
            }
        });
        return tableColumns;
    }

    /**
     * 从缓存中获取
     * @param tableId
     * @return
     */
    public List<GenTableColumn> getGenTableColumns(Long tableId) {
        return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
    }

    /**
     * 获取数据集合
     * @param tableId 表id
     * @param data  查询id
     * @return id数据
     */
    public TableDataInfo findList(Long tableId, Map<String, Object> data) throws Exception {
        GenTable table = genTableMapper.selectGenTableById(tableId);
        List<GenTableColumn> tableColumns = getGenTableColumns(tableId);
        GenSqlBo sqlBo = GenSqlBo.builderSelectList(tableColumns, data);
        String preSql = sqlBo.getPreSql();
        String afterSql = sqlBo.getAfterSql();
        Integer page = MapUtil.getInt(data, "pageNum", 1);
        Integer size = MapUtil.getInt(data, "pageSize", 10);
        PageHelper.startPage(page,size);
        String sql="select " + preSql +" from "+table.getTableName()+" where "+afterSql;
        if (StringUtils.isNotEmpty(sqlBo.getSortSql())){
            sql+="  "+ sqlBo.getSortSql();
        }
        log.info("执行查询列表sql:{}",sql);
        List<Map<String, Object>> list = customMapper.selectTableList(sql);
        dataFilterChain.doFilter(dataFilterChain, GenCustomBo.builder()
                .columns(tableColumns)
                .data(list)
                .build());
        PageInfo<Map<String, Object>> pageInfo=new PageInfo<>(list);
        return new TableDataInfo(list,Integer.parseInt(pageInfo.getTotal()+""));
    }


    /**
     * 获取数据集合
     * @param tableId 表id
     * @param id  表内id
     * @return id数据
     */
    public Map<String, Object> findById(Long tableId, Long id) throws Exception {
        GenTable table = genTableMapper.selectGenTableById(tableId);
        List<GenTableColumn> tableColumns = getGenTableColumns(tableId);
        GenSqlBo sqlBo = GenSqlBo.builderSelectById(tableColumns);
        String preSql = sqlBo.getPreSql();
        String afterSql = sqlBo.getAfterSql();
        String selectSql="select "+preSql +" from "+table.getTableName()+" where "+afterSql+"="+id;
        log.info("执行自定义查询sql: {}",selectSql);
        Map<String, Object> map = customMapper.selectTableMap(selectSql);
        dataFilterChain.doFilter(dataFilterChain, GenCustomBo.builder()
                .columns(tableColumns)
                .singleData(map)
                .build());
        return map;
    }

    /**
     * 新增
     * @param tableId 表id
     * @param data 数据
     */
    public void add(Long tableId, Map<String, Object> data) {
        GenTable table = genTableMapper.selectGenTableById(tableId);
        List<GenTableColumn> tableColumns = getGenTableColumns(tableId);
        GenSqlBo sqlBo = GenSqlBo.builderInsert(tableColumns, data);
        String preSql = sqlBo.getPreSql();
        String afterSql = sqlBo.getAfterSql();
        //preSql+="create_time,create_user,dept_id,flag";
        //afterSql+= "'"+DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN)+"',"+ SecurityUtils.getUserId()+","+SecurityUtils.getDeptId()+",0";
        String insertSql="insert into "+table.getTableName()+"("+preSql+") values ("+afterSql+")";
        log.info("执行自定义新增sql: {}",insertSql);
        customMapper.insertTable(insertSql);
    }



    public void update(Long tableId, Map<String, Object> data) {
        GenTable table = genTableMapper.selectGenTableById(tableId);
        List<GenTableColumn> tableColumns = getGenTableColumns(tableId);
        GenSqlBo sqlBo = GenSqlBo.builderUpdate(tableColumns, data);
        String preSql = sqlBo.getPreSql();
        String afterSql = sqlBo.getAfterSql();
        //如果更新携带 update_time update_user 需要开启
        //preSql+="update_time='"+DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN)+"',update_user="+SecurityUtils.getUserId();
        String updateSql="update "+table.getTableName()+" set "+preSql+" where "+afterSql;
        log.info("执行自定义修改sql: {}",updateSql);
        customMapper.updateGenTable(updateSql);
    }



    public void delete(Long tableId, List<Long> ids) {
        GenTable table = genTableMapper.selectGenTableById(tableId);
        List<GenTableColumn> tableColumns = getGenTableColumns(tableId);
        GenSqlBo sqlBo = GenSqlBo.builderDelete(tableColumns, ids);
        String preSql = sqlBo.getPreSql();
        String afterSql = sqlBo.getAfterSql();
        //afterSql+= DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN)+","+ SecurityUtils.getUserId();
        String updateSql="update "+table.getTableName()+" set "+preSql+" where "+afterSql;
        log.info("执行自定义删除sql: {}",updateSql);
        customMapper.deleteTableByIds(updateSql);
    }

}
