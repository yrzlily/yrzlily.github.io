package com.way.fact.dao;

import com.way.fact.bean.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface TypeDao extends JpaRepository<Type,Integer> {



}
