import java.util.Scanner;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
  private Client() {
  }

  public static void main(String[] args) {
    try {

      Registry registry = LocateRegistry.getRegistry(null);

      Servintf stub = (Servintf) Naming.lookup("rmi://" + args[0] + ":" + args[1] + "/mst");

      Scanner input = new Scanner(System.in);
      try {
        while (true) {
          String inp1 = input.next();
          if (inp1.equals("add_graph")) {
            String id = input.next();
            Integer v = input.nextInt();
            stub.addGraph(id, v);
          } else if (inp1.equals("add_edge")) {
            String id = input.next();
            Integer u = input.nextInt();
            Integer v = input.nextInt();
            Integer w = input.nextInt();
            stub.addEdge(id, u, v, w);
          } else if (inp1.equals("get_mst")) {
            String id = input.next();
            Integer ans = stub.getMst(id);
            System.out.println(ans.toString());
          } else {
            stub.printMsg();
          }
        }
      } catch (Exception e) {
      }

    } catch (Exception e) {
      System.err.println("Client exception: " + e.toString());
      e.printStackTrace();
    }
  }
}
