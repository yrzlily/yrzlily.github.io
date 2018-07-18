package com.way.fact.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    /**
     * 导航名称
     */
    @NotEmpty(message = "名称不能为空")
    private String name;

    /**
     * 更新时间
     */
    private Long time;

    /**
     * 默认排序
     */
    @ColumnDefault(value = "0")
    @Column(columnDefinition = "INT default 0")
    private Integer sort;

    /**
     * 父节点
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 图片
     */
    private String images;

    /**
     * 链接
     */
    private String url;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Transient
    private Nav self;

    public Nav getSelf() {
        return self;
    }

    public void setSelf(Nav self) {
        this.self = self;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
