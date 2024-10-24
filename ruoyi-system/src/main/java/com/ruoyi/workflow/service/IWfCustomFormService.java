package com.ruoyi.workflow.service;

import com.ruoyi.workflow.domain.vo.CustomFormVo;
import com.ruoyi.workflow.domain.vo.WfCustomFormVo;
import com.ruoyi.workflow.domain.WfCustomForm;
import com.ruoyi.workflow.domain.bo.WfCustomFormBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

import org.flowable.bpmn.model.BpmnModel;

/**
 * 流程业务单Service接口
 *
 * @author nbacheng
 * @date 2023-10-09
 */
public interface IWfCustomFormService {

    /**
     * 查询流程业务单
     */
    WfCustomFormVo queryById(Long id);

    /**
     * 查询流程业务单列表
     */
    TableDataInfo<WfCustomFormVo> queryPageList(WfCustomFormBo bo, PageQuery pageQuery);

    /**
     * 查询流程业务单列表
     */
    List<WfCustomFormVo> queryList(WfCustomFormBo bo);

    /**
     * 新增流程业务单
     */
    Boolean insertByBo(WfCustomFormBo bo);

    /**
     * 修改流程业务单
     */
    Boolean updateByBo(WfCustomFormBo bo);

    /**
     * 校验并批量删除流程业务单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    void updateCustom(CustomFormVo customFormVo);

	List<WfCustomForm> selectSysCustomFormByServiceName(String serviceName);
	WfCustomFormBo selectSysCustomFormByDeployId(String deployId);
	/**
     * 保存流程实例关联自定义业务表单
     * @param procDefId 流程定义ID
     * @param deployId 部署ID
     * @param deployName 部署名称
     * @param bpmnModel bpmnModel对象
     * @return
     */
    boolean saveCustomDeployForm(String procDefKey, String deployId, String deployName, BpmnModel bpmnModel);
}
