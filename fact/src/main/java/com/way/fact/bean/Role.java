package com.way.fact.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 权限
 * @author yrz
 */
@Entity
@Data
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

}
