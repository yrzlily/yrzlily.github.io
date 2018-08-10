package com.way.fact.bean.goods;

import com.way.fact.bean.type.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author yrz
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
     * 对应分类
     */
    @Column(name="cate")
    private Integer cats;

    /**
     * 对应分类信息
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cate"  , insertable = false , updatable = false)
    private Type type;

    /**
     * 产品价格
     */
    private String price;

    /**
     * 产品详情
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private GoodsContent content;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsAttrGid")
    private List<GoodsAttr> goodsAttrList;

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


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public Integer getCats() {
        return cats;
    }

    public void setCats(Integer cats) {
        this.cats = cats;
    }

    public List<GoodsAttr> getGoodsAttrList() {
        return goodsAttrList;
    }

    public void setGoodsAttrList(List<GoodsAttr> goodsAttrList) {
        this.goodsAttrList = goodsAttrList;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
