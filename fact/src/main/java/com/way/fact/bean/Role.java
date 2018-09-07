package com.way.fact.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 权限
 * @author yrz
 */
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 权限标识
     */
    @NotBlank
    @Column(name = "roles" , unique = true)
    private String roles;

    /**
     * 开启权限
     */
    @NotNull
    private Boolean available;

    /**
     * 权限名称
     */
    @NotBlank
    private String name;


    /**
     * 用户->角色
     */
    @JsonIgnoreProperties(value = {"roles"})
    @ManyToMany
    @JoinTable(name = "SysUserRole" , joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<User> userInfo;

    /**
     * 角色->权限
     * @return
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<Permission> permissions;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<User> userInfo) {
        this.userInfo = userInfo;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
