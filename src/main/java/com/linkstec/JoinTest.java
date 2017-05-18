package com.linkstec;

public class JoinTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Join join = new Join();
		Thread thread = new Thread(join);
		thread.start();
		try {
			// 主线程暂停
			thread.join();// join加入主线程，属于主线程的一部分，主线程后面部分暂停
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for ( int i = 0; i < 10; i ++){
			System.out.println(i + "main");
		}
	}

}

class Join implements Runnable {

	@Override
	public void run() {
		for ( int i = 0; i < 10; i ++){
			System.out.println(i + "join");
		}
	}
}