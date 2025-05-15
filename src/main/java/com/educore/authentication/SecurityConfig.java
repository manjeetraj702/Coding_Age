package com.educore.authentication;

import com.educore.exception.ApplicationException;
import com.educore.model.entity.Admin;
import com.educore.model.entity.Employee;
import com.educore.model.entity.Student;
import com.educore.model.entity.User;
import com.educore.model.enums.Role;
import com.educore.service.AdminService;
import com.educore.service.EmployeeService;
import com.educore.service.StudentService;
import com.educore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AdminService adminService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer :: disable)
                .authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/auth/**", "/mail/**", "/swagger-ui/**", "/v3/api-docs/**")
                .permitAll()
                        // for restrict api access for only admin access
                        .requestMatchers("/admin-signup").hasAuthority("ADMIN")
                        .requestMatchers("/employee-signup").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {

            User user = userService.fetchByPhoneNumber(username);
            if (user == null) {
                throw new ApplicationException("User not found");
            }
            // Based on the role, load either Student or Employee
            if (user.getRole() == Role.STUDENT) {
                return studentService.findByUser(user);
            } else if (user.getRole() == Role.EMPLOYEE) {
                return employeeService.findByUser(user);
            }else if(user.getRole() == Role.ADMIN){
                return adminService.findByUser(user);
            }
            throw new UsernameNotFoundException("User not found");
        };
    }
}

