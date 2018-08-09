package com.way.fact.service.impl;

import com.way.fact.bean.Nav;
import com.way.fact.dao.NavDao;
import com.way.fact.service.NavService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;


/**
 * @author yrz
 */
@Service
public class NavServiceImpl implements NavService {

    @Autowired
    private NavDao navDao;

    private final static Logger log = LoggerFactory.getLogger(NavServiceImpl.class);

    /**
     * 添加菜单
     * @param nav
     */
    @Override
    public void addNav(Nav nav){
        nav.setTime(System.currentTimeMillis());
        navDao.save(nav);
    }

    /**
     * 修改
     */
    @Override
    public void editNav(Nav nav){
        nav.setTime(System.currentTimeMillis());
        navDao.save(nav);
    }

    /**
     * 删除
     */
    @Override
    public Optional<Nav> delNav(Integer id){
        Optional<Nav> list = navDao.findById(id);
        navDao.deleteById(id);
        return list;
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Nav> findALL(){
        return navDao.findAll();
    }

    /**
     * 查询一条
     * @param id
     * @return
     */
    @Override
    public Optional<Nav> findOne(Integer id)  {
        return navDao.findById(id);
    }


    /**
     * 递归父节点寻找子节点
     * @param parentId
     * @return
     */
    @Override
    public List<Nav> findAllByParentId(List<Nav> nav , Integer parentId){
        List<Nav> list = new ArrayList<>();
        for (Nav lists:nav){
            if(lists.getParentId().equals(parentId)){
                List<Nav> child = findAllByParentId(nav,lists.getId());
                lists.setNav(child);
                list.add(lists);
            }
        }
        return list;
    }

    /**
     * 递归子节点寻找父节点
     * @return
     */
    @Override
    public Nav findAllByChild(Nav nav )  {

        Nav parent = null;
        if(nav.getParentId() != 0){
            parent = navDao.findById(nav.getParentId()).get();
            nav.setSelf(parent);
            findAllByChild(parent);
        }

       return nav;
    }
}
