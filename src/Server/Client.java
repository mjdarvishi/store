 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import comman.LoginInfo;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.j
 */
public class Client { 
  private static  Client client;
  private ObjectOutputStream writer;
  private Scanner reader;
  public static Client GetClient(){
      if(client==null)
          client=new Client();
          return client;
  }
  private Client(){
      try {
          Properties properties=new Properties();
          properties.load(new FileReader("Confige.properties"));
          String name= properties.getProperty("ip");
          Socket socket=new Socket(name, 12345);
          writer=new ObjectOutputStream(socket.getOutputStream());
          reader=new Scanner(socket.getInputStream());
          
      } catch (IOException ex) {
          Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
public String Isvalid(String user,String pass){
    String name="";
    LoginInfo info=new LoginInfo(user, pass);
      try {
          writer.writeObject(info);
          writer.flush();
          name=reader.nextLine();
      } catch (IOException ex) {
ex.printStackTrace();
      }
    return name;
}

}
