package r2.llc.communal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import r2.llc.communal.config.security.CustomSecurityConfigurer;
import r2.llc.communal.config.security.CustomSecurityProvider;
import r2.llc.communal.config.security.SecurityAccessDeniedHandler;
import r2.llc.communal.config.security.SecurityAuthenticationEntryPoint;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;


@Configuration
@EnableWebSecurity
@Order(1)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessConfig accessConfig;
    private final CustomSecurityProvider securityProvider;

    private static final String LOGIN_ENDPOINT = "/api/v1/user/auth";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .exceptionHandling()
                .accessDeniedHandler(new SecurityAccessDeniedHandler())
                .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(LOGIN_ENDPOINT).permitAll();

        for (AccessConfig.Access access : accessConfig.getAccess()) {
            for (AccessConfig.Permit permit : access.getPermit()) {
                String[] roles = Arrays.stream(permit.getRoles()).map(role -> "ROLE_" + role).toArray(String[]::new);
                http.authorizeRequests().antMatchers(permit.toHttpMethod(), access.getUrl()).hasAnyAuthority(roles);
            }
        }

        http
                .authorizeRequests()
                .anyRequest().hasAnyAuthority("ROLE_ADMIN")
                .and()
                .apply(new CustomSecurityConfigurer(securityProvider));
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/v3/api-docs",
                "swagger-ui/index.html",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**"
        );
    }
}
