package ExampleOfRmi;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sever {
    public static void main(String[] args) {
        if(System.getSecurityManager()==null)
            System.setSecurityManager(new SecurityManager());
        CalEm calculator=new CalEm();
        String name="calculator";
        try {
            UnicastRemoteObject.exportObject(calculator,0);
            Registry registry=LocateRegistry.createRegistry(1099);
            registry.bind(name, calculator);
            System.out.println("server bound");
        } catch (RemoteException ex) {
            Logger.getLogger(sever.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(sever.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
    }
    
    
}
