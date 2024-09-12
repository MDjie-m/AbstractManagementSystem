package com.renxin.consultant.controller;


import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.domain.PsyMsgRecord;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.service.IPsyMsgRecordService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.framework.web.service.ConsultantTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 消息记录Controller
 * 
 * @author renxin
 * @date 2024-09-11
 */
@RestController
@RequestMapping("/consultant/msgRecord")
public class ConsultantMsgRecordController extends BaseController
{
    @Autowired
    private IPsyMsgRecordService psyMsgRecordService;

   /* @Autowired
    private SimpMessagingTemplate messagingTemplate;*/

    @Resource
    ConsultantTokenService consultantTokenService;
    
    /**
     * 查询消息记录列表
     */
    //@PreAuthorize("@ss.hasPermi('system:record:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyMsgRecord psyMsgRecord, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        startPage();
        List<PsyMsgRecord> list = psyMsgRecordService.selectPsyMsgRecordList(psyMsgRecord);
        return getDataTable(list);
    }

    /**
     * 新增消息记录
     */
    //@PreAuthorize("@ss.hasPermi('system:record:add')")
    //@PostMapping("/sendMsg")
    @MessageMapping("/chat.addUser")
    public AjaxResult add(@RequestBody PsyMsgRecord psyMsgRecord, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
            psyMsgRecord.setSendUserId(consultId);
            psyMsgRecord.setSendUserType(2);//咨询师
        return toAjax(psyMsgRecordService.insertPsyMsgRecord(psyMsgRecord));
    }

  
   // @SendTo("/topic/public")
    //@PostMapping("/addUser")
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public AjaxResult addUser(@RequestBody PsyMsgRecord psyMsgRecord, SimpMessageHeaderAccessor headerAccessor , HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        // 将用户添加到WebSocket会话属性中
        headerAccessor.getSessionAttributes().put("username", "2-"+consultId);
        return AjaxResult.success();
    }

    /**
     * 获取消息记录详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:record:query')")
    /*@GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyMsgRecordService.selectPsyMsgRecordById(id));
    }*/
    
//    /**
//     * 修改消息记录
//     */
//    //@PreAuthorize("@ss.hasPermi('system:record:edit')")
//    @PutMapping
//    public AjaxResult edit(@RequestBody PsyMsgRecord psyMsgRecord)
//    {
//        return toAjax(psyMsgRecordService.updatePsyMsgRecord(psyMsgRecord));
//    }
//
//    /**
//     * 删除消息记录
//     */
//    //@PreAuthorize("@ss.hasPermi('system:record:remove')")
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(psyMsgRecordService.deletePsyMsgRecordByIds(ids));
//    }
}
