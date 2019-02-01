package me.lenycer.admin.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by a1100440 on 01/02/2019.
 */
@Data
@Entity
@Table(name = "USER")
public class User {

    @Id @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AdminStatus adminStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "REG_DATE", columnDefinition = "DATE DEFAULT SYSDATE")
    private Date regDate;
}
