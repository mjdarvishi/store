/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import comman.LoginInfo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import model.dbAccessor;

/**
 *
 * @author m.j
 */
class ThreadServer {

    private Socket socket;
    private ObjectInputStream reader;
    private PrintWriter writer;

    public ThreadServer(Socket socket) {
        this.socket = socket;
        try {
            reader = new ObjectInputStream(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void run(){
        try {
            Object response = reader.readObject();
         //   while (!response.equals("by")) {
                if (response instanceof LoginInfo) {
                    LoginInfo info = (LoginInfo) response;
                    String result=dbAccessor.GetAccessor().isValid(info.getUser(), info.getPass());
                    writer.println(result);
                    writer.flush();
                //}

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

public class server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
              System.out.println("server is lisening....");
            while(true){
            Socket socket = serverSocket.accept();
          
            ThreadServer ts = new ThreadServer(socket);
            System.out.println("new client was acceptied....");
            ts.run();
            
            }
        } catch (IOException ex) {
    ex.printStackTrace();
        }

    }

}
