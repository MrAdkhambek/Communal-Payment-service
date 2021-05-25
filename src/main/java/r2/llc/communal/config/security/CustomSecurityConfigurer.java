package r2.llc.communal.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class CustomSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final CustomSecurityProvider securityProvider;

    public CustomSecurityConfigurer(CustomSecurityProvider securityProvider) {
        this.securityProvider = securityProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        CustomSecurityFilter securityFilter = new CustomSecurityFilter(this.securityProvider);
        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
