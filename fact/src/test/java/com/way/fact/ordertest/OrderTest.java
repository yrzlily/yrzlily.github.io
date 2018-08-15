package com.way.fact.ordertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(BlockJUnit4ClassRunner.class)
@SpringBootTest
public class OrderTest {

    private static final Logger log = LoggerFactory.getLogger(OrderTest.class);

    private int a = 4;
    private int b = 6;

    @Test
    public void lock(){
        OrderTest orderTest = new OrderTest();
        System.out.println("a:" + orderTest.a + " b:" + orderTest.b);
        change(orderTest.a , orderTest.b);
        System.out.println("a:" + orderTest.a + " b:" + orderTest.b);

    }

    private void change(int a, int b){
        int tmp = a;
        a = b;
        b = tmp;

    }

}
