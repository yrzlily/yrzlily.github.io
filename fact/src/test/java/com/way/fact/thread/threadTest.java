package com.way.fact.thread;


class threadTest implements Runnable{

    private String mark;

    threadTest(String mark){
        this.mark = mark;
    }

    @Override
    public void run() {


            try {
                for (int i = 0; i <10 ; i++) {
                    System.out.println(mark + ":执行第"+i+"次");
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}

class testThread{

    public static void main(String [] args){
        threadTest threadTest = new threadTest("A");

        java.lang.Thread t = new java.lang.Thread(threadTest);
        t.start();

        threadTest threadTest1 = new threadTest("B");
        java.lang.Thread t1 = new java.lang.Thread(threadTest1);
        t1.start();


    }

}