package me.lenycer.jpa.onetomany;

import lombok.Data;
import me.lenycer.jpa.manytoone.Team;

import javax.persistence.*;

/**
 * Created by a1100440 on 07/01/2019.
 */
@Data
@Entity
public class Child {

    @Id @GeneratedValue
    @Column
    private Long id;

    private String name;

    private int age;

    @Column(name =  "PARENT_ID")
    private Long parentId;
}
