package com.way.fact.service.impl;

import com.way.fact.bean.goods.GoodsAttr;
import com.way.fact.bean.type.TypeAttr;
import com.way.fact.dao.TypeAttrDao;
import com.way.fact.service.TypeAttrService;
import com.way.fact.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 分类属性服务层
 * @author yrz
 */
@Service
public class TypeAttrServiceImpl implements TypeAttrService {

    @Autowired
    private TypeAttrDao typeAttrDao;

    @Override
    public Page<TypeAttr> findAll(
            Pageable pageable,
            Integer tid
    ) {
        return typeAttrDao.findAll(new Specification<TypeAttr>() {

            @Override
            public Specification<TypeAttr> or(Specification<TypeAttr> other) {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<TypeAttr> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicate = new ArrayList<>();

                if(tid!=null){
                    predicate.add(criteriaBuilder.equal(root.get("tid").as(Integer.class) , tid));
                }

                Predicate[] predicates = new Predicate[predicate.size()];
                criteriaQuery.where(criteriaBuilder.and(predicate.toArray(predicates)));
                return criteriaQuery.getRestriction();
            }
        } , pageable);
    }

    /**
     * 相关产品对应规格
     * @param typeAttrList
     * @param goodsAttrList
     * @return
     */
    @Override
    public List<TypeAttr> findSpecifications(List<TypeAttr> typeAttrList, List<GoodsAttr> goodsAttrList) {


        for (TypeAttr typeAttr: typeAttrList){
            for (int i = 0; i< goodsAttrList.size() ; i++){
                if(!goodsAttrList.get(i).getGoodsAttrTid().equals(typeAttr.getId())){
                    goodsAttrList.remove(goodsAttrList.get(i));
                }
            }
            typeAttr.setGoodsAttrList(goodsAttrList);
        }

        return typeAttrList;
    }
}
