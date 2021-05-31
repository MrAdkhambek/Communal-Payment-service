package r2.llc.communal.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import r2.llc.communal.config.SecurityConfig;
import r2.llc.communal.util.ErrorAPI;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;


@Slf4j
public class CustomSecurityFilter extends OncePerRequestFilter {

    private final CustomSecurityProvider securityProvider;

    public CustomSecurityFilter(CustomSecurityProvider securityProvider) {
        this.securityProvider = securityProvider;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String URI = request.getRequestURI();
        String token = this.securityProvider.resolveToken(request);

        try {
            if (!Arrays.asList(SecurityConfig.OPEN_APIs).contains(URI) && !URI.startsWith("/actuator")) {
                Authentication auth = this.securityProvider.getAuthentication(requireNonNull(token));
                SecurityContextHolder.getContext().setAuthentication(requireNonNull(auth));
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.setStatus(401);
            OutputStream out = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, new ErrorAPI(HttpStatus.UNAUTHORIZED.getReasonPhrase(), 401));
            out.flush();

            log.error("UNAUTHORIZED ERROR {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
