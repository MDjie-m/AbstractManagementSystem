package com.ruoyi.web.controller.git;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.feishu.FeiShuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    @Autowired
    FeiShuService feiShuService;

    /**
     * 通用上传请求（单个）
     */
    @RequestMapping("/pushStatus")
    public AjaxResult pushStatus(HttpServletRequest request, @RequestBody String postData) {
        AjaxResult ajax = AjaxResult.success();
        Map<String, Object> gitMap = JSON.parseObject(postData, Map.class);
        JSONObject projectObj = (JSONObject) gitMap.get("project");
        String name = projectObj.getString("name");
        String webUrl = projectObj.getString("web_url");
        String userName = (String) gitMap.get("user_name");
        JSONArray commits = (JSONArray) gitMap.get("commits");
        JSONObject commitsObj = (JSONObject) commits.get(0);
        String message = commitsObj.getString("message");

        feiShuService.sendFeiShuGitMessage(webUrl, name, userName, message);
        return ajax;
    }
}
