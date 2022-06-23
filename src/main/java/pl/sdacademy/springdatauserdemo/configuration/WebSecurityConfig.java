package pl.sdacademy.springdatauserdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/mvc/user/**").hasAnyRole("MVC_ROLE")
                .antMatchers("/user/**").hasAnyRole("REST_ROLE")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("mvcuser").password("$2a$10$LP5oK60gv4sQ6u9t4.g83.iuaDw.te5USkFF8vbCYpQevjYRaogTu").roles("MVC_ROLE")
               .and()
               .withUser("restuser").password("$2a$10$ZAZhlpC97/zTh304tKwTSeONvrtN9cKahiB7cuS8Tc.z5q92.i2J.").roles("REST_ROLE")
               .and()
               .withUser("admin").password("$2a$10$UEwkuqn6rdmWGZtpVzjsnufGGSpQyD6uVLKX.36Xv4wvVpzZuHCue").roles("MVC_ROLE", "REST_ROLE");
    }

    @Bean
    public PasswordEncoder noopPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Primary
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
