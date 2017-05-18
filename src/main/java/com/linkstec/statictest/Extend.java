package com.linkstec.statictest;

public class Extend extends Base{
    
    public static String who = "Mr. Extend";
     
    static {
        System. out.println("static block in Extend" );
    }
    public static void functionInExtend(){
        System. out.println("static function in Extend!" );
    }
    Extend(){
        super();
    }
}
