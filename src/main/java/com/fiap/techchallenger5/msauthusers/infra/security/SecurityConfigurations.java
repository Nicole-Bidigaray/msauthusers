package com.fiap.techchallenger5.msauthusers.infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())    
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(uriLiberadas()).permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/currentUser").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/auth/register/user").permitAll()
                    .requestMatchers(HttpMethod.GET,"/produtos/**" ).authenticated()
                    .requestMatchers(HttpMethod.POST,"/produtos/**" ).hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/produtos/**" ).hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/auth/register/admin").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/produtos/**" ).hasRole("ADMIN")
                    .requestMatchers( "/pagamentos/**", "/pedidos/**" ).hasRole("ADMIN")
                    
                    .requestMatchers(HttpMethod.POST, "/pagamentos/**" ).hasRole("USER")
                    .requestMatchers( "/carrinhos/**" ).hasRole("USER")
                    .anyRequest().authenticated()                        
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))    
                // .httpBasic(Customizer.withDefaults())      
                .build();
    }

    private String[] uriLiberadas(){
        return new String[]{
            "/swagger-mscarrinhos/**",
            "/swagger-msprodutos/**",
            "/swagger-mspedidos/**",
            "/swagger-mspagamentos/**",
            "/swagger-msauthusers/**",
            "/h2-console/**",
            "/auth/login",
            "/api-docs/**",  
            "/swagger-resources/**", 
            "/configuration/**", 
            "/webjars/**",
            "/v3/**",
            "/swagger-ui/**",
            "v3/api-docs/**"
        };
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
