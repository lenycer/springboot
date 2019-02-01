package me.lenycer.admin.security;

import me.lenycer.admin.user.domain.User;
import me.lenycer.admin.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by a1100440 on 01/02/2019.
 */
@Component
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username);

        if(user == null) {
            throw new UsernameNotFoundException(username + " is not found");
        }

        AdminUserDetails adminUserDetails = new AdminUserDetails();

        adminUserDetails.setUsername(user.getLoginId());
        adminUserDetails.setPassword(user.getPassword());
        adminUserDetails.setName(user.getName());
        adminUserDetails.setId(user.getId());
        adminUserDetails.setAdminStatus(user.getAdminStatus());

        return adminUserDetails;
    }
}
