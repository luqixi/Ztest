package com.linkstec.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerCosumer {
    public static void main(String[] args) {
        /*
         *  jdk1.5提供了多线程的升级解决方法（显示的锁机制）
         *  将同步synchronized替换成了显示的Lock操作----》lock()  unlock()
         *  将Object中的wait、notify/notifyAll  替换成了 Condition (await/signal/signalAll)
         *  该对象可以 Lock.newCondition() 获取
         *  一个锁可以绑定多个condition对象，避免了因同步嵌套导致死锁问题的发生。
         *     释放锁的操作必须放在我们的try/finally代码块或者try..catch/finally代码块当中使用
         * 
         *     实现了本方只唤醒对方的操作！
         * 
         * */
        Resource r = new Resource();
        MyProducer product = new MyProducer(r);
        MyConsumer customer = new MyConsumer(r);
        Thread t1 = new Thread(product);
        t1.setName("(生产者一号)");
        Thread t2 = new Thread(product);
        t2.setName("(生产者二号)");
        Thread t3 = new Thread(customer);
        t3.setName("(消费者一号)");
        Thread t4 = new Thread(customer);
        t4.setName("(消费者二号)");
        System.out.println("ProducerCosumer.main()开始");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Resource{
    private String name;
    private int count = 1;
    private boolean flag = false;
    private Lock lock = new ReentrantLock();
    private Condition condition_pro = lock.newCondition();
    private Condition condition_cus = lock.newCondition();
    public void set(String name) throws InterruptedException{
        lock.lock();
        try {
            while(flag){
                condition_pro.await();
            }
            this.name = name+count++;
            System.out.println(Thread.currentThread().getName()+"生产者生产..."+this.name);
            flag = true;
            condition_cus.signal();
        } finally {
            lock.unlock();
        }
        
    }
    public void get() throws InterruptedException{
        lock.lock();
        try {
            while(!flag){
                condition_cus.await();
            }
            System.out.println(Thread.currentThread().getName()+"消费者消费.........."+this.name);
            flag = false;
            condition_pro.signal();
        } finally {
            lock.unlock();
        }
    }
}
class MyProducer implements Runnable{
    private Resource resource;
    public MyProducer(Resource r) {
        this.resource = r;
    }
    public void run(){
        while(true){
            try {
                resource.set("【商品】");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class MyConsumer implements Runnable{
    private Resource resource;
    public MyConsumer(Resource r) {
        this.resource = r;
    }
    @Override
    public void run() {
        while(true){
            try {
                resource.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }
    }
}