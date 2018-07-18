package com.way.fact.bean;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 电话号码
     */
    @NotBlank(message = "电话号码不能为空")
    private String phone;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户状态
     */
    private Long status = 1L;

    @JsonIgnoreProperties(value = {"userInfo"})
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
