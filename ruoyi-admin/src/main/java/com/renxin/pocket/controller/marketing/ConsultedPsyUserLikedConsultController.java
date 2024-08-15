package com.renxin.pocket.controller.marketing;

import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.user.domain.PsyUserLikedConsult;
import com.renxin.user.service.IPsyUserLikedConsultService;
import com.renxin.user.vo.PsyUserLikedConsultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的关注
 *
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/consulted/user/liked/consult")
public class ConsultedPsyUserLikedConsultController extends BaseController {

    @Resource
    private PocketTokenService pocketTokenService;

    @Resource
    private IPsyUserLikedConsultService psyConsultFocusService;

    @PostMapping("/getLiked")
    public AjaxResult getLiked(@RequestBody PsyUserLikedConsult req) {
        return AjaxResult.success(psyConsultFocusService.getLiked(req));
    }

    @GetMapping("/getLikes")
    public TableDataInfo getLikes(HttpServletRequest request) {
        Long id = pocketTokenService.getUserId(request);
        List<PsyUserLikedConsultVO> list = new ArrayList<>();
        startPage();
        if (id == -1) {
            return getDataTable(list);
        }

        list = psyConsultFocusService.getLikes(id);
        return getDataTable(list);
    }

    @PostMapping
    public AjaxResult add(@RequestBody PsyUserLikedConsult req) {
        return AjaxResult.success(psyConsultFocusService.add(req));
    }

    @PostMapping("/del")
    public AjaxResult del(@RequestBody PsyUserLikedConsult req) {
        return AjaxResult.success(psyConsultFocusService.del(req));
    }

}
