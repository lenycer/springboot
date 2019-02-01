package me.lenycer.admin.user.service;

import me.lenycer.admin.user.domain.AdminStatus;
import me.lenycer.admin.user.domain.User;
import me.lenycer.admin.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * Created by a1100440 on 01/02/2019.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    @Transactional
    public User createUser(User user) {
        user.setAdminStatus(AdminStatus.STANDBY);
        user.setRegDate(new Date());

        return userRepository.save(user);
    }

    @Transactional
    public boolean changeUserStatus(Long userId, AdminStatus adminStatus) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(u -> {
            u.setAdminStatus(adminStatus);
        });
        return true;
    }
}
