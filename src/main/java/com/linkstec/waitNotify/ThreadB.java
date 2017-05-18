package com.linkstec.waitNotify;

public class ThreadB extends Thread {

	private Object lock;
	public ThreadB(Object lock) {
		super();
		this.lock = lock;
	}
	@Override
	public void run() {
		synchronized (lock) {
			for (int i = 0; i < 10; i ++) {
				MyList.add();
				if ( MyList.size() == 5) {
					lock.notify();//不会立即释放锁
					System.out.println("发出通知!");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("添加了" + (i + 1) + "个元素");
			}
		}
	}

}
