package com.way.fact.service;

import com.way.fact.bean.Nav;
import com.way.fact.dao.NavDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author Administrator
 */
@Service
public class NavService   {

    @Autowired
    private NavDao navDao;

    private final static Logger log = LoggerFactory.getLogger(NavService.class);

    /**
     * 添加菜单
     * @param nav
     */
    public void addNav(Nav nav){
        nav.setTime(System.currentTimeMillis());
        navDao.save(nav);
    }

    /**
     * 修改
     */
    public void editNav(Nav nav){
        nav.setTime(System.currentTimeMillis());
        navDao.save(nav);
    }

    /**
     * 删除
     */
    public Optional<Nav> delNav(Integer id){
        Optional<Nav> list = navDao.findById(id);
        navDao.deleteById(id);
        return list;
    }

    /**
     * 查询所有
     * @return
     */
    public List<Nav> findALL(){
        return navDao.findAll();
    }

    /**
     * 查询一条
     * @param id
     * @return
     */
    public Optional<Nav> findOne(Integer id)  {
        return navDao.findById(id);
    }


    /**
     * 递归
     * @param parentId
     * @return
     */
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
