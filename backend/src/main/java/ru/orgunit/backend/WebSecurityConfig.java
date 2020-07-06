package ru.orgunit.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // todo Сделать нормально.
        http.csrf().disable();

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/*").permitAll();
/*        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/private/*").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/*").authenticated();*/
        /*http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();*/

        /*http
                .authorizeRequests()
                .antMatchers("/", "/api/*").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();*/
    }


    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/
}
