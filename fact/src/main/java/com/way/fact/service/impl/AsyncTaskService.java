package com.way.fact.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 调用线程
 * @author yrz
 */
@Service

public class AsyncTaskService {

    @Async
    public void dataTranslate(int i){

    }

}
