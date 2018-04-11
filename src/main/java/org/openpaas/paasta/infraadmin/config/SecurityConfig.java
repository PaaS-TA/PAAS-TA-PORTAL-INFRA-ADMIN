package org.openpaas.paasta.infraadmin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private  static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Value("${spring.security.username}")
    String username;

    @Value("${spring.security.password}")
    String password;

    /**
     * Configure global.
     *
     * @throws Exception the exception
     */

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("USER : " + username + " PWD : " + password);
        auth.inMemoryAuthentication().withUser(username).password(password).roles("USER");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
        http.logout().logoutUrl("/logout");
        http.csrf().disable();
        //Spring boot Admin 정보 접근 URL - 시작
        http.authorizeRequests()
                .antMatchers("/info").permitAll()
                .antMatchers("/env").permitAll()
                .antMatchers("/metrics").permitAll()
                .antMatchers("/trace").permitAll()
                .antMatchers("/dump").permitAll()
                .antMatchers("/jolokia").permitAll()
                .antMatchers("/configprops").permitAll()
                .antMatchers("/logfile").permitAll()
                .antMatchers("/flyway").permitAll()
                .antMatchers("/liquibase").permitAll()
                .antMatchers("/heapdump").permitAll()
                .antMatchers("/loggers").permitAll()
                .antMatchers("/auditevents").permitAll()
                .antMatchers("/hystrix.stream").permitAll()
                .antMatchers("/docs").permitAll()
                .antMatchers("/jmx").permitAll()
                .antMatchers("/management/**").permitAll()
                .antMatchers("/api/applications/**").permitAll()
                .antMatchers("/health/**").permitAll()
                .antMatchers("/resources/**").permitAll();
        //Spring boot Admin 정보 접근 URL - 끝
        http.authorizeRequests().antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**").permitAll();
        http.authorizeRequests().antMatchers("/**").authenticated();
        http.httpBasic();
    }

}
