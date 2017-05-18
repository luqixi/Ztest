package com.linkstec.statictest;
/**
 * Java 中的static代码块并不一定会被调用。
	设两个含静态方法和静态块的两个类（父类Base 子类Extend）
	static块要写在public类里，所以两个类要写在单独的两个.java文件里
	
	陷阱1 
	可见在执行Extend.functionInBase();
	时类Extend中的静态代码块并未被执行，要让其执行的充分条件可以是(满足其中一项即可)
	1. new 一个Extend类
	2. 调用一个Extend类中实现的(而非继承下来的)静态方法
	 结论1:在一个未被初始化过的子类Extend中，调用Extend的父类实现的静态方法，Extend的静态代码块不会被调用。
	 陷阱2
	在Extend ex = new Extend();
	后执行
	ex.showWho();
	时，并未如愿输出Mr. Extend 而是输出了：
	Mr. Base
	但是
	System.out.println(Extend. who);
	如愿输出
	Mr. Extend
	结论	2:子类Extend从父类Base继承过来的静态方法调用的静态变量并不随着子类Extend中这个静态变量的覆盖而覆盖。WTF！
 * @author linkage
 *
 */
public class Main {
	  
    public static void main(String[] args) {
                       
        Extend.functionInBase();
        Extend ex = new Extend();
        ex.showWho();
        System.out.println(Extend.who);
    }
}