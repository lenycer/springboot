package me.lenycer.jpa.simple;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by a1100440 on 07/01/2019.
 */
@Data
@Entity
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    private String name;

    private String location;
}
