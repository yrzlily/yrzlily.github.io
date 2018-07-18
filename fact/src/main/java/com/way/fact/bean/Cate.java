package com.way.fact.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * 分类实体
 * @author Administrator
 */
@Entity
@Data
public class Cate {

    /**
     * ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 分类名称
     */
    @NotBlank
    private String name;

    /**
     * 上级分类
     */
    private Integer parentId;

    /**
     * 分类排序
     */
    private Integer sort;

}
