import java.rmi.*;

public interface Servintf extends Remote {
  void printMsg() throws RemoteException;

  void addGraph(String id, Integer v) throws RemoteException;

  void addEdge(String id, Integer u, Integer v, Integer w) throws RemoteException;

  Integer getMst(String id) throws RemoteException;
}
