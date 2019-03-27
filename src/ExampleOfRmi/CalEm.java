package ExampleOfRmi;

import java.rmi.RemoteException;

public class CalEm implements  Cal{

    @Override
    public int Add(int a, int b) throws RemoteException {
     return a+b;
    }
    
}
