package com.linkstec.exception;
class ExampleA extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExampleA(String string) {
	}}
public class ExampleB extends ExampleA{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1110986513774451433L;
	public ExampleB(String string) {
		super(string);
	}
	public static void main(String[] args) {
		try {
		    throw new ExampleB("b");
		} catch(ExampleA e){
		    System.out.println("ExampleA");
		} catch(Exception e){
		    System.out.println("Exception");
		}
	}
}
