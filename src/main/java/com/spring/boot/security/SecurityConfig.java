package com.spring.boot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.spring.boot.user.UserSecurityService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	//������ Security�� UserSecurityService�� ���
	private final UserSecurityService userSecurityService;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {	
        
		// ���ѿ� ���� ����ϴ� url ����
		
        // /login, /signup �������� ��� ���, �ٸ� �������� ������ ����ڸ� ���
        http
            .authorizeRequests()
                .antMatchers("/**").permitAll();

		// login ����
        http
            .formLogin()
                .loginPage("/user/login")    // GET ��û (login form�� ������)
                .loginProcessingUrl("/auth")    // POST ��û (login â�� �Է��� �����͸� ó��)
                .usernameParameter("email")	// login�� �ʿ��� id ���� email�� ���� (default�� username)
                .passwordParameter("password")	// login�� �ʿ��� password ���� password(default)�� ����
                .defaultSuccessUrl("/");	// login�� �����ϸ� /�� redirect

		// logout ����
        http
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");	// logout�� �����ϸ� /�� redirect

        return http.build();
    }
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {	
//		
//		http
//			.authorizeRequests().antMatchers("/**").permitAll() //� url���� �α��� ���� ���� �����ϵ��� ��
//			.and()
//			.formLogin().loginPage("/user/login").defaultSuccessUrl("/") //login���������� �α��� �����ϸ� root�� ���� ��
//			.and()
//			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //logout�ּҰ� ������ logout��Ŵ
//			.logoutSuccessUrl("/").invalidateHttpSession(true); //logout�Ǹ� ����ȭ������ ���������� ������ invalidate��
//		
//		return http.build();
//	}
	
	//passwordEncoder �����ϸ� BCrypt ��ȣȭ ��ü�� ��ȯ
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		//AuthenticationManager�� �������� security ������ ���
		//AuthenticationManager Bean������ �������� ���� �������� ������ �ۼ��� UserSecurityService�� PasswordEncoder�� �ڵ����� ������
		
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
}
