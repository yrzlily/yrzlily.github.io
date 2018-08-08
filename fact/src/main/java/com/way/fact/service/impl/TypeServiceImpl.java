package com.way.fact.service.impl;

import com.way.fact.bean.Nav;
import com.way.fact.bean.Type;
import com.way.fact.bean.User;
import com.way.fact.dao.TypeDao;
import com.way.fact.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类逻辑层
 * @author Administrator
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    /**
     * 分页
     * @param pageable
     * @param search
     * @param pid
     * @return
     */
    @Override
    public Page<Type> findAll(Pageable pageable, String search , Integer pid) {
        return typeDao.findAll(new Specification<Type>() {

            @Override
            public Specification<Type> or(Specification<Type> other) {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<Type> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicate = new ArrayList<>();

                //名称检索
                if(search != null){
                    predicate.add(criteriaBuilder.like(root.get("typeName").as(String.class) ,"%" + search + "%"));
                }

                //父类检索
                if(pid != null){
                    predicate.add(criteriaBuilder.equal(root.get("parentID").as(Integer.class) , pid));
                }
                Predicate[] predicates = new Predicate[predicate.size()];
                criteriaQuery.where(criteriaBuilder.and(predicate.toArray(predicates)));
                return criteriaQuery.getRestriction();
            }
        } , pageable);
    }

    /**
     * 父类递归
     * @param id
     * @return
     */
    @Override
    public Type parent(Integer id) {

        Type child = typeDao.findById(id).get();
        Type fin;
        if(child.getParentID()!=0){
            Type parent = typeDao.findById(child.getParentID()).get();
            child.setTypes(parent);
            fin = child;
        }else{
            fin = child;
        }

        return fin;
    }
}
