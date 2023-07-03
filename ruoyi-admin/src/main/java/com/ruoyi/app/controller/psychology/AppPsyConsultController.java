package com.ruoyi.app.controller.psychology;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.psychology.domain.PsyConsult;
import com.ruoyi.psychology.request.PsyConsultReq;
import com.ruoyi.psychology.service.IPsyConsultServeService;
import com.ruoyi.psychology.service.IPsyConsultService;
import com.ruoyi.psychology.vo.PsyConsultServeVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 心理咨询师Controller
 * 
 * @author ruoyi
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/app/consult")
public class AppPsyConsultController extends BaseController
{
    @Resource
    private IPsyConsultService psyConsultService;

    @Resource
    private IPsyConsultServeService psyConsultServeService;

    /**
     * 查询心理咨询师列表
     */
    @PostMapping("/search")
    public TableDataInfo list(@RequestBody PsyConsultReq req)
    {
        startPage();
        List<PsyConsult> list = psyConsultService.search(req);
        return getDataTable(list);
    }

    /**
     * 获取心理咨询师详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultService.getOne(id));
    }

    @GetMapping(value = "/serve/{id}")
    public AjaxResult getServe(@PathVariable("id") Long id)
    {
        PsyConsultServeVO req = new PsyConsultServeVO();
        req.setConsultId(id);
        req.setStatus("0");
        return AjaxResult.success(psyConsultServeService.getList(req));
    }



}
