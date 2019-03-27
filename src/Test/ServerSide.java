/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author m.j
 */
class ThreadServer extends Thread {

    private Socket socket;
    private Scanner scanner;
    private PrintWriter printWriter;

    public ThreadServer(Socket socket) {
        this.socket = socket;
        try {
            scanner = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        String response = scanner.next();
        while (!response.equals("by")) {
            printWriter.println("response:" + response);
            printWriter.flush();
            response = scanner.next();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        printWriter.println("by");
        printWriter.flush();
        System.out.println("clinet was disconectted....");
    }

}

public class ServerSide {

    public static void main(String[] args) throws IOException {
        ServerSocket socket1 = new ServerSocket(12345);
        System.out.println("server is lisening...");
        while (true) {
            Socket socket = socket1.accept();
            System.out.println("a new client was acceptide");
            ThreadServer server =new ThreadServer(socket);
            server.run();

        }
    }
}