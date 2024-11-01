package com.renxin.framework.interceptor;

import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.Enumeration;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.framework.web.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.alibaba.fastjson2.JSON;
import com.renxin.common.annotation.RepeatSubmit;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.utils.ServletUtils;

/**
 * 防止重复提交拦截器
 *
 * @author renxin
 */
@Component
@Slf4j
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor
{
    @Resource
    private ConsultantTokenService consultantTokenService;
    @Resource
    private PocketTokenService pocketTokenService;
    
    //接口请求执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null)
            {
                if (this.isRepeatSubmit(request, annotation))
                {
                    AjaxResult ajaxResult = AjaxResult.error(annotation.message());
                    ServletUtils.renderString(response, JSON.toJSONString(ajaxResult));
                    return false;
                }
            }
            return true;
        }
        else
        {
            return true;
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);

    //接口请求执行后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        // 若接口执行出现异常, 则打印该次请求的入参信息
        if (response.getStatus() == 500) {
            StringBuffer detail = new StringBuffer();
            
            //打印请求发起人
            Long consultId = consultantTokenService.getConsultId(request);
            Long userId = pocketTokenService.getUserId(request);
            if (consultId != -1){ detail.append("咨询师consultantId: " + consultId); }
            if (userId != -1){ detail.append("来访者userId: " + userId); }
            
            // 打印URL
            String url = request.getRequestURL().toString();
            detail.append("调用接口: " + url + "出现了异常. ");
            
            // 打印入参
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                detail.append(" Request Parameter - " + paramName + ": " + paramValue + ";");
            }

            // 打印请求体
            StringBuilder requestBody = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line).append("\n");
            }
            detail.append(" Request Body: " + requestBody.toString());
            log.error(detail.toString());
        }
    }
}

