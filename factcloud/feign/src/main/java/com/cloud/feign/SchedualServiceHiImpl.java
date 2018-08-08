package com.cloud.feign;

import org.springframework.stereotype.Service;

/**
 * 测试服务层
 * @author Administrator
 */
@Service
public class SchedualServiceHiImpl implements SchedualServiceHi{

    @Override
    public String sayHay(String name) {
        return "sorry:" + name;
    }
}
