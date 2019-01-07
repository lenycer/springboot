package me.lenycer.jpa.nquery;

import me.lenycer.jpa.manytoone.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by a1100440 on 07/01/2019.
 */
public interface NativeRepository extends JpaRepository<Member, Long> {

    @Query(value = "select m.name memberName, t.team_name teamName from member m, team t " +
            "where m.team_id = t.team_id " +
            "and t.team_id = ?1", nativeQuery = true)
    List<NativeQueryDTO> nativeQuery(Long teamId);
}
