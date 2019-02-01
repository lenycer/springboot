package me.lenycer.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by a1100440 on 14/01/2019.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminUserAuthenticationProvider adminUserAuthenticationProvider;
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //allowed
//        web.ignoring().antMatchers("/swagger-ui.html",
//                "/webjars/**",
//                "/v2/api-docs/**",
//                "/swagger-resources/**",
//                "/css/**",
//                "/js/**",
//                "/font/**",
//                "/images/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/error**").permitAll()
//                .antMatchers("/web/login/**",     //아이디 찾기, 비밀번호 찾기 화면
//                        "/web/join/**",    //회원 등록 화면
//                        "/members",     //회원 목록 조회 화면, 회원 api
//                        "/join/**",     //회원 가입 시 api
//                        "/terms/**",     //약관 api
//                        "/user/**",     //user api
//                        "/",            //index page
//                        "/thirdparties").permitAll()
//                .antMatchers("/re-terms").hasAuthority("NEED_TERMS")
//                .antMatchers("/web/re-terms").hasAuthority("NEED_TERMS")
//                .antMatchers("/sleep").hasAuthority("SLEEP")
//                .antMatchers("/web/sleep").hasAuthority("SLEEP")
//                .anyRequest().hasAuthority("USER");
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login/login.html")
                .loginProcessingUrl("/user/logon")
                .defaultSuccessUrl("/")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                .usernameParameter("loginId")
                .passwordParameter("password")
                .permitAll();

        http.logout()
                .logoutUrl("/user/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login/login.html")
                .permitAll();

        http.sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(adminUserAuthenticationProvider);
    }
}
