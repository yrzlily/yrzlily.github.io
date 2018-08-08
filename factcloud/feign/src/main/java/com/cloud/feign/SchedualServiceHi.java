package com.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 服务调用接口
 * @author Administrator
 */
@FeignClient(value = "service-hi" , fallback = SchedualServiceHiImpl.class)
public interface SchedualServiceHi {

    /**
     * 测试接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/hi",method = RequestMethod.GET )
    String sayHay(@RequestParam(value = "name")String name);
}
