package com.linkstec;

public class ReturnTest {

	public static void main(String[] args) {
		
	    //return值，改了之后，返回值不变
		int i = returnTe1();
		System.out.println(i);
		System.out.println("===============我不是分隔符==============================================");
		//return引用，改了之后，返回值也跟着改变
		ReturnTestBean returntestbean = returnTe2();
		System.out.println(returntestbean.getName());

	}

	static int returnTe1() {
		int ret = 0;
		try{
			return ret;
//		throw new Exception();
		}
		catch(Exception e){
		ret = 100;
		return ret;
		}
		finally{
		ret = 2;
		}
	}
	static ReturnTestBean returnTe2() {
		ReturnTestBean ret = new ReturnTestBean();
		try{
			throw new Exception();
		}
		catch(Exception e){
			ret.setName("nihao");
			return ret;
		}
		finally{
			ret.setName("fuck");
		}
	}
}
