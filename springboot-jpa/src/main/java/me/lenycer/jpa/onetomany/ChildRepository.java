package me.lenycer.jpa.onetomany;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by a1100440 on 07/01/2019.
 */
public interface ChildRepository extends JpaRepository<Child, Long> {
}
