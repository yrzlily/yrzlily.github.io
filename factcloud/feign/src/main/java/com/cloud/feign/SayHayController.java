package com.cloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 * @author Administrator
 */
@RestController
public class SayHayController {

    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @GetMapping("/hi")
    public String index(@RequestParam("name")String name){
        return  schedualServiceHi.sayHay(name);
    }

}
