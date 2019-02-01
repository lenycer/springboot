package me.lenycer.admin.security;

import me.lenycer.admin.user.domain.AdminStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by a1100440 on 15/01/2019.
 */
@Component
public class AdminUserAuthenticationProvider implements AuthenticationProvider {

    final protected Logger logger = LoggerFactory.getLogger(AdminUserAuthenticationProvider.class);

    @Autowired
    AdminUserDetailsService adminUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getName();
        String password = (String) authentication.getCredentials();

        AdminUserDetails adminUserDetails = (AdminUserDetails) adminUserDetailsService.loadUserByUsername(username);

        logger.info("login user [{} : {} : {}]", adminUserDetails.getUsername(), adminUserDetails.getId(), adminUserDetails.getAdminStatus());

        if(!passwordEncoder.matches(password, adminUserDetails.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }

        if(adminUserDetails.getAdminStatus() == AdminStatus.STANDBY) {
            throw new BadCredentialsException("status is 'standby'");
        } else if(adminUserDetails.getAdminStatus() == AdminStatus.REJECT) {
            throw new BadCredentialsException("status is 'reject'");
        }

        adminUserDetails.addAuthority(new SimpleGrantedAuthority("USER"));
        return new UsernamePasswordAuthenticationToken(adminUserDetails, password, adminUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
