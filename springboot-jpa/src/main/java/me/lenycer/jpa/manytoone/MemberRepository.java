package me.lenycer.jpa.manytoone;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by a1100440 on 07/01/2019.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
