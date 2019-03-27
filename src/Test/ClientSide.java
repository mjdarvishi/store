/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.j
 */
public class ClientSide {
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("127.0.0.1",12345);
            PrintWriter printWriter=new PrintWriter( socket.getOutputStream());
            Scanner scanner=new Scanner(socket.getInputStream());
            printWriter.println("hi");
            printWriter.println("how are you?");
            printWriter.println("u?");
             printWriter.println("by");
            printWriter.flush(); 
            
            String response=scanner.next();
            while(response.indexOf("by")==-1){
                   System.out.println(response);
                   response=scanner.next();
                }
        } catch (IOException ex) {
            Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
