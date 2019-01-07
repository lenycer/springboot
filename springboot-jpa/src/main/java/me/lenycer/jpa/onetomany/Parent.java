package me.lenycer.jpa.onetomany;

import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by a1100440 on 07/01/2019.
 */
@Data
@Entity
public class Parent {

    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    @Column(name = "PARENT_NAME")
    private String name;

    @OneToMany
    @JoinColumn(name = "PARENT_ID")
    @Fetch(FetchMode.SUBSELECT)
    private List<Child> chlild;
}
