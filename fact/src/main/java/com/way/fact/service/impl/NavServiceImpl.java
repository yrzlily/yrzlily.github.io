package com.way.fact.service.impl;

import com.way.fact.bean.Nav;
import com.way.fact.dao.NavDao;
import com.way.fact.service.NavService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author Administrator
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
     * 递归
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




}
