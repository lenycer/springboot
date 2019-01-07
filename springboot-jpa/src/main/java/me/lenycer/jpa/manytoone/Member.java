package me.lenycer.jpa.manytoone;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by a1100440 on 07/01/2019.
 */
@Data
@Entity
public class Member {

    @Id @GeneratedValue
    @Column
    private Long id;

    private String name;

    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
