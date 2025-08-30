package com.leinner.springboot.vital_care.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RedirecciónUsuario implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "/";
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            String role = auth.getAuthority();
            if ("ROLE_ADMIN".equals(role)) {
                redirectUrl = "/administrador";
                break;
            } else if ("ROLE_MEDICO".equals(role)) {
                redirectUrl = "/medico";
                break;
            } else if ("ROLE_PACIENTE".equals(role)) {
                redirectUrl = "/paciente";
                break;
            }
        }
        response.sendRedirect(redirectUrl);
    }
}
