package com.way.fact.service;

/**
 * @author yrz
 */
public interface AsyncTaskService {

    /**
     * 线程测试
     * @param i 执行次数
     */
    void asyncTask(int i);

    /**
     * 停止线程
     */
    void stops();
}
