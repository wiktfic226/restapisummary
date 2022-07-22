package pl.fis.restapisummary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import pl.fis.restapisummary.security.UserPermission;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/api/decimal")
                .hasAnyAuthority(UserPermission.DECIMAL_WRITE.toString(), UserPermission.ACCESS_ALL.toString())
                .antMatchers(HttpMethod.PUT, "/api/multiplier")
                .hasAnyAuthority(UserPermission.MULTIPLIER_WRITE.toString(), UserPermission.ACCESS_ALL.toString())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
}
