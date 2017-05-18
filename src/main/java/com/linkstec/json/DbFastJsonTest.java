package com.linkstec.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;

/**
 * fastjson
 * @author liuyl
 *
 */
public class DbFastJsonTest {

	private static FileInputStream fileInputStream;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("bin/wechat.txt");
		File fileout = new File("bin/wechat.csv");
		String head = "\"客户号\",\"股票代码\",\"股票名称\",\"公告信息\"";
		System.out.println(head);
		System.out.println(file.getAbsolutePath());
		String jsonline = null;
		
		FileOutputStream fos;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(fileout);
			OutputStreamWriter osw;
			try {
				osw = new OutputStreamWriter(fos,"UTF-8");
				bw = new BufferedWriter(osw);
				try {
					bw.write(head);
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		try {
			fileInputStream = new FileInputStream(file);
			BufferedReader bufferedReader;
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
				try {
					while((jsonline=bufferedReader.readLine())!=null){
						JSONObject jsonObject = JSONObject.parseObject(jsonline);
						String content = jsonObject.getString("content");
						System.out.println(content);
						
						JSONObject jsonObject2 = JSONObject.parseObject(content);
						
					    bw.write("\""+jsonObject.getString("sendAccounts")
					    +"\",\""
					    +jsonObject2.getString("stockCode")
					    +"\",\""
					    +jsonObject2.getString("stockName")
					    +"\",\""
					    +jsonObject2.getString("stockImportDesc")
					    +"\"");
					    bw.newLine();
					    bw.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}