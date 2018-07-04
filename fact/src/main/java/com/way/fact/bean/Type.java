package com.way.fact.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Entity
public class Type {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @JsonIgnoreProperties("typeList")
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER,mappedBy = "typeList")
    private List<Goods> goodsList;

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

}
