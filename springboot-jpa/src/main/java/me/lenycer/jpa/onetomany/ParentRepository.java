package me.lenycer.jpa.onetomany;

import me.lenycer.jpa.manytoone.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by a1100440 on 07/01/2019.
 */
public interface ParentRepository extends JpaRepository<Parent, Long> {
}
