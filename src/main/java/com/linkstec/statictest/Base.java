package com.linkstec.statictest;

public class Base {
    
    public static String who = "Mr. Base";
     
    Base(){
    }
    static {
        System. out.println("static block in Base" );
    }
    public static void functionInBase(){
        System. out.println("static function in Base! -- " + who);
    }
    public void showWho(){
        System. out.println(who);
    }
}