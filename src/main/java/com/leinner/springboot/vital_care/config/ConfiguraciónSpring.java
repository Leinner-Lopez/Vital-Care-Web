package com.leinner.springboot.vital_care.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguraciónSpring {

    @Autowired
    RedirecciónUsuario redirecciónUsuario;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/", "/Registro", "/css/**","/imagenes/**").permitAll()
                                .requestMatchers("/paciente/**").hasAuthority("ROLE_PACIENTE")
                                .requestMatchers("/medico/**").hasAuthority("ROLE_MEDICO")
                                .requestMatchers("/administrador/**").hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                                .loginPage("/") // tu login personalizado
                                .loginProcessingUrl("/login")
                                .successHandler(redirecciónUsuario) // puedes personalizar la redirección según el rol
                                .permitAll()
                )
                .logout(logout -> logout
                                .logoutSuccessUrl("/")
                                .permitAll()
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    } 
}
