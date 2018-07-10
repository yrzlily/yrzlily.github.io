package com.way.fact.bean;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
@Entity
public class Goods implements Serializable {

    private static final long serialVersionUID = -3946734305303957850L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties("goodsList")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "goods_type",joinColumns = {@JoinColumn(name = "goods_id")} , inverseJoinColumns = {@JoinColumn(name = "type_id")})
    private List<Type> typeList ;

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

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }
}
