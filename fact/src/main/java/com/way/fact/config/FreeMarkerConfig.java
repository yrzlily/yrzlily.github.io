package com.way.fact.config;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * FreeMarker配置
 * @author Administrator
 */
@Component
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable() throws Exception {
        configuration.setSharedVariable("block" , new BlockDirective());
        configuration.setSharedVariable("override" , new OverrideDirective());
        configuration.setSharedVariable("extends" , new ExtendsDirective());
    }

}
