package com.swlab.webapp.config.handler;

import com.swlab.webapp.model.user.SecurityUser;
import com.swlab.webapp.model.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());

        if (accessDeniedException instanceof AccessDeniedException) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
                Set<UserRole.RoleType> roleTypes = securityUser.getRoleTypes();
                if (!roleTypes.isEmpty()) {
                    request.setAttribute("msg", "접근권한이 없는 사용자 입니다.");;
                    if (roleTypes.contains(UserRole.RoleType.ROLE_VIEW)) {
                        request.setAttribute("nextPage", "/home");
                    }
                }
            } else {
                request.setAttribute("msg", "로그인 권한이 없는 계정 입니다.");
                request.setAttribute("nextPage", "/login");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                SecurityContextHolder.clearContext();
            }
        } else {
            logger.info(accessDeniedException.getClass().getCanonicalName());
        }
        request.getRequestDispatcher("/err/denied").forward(request, response);
    }
}
