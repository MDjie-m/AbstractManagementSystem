package com.ruoyi.web.controller.workflow;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.workflow.domain.bo.WfTaskBo;
import com.ruoyi.workflow.service.IWfTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * 工作流任务管理
 *
 * @author KonBAI
 * @createTime 2022/3/10 00:12
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/task")
public class WfTaskController {

    private final IWfTaskService flowTaskService;

    /**
     * 取消申请
     */
    @PostMapping(value = "/stopProcess")
    @SaCheckPermission("workflow:process:cancel")
    public R stopProcess(@RequestBody WfTaskBo bo) {
        flowTaskService.stopProcess(bo);
        return R.ok();
    }
    
    /**
     * 收回流程
     */
    @PostMapping(value = "/recallProcess")
    @SaCheckPermission("workflow:process:recall")
    public R recallProcess(@RequestBody WfTaskBo bo) {
    	return flowTaskService.recallProcess(bo);
    }

    /**
     * 撤回流程
     */
    @PostMapping(value = "/revokeProcess")
    @SaCheckPermission("workflow:process:revoke")
    public R revokeProcess(@RequestBody WfTaskBo bo) {
        flowTaskService.revokeProcess(bo);
        return R.ok();
    }

    /**
     * 获取流程变量
     * @param taskId 流程任务Id
     */
    @GetMapping(value = "/processVariables/{taskId}")
    @SaCheckPermission("workflow:process:query")
    public R processVariables(@PathVariable(value = "taskId") String taskId) {
        return R.ok(flowTaskService.getProcessVariables(taskId));
    }

    /**
     * 审批任务
     */
    @PostMapping(value = "/complete")
    @SaCheckPermission("workflow:process:approval")
    public R complete(@RequestBody WfTaskBo bo) {
        flowTaskService.complete(bo);
        return R.ok();
    }

    /**
     * 驳回任务
     */
    @PostMapping(value = "/reject")
    @SaCheckPermission("workflow:process:approval")
    public R taskReject(@RequestBody WfTaskBo taskBo) {
        flowTaskService.taskReject(taskBo);
        return R.ok();
    }
    
    /**
     * 拒绝任务
     */
    @PostMapping(value = "/refuse")
    @SaCheckPermission("workflow:process:approval")
    public R taskRefuse(@RequestBody WfTaskBo taskBo) {
        flowTaskService.taskRefuse(taskBo);
        return R.ok();
    }

    /**
     * 退回任务
     */
    @PostMapping(value = "/return")
    @SaCheckPermission("workflow:process:approval")
    public R taskReturn(@RequestBody WfTaskBo bo) {
        flowTaskService.taskReturn(bo);
        return R.ok();
    }
    
    
    /**
     * 加签任务
     */
    @PostMapping(value = "/addSign")
    @SaCheckPermission("workflow:process:approval")
    public R addSignTask(@RequestBody WfTaskBo bo) {
        flowTaskService.addSignTask(bo);
        return R.ok("加签任务成功");
    }
    
    /**
     * 多实例加签任务
     */
    @PostMapping(value = "/multiInstanceAddSign")
    @SaCheckPermission("workflow:process:approval")
    public R multiInstanceAddSign(@RequestBody WfTaskBo bo) {
        flowTaskService.multiInstanceAddSign(bo);
        return R.ok("多实例加签任务成功");
    }
    
    /**
     * 任务跳转
     */
    @PostMapping(value = "/jump")
    @SaCheckPermission("workflow:process:approval")
    public R taskJump(@RequestBody WfTaskBo bo) {
        flowTaskService.taskJump(bo);
        return R.ok();
    }
    
    /**
     * 获取用户任务节点,作为跳转任务使用
     */
    @PostMapping(value = "/userTask")
    @SaCheckPermission("workflow:process:approval")
    public R userTaskList(@RequestBody WfTaskBo bo) {
    	return flowTaskService.userTaskList(bo);
    }

    /**
     * 获取所有可回退的节点
     */
    @PostMapping(value = "/returnList")
    @SaCheckPermission("workflow:process:query")
    public R findReturnTaskList(@RequestBody WfTaskBo bo) {
        return R.ok(flowTaskService.findReturnTaskList(bo));
    }

    /**
     * 删除任务
     */
    @DeleteMapping(value = "/delete")
    @SaCheckPermission("workflow:process:approval")
    public R delete(@RequestBody WfTaskBo bo) {
        flowTaskService.deleteTask(bo);
        return R.ok();
    }

    /**
     * 认领/签收任务
     */
    @PostMapping(value = "/claim")
    @SaCheckPermission("workflow:process:claim")
    public R claim(@RequestBody WfTaskBo bo) {
        flowTaskService.claim(bo);
        return R.ok();
    }

    /**
     * 取消认领/签收任务
     */
    @PostMapping(value = "/unClaim")
    @SaCheckPermission("workflow:process:claim")
    public R unClaim(@RequestBody WfTaskBo bo) {
        flowTaskService.unClaim(bo);
        return R.ok();
    }

    /**
     * 委派任务
     */
    @PostMapping(value = "/delegate")
    @SaCheckPermission("workflow:process:approval")
    public R delegate(@RequestBody WfTaskBo bo) {
        if (ObjectUtil.hasNull(bo.getTaskId(), bo.getUserId())) {
            return R.fail("参数错误！");
        }
        flowTaskService.delegateTask(bo);
        return R.ok();
    }

    /**
     * 转办任务
     */
    @PostMapping(value = "/transfer")
    @SaCheckPermission("workflow:process:approval")
    public R transfer(@RequestBody WfTaskBo bo) {
        if (ObjectUtil.hasNull(bo.getTaskId(), bo.getUserId())) {
            return R.fail("参数错误！");
        }
        flowTaskService.transferTask(bo);
        return R.ok();
    }

    /**
     * 自定义业务使用
     * 判断是否是第一个发起人节点，目前只针对退回，驳回情况进行处理
     * @param dataId 流程业务数据id, variables 变量集合,json对象
     * @return
     */
    @PostMapping(value = "/isFirstInitiator/{dataId}")
    public R isFirstInitiator(@PathVariable(value = "dataId") String dataId,
            @RequestBody Map<String, Object> variables) {
        
    	String sRevokeOrReject = (String) variables.get("actStatus");
    	String processInstanceId = (String) variables.get("processInstanceId");
    	return R.ok(flowTaskService.isFirstInitiator(processInstanceId, sRevokeOrReject));
    }	
    
    /**
     * 自定义业务使用
     *  删除自定义业务任务关联表与流程历史表，以便可以重新发起流程。
     * @param dataId 流程业务数据id, variables 变量集合,json对象
     * @return
     */
    @PostMapping(value = "/deleteActivityAndJoin/{dataId}")
    public R del(@PathVariable(value = "dataId") String dataId,
            @RequestBody Map<String, Object> variables) {
    	String sRevokeOrReject = (String) variables.get("actStatus");
    	String processInstanceId = (String) variables.get("processInstanceId");
    	return R.ok(flowTaskService.deleteActivityAndJoin(dataId, processInstanceId, sRevokeOrReject));
    }	
    
    /**
     * 获取下个任务节点信息
     *
     * @param processId 任务ID
     */
    @PostMapping(value = "/nextFlowNode")
    public R getNextFlowNode(@RequestBody WfTaskBo bo) {
        return flowTaskService.getNextFlowNode(bo);
    }
    
    /**
     * 生成流程图
     *
     * @param processId 任务ID
     */
    @RequestMapping("/diagram/{processId}")
    public void genProcessDiagram(HttpServletResponse response,
                                  @PathVariable("processId") String processId) {
        InputStream inputStream = flowTaskService.diagram(processId);
        OutputStream os = null;
        BufferedImage image = null;
        try {
            image = ImageIO.read(inputStream);
            response.setContentType("image/png");
            os = response.getOutputStream();
            if (image != null) {
                ImageIO.write(image, "png", os);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
