package me.lenycer.jpa.manytoone;

import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by a1100440 on 07/01/2019.
 */
@Data
@Entity
//@BatchSize(size = 5) // team 정보 필요 시 select in 으로 조회 n+1 처리.
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "TEAM_NAME")
    private String name;
}
