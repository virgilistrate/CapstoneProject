package virgilistrate.CapstoneProject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import virgilistrate.CapstoneProject.entities.User;
import virgilistrate.CapstoneProject.services.UserService;

import java.io.IOException;

@Component
public class JWTCheckedFilter extends OncePerRequestFilter {

    private final JWTSecret jwtSecret;
    private final UserService userService;

    public JWTCheckedFilter(JWTSecret jwtSecret, UserService userService) {
        this.jwtSecret = jwtSecret;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authHeader.replace("Bearer ", "");
        jwtSecret.verifyToken(accessToken);

        long userId = jwtSecret.extractIdFromToken(accessToken);
        User userLogged = this.userService.getUserById(userId);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userLogged, null, userLogged.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return new AntPathMatcher().match("/auth/**", path)
                || new AntPathMatcher().match("/cars/**", path)
                || new AntPathMatcher().match("/vehicles/**", path)
                || new AntPathMatcher().match("/public/**", path);
    }
}