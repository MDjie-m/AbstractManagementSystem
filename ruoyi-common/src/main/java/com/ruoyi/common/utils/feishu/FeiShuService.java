package com.ruoyi.common.utils.feishu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class FeiShuService {

    private static final String FEISHU_WEBHOOK_URL = "https://open.feishu.cn/open-apis/bot/v2/hook/28000449-ad5e-4b54-b407-f0524a4be78b";
    private static final Logger log = LoggerFactory.getLogger(FeiShuService.class);

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public FeiShuService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

//    public static void main(String[] args) {
//        FeiShuService feiShuService = new FeiShuService();
//        feiShuService.sendFeiShuGitMessage("https://gitcode.com/Fate__XiaoZuo/wan_yong_quan_neng_bao", "万用全能宝", "Fate__XiaoZuo");
//    }

    public void sendFeiShuGitMessage(String gitLink, String gitName, String changeName, String commits) {

        Map<String, Object> contentElement1 = new HashMap<>();
        contentElement1.put("tag", "text");
        contentElement1.put("text", "提交信息: " + commits);

        Map<String, Object> contentElement2 = new HashMap<>();
        contentElement2.put("tag", "text");
        contentElement2.put("text", "目标仓库: " + gitName);

        Map<String, Object> linkElement = new HashMap<>();
        linkElement.put("tag", "a");
        linkElement.put("text", "点击这里");
        linkElement.put("href", gitLink);

        Map<String, Object> contentElement3 = new HashMap<>();
        contentElement3.put("tag", "text");
        contentElement3.put("text", "查看项目: ");
        List<Map<String, Object>> row1 = Arrays.asList(contentElement3, linkElement);

        List<List<Map<String, Object>>> contentRows = Arrays.asList(
                Collections.singletonList(contentElement1),
                Collections.singletonList(contentElement2),
                row1
        );

        Map<String, Object> zhCnContent = new HashMap<>();
        zhCnContent.put("title", String.format("%s 推送了分支", changeName));
        zhCnContent.put("content", contentRows);

        Map<String, Object> postContent = new HashMap<>();
        postContent.put("zh_cn", zhCnContent);

        Map<String, Object> content = new HashMap<>();
        content.put("post", postContent);

        Map<String, Object> payload = new HashMap<>();
        payload.put("msg_type", "post");
        payload.put("content", content);

        // 将Map转换为JSON字符串
        String jsonPayload;
        try {
            jsonPayload = objectMapper.writeValueAsString(payload);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert map to JSON", e);
        }

        // 发送POST请求
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<String> response = restTemplate.exchange(FEISHU_WEBHOOK_URL, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("消息发送成功！");
        } else {
            log.info("消息发送失败！响应: {}", response.getBody());
        }
    }
}