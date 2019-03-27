package ExampleOfRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cal extends  Remote{
    int Add(int a,int b) throws RemoteException;
}
