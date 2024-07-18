package com.renxin.framework.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.common.exception.GlobalException;
import com.renxin.framework.web.service.ConsultantTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.renxin.common.core.domain.model.LoginUser;
import com.renxin.common.utils.SecurityUtils;
import com.renxin.common.utils.StringUtils;
import com.renxin.framework.web.service.TokenService;

/**
 * token过滤器 验证token有效性
 * 
 * @author renxin
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Value("${token.header}")
    private String adminHeader;

    @Value("${consultant.token.header}")
    private String consultantHeader;

    @Value("${pocket.token.header}")
    private String pocketHeader;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ConsultantTokenService consultantTokenService;

    @Autowired
    private PocketTokenService pocketTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        //请求urL
        String requestURI = request.getRequestURI();
        
        // 检查是否为管理用户，并进行身份验证
        String adminHeaderData = request.getHeader(adminHeader);
        if(StringUtils.isNotEmpty(adminHeaderData)){
            // 获取当前登录的管理用户
            LoginUser loginUser = tokenService.getLoginUser(request);
            // 当普通用户存在且当前认证为空时，进行身份验证
            if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
            {
                // 验证用户令牌
                tokenService.verifyToken(loginUser);
                // 创建并设置认证令牌
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        
        // 检查是否为咨询师用户，并进行身份验证
        String consultantHeaderData = request.getHeader(consultantHeader);
        if(StringUtils.isNotEmpty(consultantHeaderData)){
            // 获取当前登录的咨询用户
            ConsultDTO consultUser = consultantTokenService.getLoginUser(request);
            // 当咨询用户存在且当前认证为空时，进行身份验证
            if (StringUtils.isNotNull(consultUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
            {
                // 验证用户令牌
                consultantTokenService.verifyToken(consultUser);
                // 创建并设置认证令牌
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(consultUser, null, consultUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            if (!requestURI.startsWith("/consultant/")) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                throw new GlobalException("咨询师用户, 不可访问其他服务端的接口");
            }
        }
        
        
        //检查是否为"来访者"用户
        String pocketHeaderData = request.getHeader(pocketHeader);
        if(StringUtils.isNotEmpty(pocketHeaderData)){
            // 获取当前登录的咨询用户
            LoginDTO pocketUser = pocketTokenService.getLoginUser(request);
            // 当咨询用户存在且当前认证为空时，进行身份验证
            if (StringUtils.isNotNull(pocketUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
            {
                // 验证用户令牌
                pocketTokenService.verifyToken(pocketUser);
                // 创建并设置认证令牌
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(pocketUser, null, pocketUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            if (!requestURI.startsWith("/pocket/")) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                throw new GlobalException("来访者用户, 不可访问其他服务端的接口");
            }
        }
        
        chain.doFilter(request, response);
    }
}
