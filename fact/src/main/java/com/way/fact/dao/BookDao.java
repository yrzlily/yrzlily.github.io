package com.way.fact.dao;

import com.way.fact.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface BookDao extends JpaRepository<Book,Integer> {


}
