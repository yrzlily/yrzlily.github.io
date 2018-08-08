package com.way.fact.bean;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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

    /**
     * 产品名称
     */
    private String name;

    /**
     * 封面图
     */
    private String images;

    /**
     * 产品排序
     */
    private Integer sort;

    /**
     * 产品描述
     */
    private String Description;

    /**
     * 修改时间
     */
    private Timestamp update_time;

    /**
     * 库存
     */
    private Integer num;

    /**
     * 产品状态
     * 0=上架
     * 1=下架
     */
    private String status;

    /**
     * 产品价格
     */
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private GoodsContent content;

//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    @JsonIgnoreProperties("goodsList")
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "goods_type",joinColumns = {@JoinColumn(name = "goods_id")} , inverseJoinColumns = {@JoinColumn(name = "type_id")})
//    private List<Type> typeList ;

    public GoodsContent getContent() {
        return content;
    }

    public void setContent(GoodsContent content) {
        this.content = content;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

}
