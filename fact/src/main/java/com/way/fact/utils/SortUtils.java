package com.way.fact.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

/**
 * 排序工具类
 * @author Administrator
 */
@Configuration
public class SortUtils {

    public static Sort basicSort(com.way.fact.bean.Sort... sorts){
        Sort result = null;
        for (com.way.fact.bean.Sort sort : sorts) {
            if (result == null) {
                result = new Sort(Sort.Direction.fromString(sort.getOrderType()), sort.getOrderField());
            } else {
                result = result.and(new Sort(Sort.Direction.fromString(sort.getOrderType()), sort.getOrderField()));
            }
        }
        
        return result;
    }

}
