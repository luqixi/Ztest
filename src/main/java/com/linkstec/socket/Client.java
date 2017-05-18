package com.linkstec.socket;

import java.io.OutputStream;
import java.net.Socket;

public class Client {
	static class SendThread extends Thread {
		Socket server;
		byte[] value;

		public SendThread(Socket server, String value) {
			super();
			this.server = server;
			this.value = value.getBytes();
		}

		public void run() {
			while (true) {
				try {
					OutputStream out = server.getOutputStream();
					out.write(value);
					out.flush();
					Thread.sleep(1000);
				} catch (Exception ex) {
					return;
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Socket server = new Socket("localhost", 9090);
		SendThread t1 = new SendThread(server, "1111.11");
		SendThread t2 = new SendThread(server, "2222.22");
		SendThread t3 = new SendThread(server, "3333.33");
		t1.start();
		t2.start();
		t3.start();
	}

}
