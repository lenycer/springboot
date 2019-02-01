package me.lenycer.admin.user.repository;

import me.lenycer.admin.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by a1100440 on 01/02/2019.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginId(String loginId);
}
