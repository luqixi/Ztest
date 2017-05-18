package com.linkstec.concurrent;

public class SyncTest implements Runnable{

	public synchronized void get(){
		System.out.println(Thread.currentThread().getId());
		set();
	}

	public synchronized void set(){
		System.out.println(Thread.currentThread().getId());
	}

	@Override
	public void run() {
		get();
	}
	public static void main(String[] args) {
		SyncTest ss=new SyncTest();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}
}
