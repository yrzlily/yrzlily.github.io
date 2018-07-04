package com.way.fact.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户
 * @author Administrator
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;


    private Long status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole" , joinColumns = {@JoinColumn(name = "uid")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
