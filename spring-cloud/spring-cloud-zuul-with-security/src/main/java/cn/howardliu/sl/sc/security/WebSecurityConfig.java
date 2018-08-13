package cn.howardliu.sl.sc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;

/**
 * <br>created at 18-4-14
 *
 * @author liuxh
 * @since 0.1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private FilterSecurityInterceptor filterSecurityInterceptor;
    @Autowired
    private DataSource dataSource;

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write("{\"code\":\"403\",\"msg\":\"权限不足，请联系管理员!\"}");
            out.flush();
            out.close();
        };
    }

    @Bean
    @Primary
    public DefaultWebInvocationPrivilegeEvaluator customWebInvocationPrivilegeEvaluator() {
        return new DefaultWebInvocationPrivilegeEvaluator(filterSecurityInterceptor);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .securityInterceptor(filterSecurityInterceptor)
                .privilegeEvaluator(customWebInvocationPrivilegeEvaluator())
                .ignoring()
                .antMatchers("/static/**", "/resource/**", "/3rd/**")
                .antMatchers("/**/*.css", "/**/*.js")
                .antMatchers("/**/*.png", "/**/*.jpg", "/**/*.ico")
        ;
    }

    @Bean
    public PersistentTokenRepository jdbcTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login-error.html")
                .loginProcessingUrl("/login.html")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout.html")
                .logoutSuccessUrl("/login.html?logout")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(604800)
                .tokenRepository(jdbcTokenRepository())
                .userDetailsService(userDetailsService)
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
        ;
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
