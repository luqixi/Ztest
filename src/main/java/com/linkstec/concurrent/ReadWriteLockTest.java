package com.linkstec.concurrent;

import java.util.Random;

public class ReadWriteLockTest {

	public static void main(String[] args) {    
        final SyncData data = new SyncData();
//      final ReadWriteLockData data = new ReadWriteLockData();
//      final RwLockData data = new RwLockData();
        
      //写入  
      for (int i = 0; i < 3; i++) {
          Thread t = new Thread(new Runnable() {    
              @Override  
      public void run() {    
                  for (int j = 0; j < 5; j++) {    
                      data.set(new Random().nextInt(30));    
                  }    
              }    
          });  
          t.setName("Thread-W" + i);  
          t.start();  
      }    
      //读取  
      for (int i = 0; i < 3; i++) {    
          Thread t = new Thread(new Runnable() {    
              @Override  
      public void run() {    
                  for (int j = 0; j < 5; j++) {    
                      data.get();    
                  }    
              }    
          });    
          t.setName("Thread-R" + i);  
          t.start();  
      }    
  } 
}
