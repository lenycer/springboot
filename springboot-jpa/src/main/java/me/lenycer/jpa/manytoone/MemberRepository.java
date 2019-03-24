package me.lenycer.jpa.manytoone;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by a1100440 on 07/01/2019.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(value = "MemberEntity")
    @Override
    List<Member> findAll();
}
