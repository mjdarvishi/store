/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

class MyThread extends Thread{
    public void run(){
          for (int i = 0; i < 5; i++) {
            System.out.println("i="+i);
        }
    }
}

public class TheardTest {
    public static void main(String[] args) throws InterruptedException {
      MyThread t=new MyThread();
      t.start();
        for(int i = 0; i < 5 ;i++) {
            System.out.println("i="+i);
            Thread.sleep(90000);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("i="+i);
        }
    }
    
}
