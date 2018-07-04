package com.way.fact.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
@Entity
public class Nav implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String name;

    private Long time;

    @ColumnDefault(value = "0")
    private Integer sort;

    private Integer parentId = 0;

    private String images;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Transient
    private List<Nav> nav;

    public List<Nav> getNav() {
        return nav;
    }

    public void setNav(List<Nav> nav) {
        this.nav = nav;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column
    public Integer getParentId() {
        return parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}