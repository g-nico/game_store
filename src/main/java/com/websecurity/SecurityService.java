package com.websecurity;

import com.model.frontObjects.LoginDto;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SecurityService {

    @Autowired
    private UserService userService;

    @Lazy
    @Bean
    public static LoggedUser getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (LoggedUser) auth.getPrincipal();
    }

    @Lazy
    @Bean
    private DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setForcePrincipalAsString(false);
        authProvider.setUserDetailsService(userService);
        return  authProvider;
    }

    @Lazy
    @Bean
    public String encodePassword(String passwd) {
        return this.encoder().encode(passwd);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(5);

    }

    public boolean login(final LoginDto loginDto) {
        if(validateLoginFields(loginDto)) {

            UserDetails userDetails = userService.loadUserByUsername(loginDto.getEmail());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, loginDto.getPassword(), userDetails.getAuthorities());

            this.daoAuthenticationProvider().authenticate(usernamePasswordAuthenticationToken);

            if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            return true;
        }
        return false;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) {
            SecurityContextLogoutHandler sch = new SecurityContextLogoutHandler();
            sch.setInvalidateHttpSession(true);
            sch.setClearAuthentication(true);
            sch.logout(request, response, auth);
        }
    }

    private boolean validateLoginFields(final LoginDto loginDto) {
        if (userService.getUserByEmail(loginDto.getEmail()) == null) {
            return false;
        }

        return (loginDto.getPassword().equals(userService.getUserByEmail(loginDto.getEmail()).getPassword()));
    }

    private boolean doPasswordsMatch(final String raw, final String encoded) {
        return encoder().matches(raw, encoded);
    }
}
