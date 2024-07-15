package com.renxin.pocket.controller.consult;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.request.PsyConsultReq;
import com.renxin.psychology.request.PsyConsultServeConfigReq;
import com.renxin.psychology.service.IPsyConsultColumnService;
import com.renxin.psychology.service.IPsyConsultServeConfigService;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.vo.PsyConsultColumnVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 心理咨询师Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/pocket/consult")
public class PocketConsultantController extends BaseController
{
    @Resource
    private IPsyConsultService psyConsultService;

    @Resource
    private IPsyConsultColumnService psyConsultColumnService;

    @Resource
    private IPsyConsultServeConfigService psyConsultServeConfigService;

    /**
     * 查询心理咨询师列表
     */
    @PostMapping("/search")
    @RateLimiter
    public TableDataInfo list(@RequestBody PsyConsultReq req)
    {
        startPage();
        List<PsyConsult> list = psyConsultService.search(req);
        return getDataTable(list);
    }

    @GetMapping(value = "/getConsultWorksById/{id}")
    @RateLimiter
    public AjaxResult getConsultWorksById(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultService.getConsultWorksById(id));
    }


    /**
     * 获取心理咨询师详细信息
     */
    @GetMapping(value = "/{id}")
    @RateLimiter
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultService.getOne(id));
    }

    @GetMapping(value = "/serve/{id}")
    @RateLimiter
    public AjaxResult getServe(@PathVariable("id") Long id)
    {
        PsyConsultServeConfigReq req = new PsyConsultServeConfigReq();
        req.setCId(id);
        req.setStatus("0");
        return AjaxResult.success(psyConsultServeConfigService.getList(req));
    }

    /**
     * 获取心理咨询师详细信息
     */
    @GetMapping(value = "/getConsultInfoByServe/{cId}/{sId}")
    @RateLimiter
    public AjaxResult getConsultInfoByServe(@PathVariable("cId") Long cId, @PathVariable("sId") Long sId)
    {
        return AjaxResult.success(psyConsultService.getConsultInfoByServe(cId, sId));
    }

    @GetMapping(value = "/getConsultColumn/{cat}/{id}")
    @RateLimiter
    public TableDataInfo getConsultColumn(@PathVariable("cat") String cat, @PathVariable("id") Long id)
    {
        startPage();
        List<PsyConsultColumnVO> list = new ArrayList<>();
        if (!"0".equals(cat) && !"1".equals(cat)) {
            return getDataTable(list);
        }
        PsyConsultColumnVO req = new PsyConsultColumnVO();
        req.setCat(cat);
        req.setStatus("0");
        if (id != 0L) {
            req.setConsultId(id);
        }

        list = psyConsultColumnService.getList(req);

        return getDataTable(list);
    }


}
