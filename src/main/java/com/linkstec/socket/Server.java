package com.linkstec.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static ServerSocket socketServer;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		socketServer = new ServerSocket(9090);
		while (true) {
			Socket socket = socketServer.accept();
			socket.setSoTimeout(5000);//5秒超时
			InputStream in = socket.getInputStream();
			byte[] b = new byte[7];
			int len;
			try {
				while ((len = in.read(b)) != -1) {
					String value = new String(b, 0, len);
					double d=Double.parseDouble(value);
					//process data
					System.out.println(d);
				}
			} catch (Exception ex) {
				//handle exception
				ex.printStackTrace();
			}
		}
	}

}

