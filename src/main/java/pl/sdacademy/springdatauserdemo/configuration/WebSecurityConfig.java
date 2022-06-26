package pl.sdacademy.springdatauserdemo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.sdacademy.springdatauserdemo.model.User;
import pl.sdacademy.springdatauserdemo.web.filters.JwtAuthorizationFilter;

import static pl.sdacademy.springdatauserdemo.model.User.Role.*;

@Configuration
@Profile("!integration-tests")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtAuthorizationFilter authorizationFilter;

    public WebSecurityConfig(@Qualifier("authUserDetailsService") UserDetailsService userDetailsService, JwtAuthorizationFilter authorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.authorizationFilter = authorizationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/mvc/user/**").hasAnyAuthority(MVC_ROLE.name(), ADMIN.name())
                .antMatchers("/user/**").hasAnyAuthority(REST_ROLE.name(), ADMIN.name())
                .antMatchers("/auth/**").permitAll()
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
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication()
//               .withUser("mvcuser").password("$2a$10$LP5oK60gv4sQ6u9t4.g83.iuaDw.te5USkFF8vbCYpQevjYRaogTu").roles("MVC_ROLE")
//               .and()
//               .withUser("restuser").password("$2a$10$ZAZhlpC97/zTh304tKwTSeONvrtN9cKahiB7cuS8Tc.z5q92.i2J.").roles("REST_ROLE")
//               .and()
//               .withUser("admin").password("$2a$10$UEwkuqn6rdmWGZtpVzjsnufGGSpQyD6uVLKX.36Xv4wvVpzZuHCue").roles("MVC_ROLE", "REST_ROLE");
//    }


    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

//    @Bean
//    public PasswordEncoder noopPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    @Primary
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
