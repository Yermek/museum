package com.businessproj.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kseniya
 */

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "nameUser")
    private String nameUser;

    @Column(unique = true, name = "emailUser")
    private String emailUser;

    @Column(name = "passUser")
    private String passUser;

    @Column(name = "accBalanceUser")
    private float accBalanceUser;

    @ManyToOne
    @JoinColumn
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public float getAccBalanceUser() {
        return accBalanceUser;
    }

    public void setAccBalanceUser(float accBalanceUser) {
        this.accBalanceUser = accBalanceUser;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
