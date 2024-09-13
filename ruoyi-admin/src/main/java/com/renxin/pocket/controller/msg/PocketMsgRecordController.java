package com.renxin.pocket.controller.msg;


import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.domain.PsyMsgRecord;
import com.renxin.common.service.IPsyMsgRecordService;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.framework.web.service.PocketTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 消息记录Controller
 * 
 * @author renxin
 * @date 2024-09-11
 */
@RestController
@RequestMapping("/pocket/msgRecord")
public class PocketMsgRecordController extends BaseController
{
    @Autowired
    private IPsyMsgRecordService psyMsgRecordService;

   /* @Autowired
    private SimpMessagingTemplate messagingTemplate;*/
    
    @Resource
    private PocketTokenService pocketTokenService;

    /**
     * 查询消息记录列表
     */
    //@PreAuthorize("@ss.hasPermi('system:record:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyMsgRecord req, HttpServletRequest request)
    {
        Long userId = pocketTokenService.getUserId(request);
        startPage();

        req.setTalkUserId1(req.getTalkUserId());
        req.setTalkUserType1(req.getTalkUserType());
        req.setTalkUserId2(userId);
        req.setTalkUserType2(1);//来访者
        List<PsyMsgRecord> list = psyMsgRecordService.selectPsyMsgRecordList(req);
        return getDataTable(list);
    }

    /**
     * 新增消息记录
     */
    //@PreAuthorize("@ss.hasPermi('system:record:add')")
    //@PostMapping("/sendMsg")
/*    @MessageMapping("/chat.addUser")
    public AjaxResult add(@RequestBody PsyMsgRecord psyMsgRecord, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
            psyMsgRecord.setSendUserId(consultId);
            psyMsgRecord.setSendUserType(2);//咨询师
        return toAjax(psyMsgRecordService.insertPsyMsgRecord(psyMsgRecord));
    }*/
    

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
