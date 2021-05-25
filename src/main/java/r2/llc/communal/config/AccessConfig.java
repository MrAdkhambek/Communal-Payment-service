package r2.llc.communal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "permission")
public class AccessConfig {

    private Role[] roles;
    private Access[] access;

    @Data
    public static class Role {
        private String name;
    }

    @Data
    public static class Permit {
        private String method;
        private String[] roles;

        public HttpMethod toHttpMethod() {
            return HttpMethod.valueOf(method);
        }
    }

    @Data
    public static class Access {
        private String url;
        private Permit[] permit;
    }
}
