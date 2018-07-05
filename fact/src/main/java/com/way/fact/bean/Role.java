package com.way.fact.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 权限
 * @author Administrator
 */
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String role;

    private Boolean available = Boolean.FALSE;

    /**
     * 用户->角色
     */
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<User> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<User> userInfo) {
        this.userInfo = userInfo;
    }
}
