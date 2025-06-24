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
public class RedirecciónUsuario implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "/";
        for(GrantedAuthority auth : authentication.getAuthorities()){
            String role = auth.getAuthority();
            if(role.equals("ROLE_ADMIN")){
                redirectUrl = "/administrador";
                break;
            }else if(role.equals("ROLE_MEDICO")){
                redirectUrl = "/medico";
                break;
            }else if(role.equals("ROLE_PACIENTE")){
                redirectUrl = "/paciente";
                break;
            }
        }
        response.sendRedirect(redirectUrl);   
    }   
}
