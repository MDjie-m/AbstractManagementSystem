package com.ruoyi.web.controller.git;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Anonymous
@RestController
@RequestMapping("/git")
public class GitController {
    private static final Logger log = LoggerFactory.getLogger(GitController.class);

    /**
     * 通用上传请求（单个）
     */
    @RequestMapping("/pushStatus")
    public AjaxResult pushStatus(HttpServletRequest request, @RequestBody String postData) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("ok", "yes");
        return ajax;
    }
}
