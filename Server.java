import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.HashMap; 
import java.util.Map; 

public class Server implements Servintf{

 
  

  static class Edge implements Comparable<Edge> {
    Integer u;
    Integer v;
    Integer w;
  
    Edge(int u,int v, int w){
      this.u = u;
      this.v = v;
      this.w = w;
    }
    
    public int compareTo(Edge compareEdge)
    {
            return this.w-compareEdge.w;
    }

  }

  static class Ugraph 
    { 
        
        int v; 
        LinkedList<Edge> adj; 
        int[] dsu;

        Ugraph(int v) 
        { 
            this.v = v;
            dsu = new int[v+1];
            adj = new LinkedList<Edge>(); 
        }
    } 

  public  Map<String, Ugraph> gmap = new HashMap<>();

  Integer par(String id, int u){
    if(gmap.get(id).dsu[u] < 0){
      return u;
    }
    else{
      gmap.get(id).dsu[u] = par(id,gmap.get(id).dsu[u]);
      return gmap.get(id).dsu[u];
    }
  }

  int join(String id,int u,int v){
    u = par(id,u);
    v = par(id,v);
    if(u==v) return 0;
    if(gmap.get(id).dsu[u] > gmap.get(id).dsu[v]){
       int t = u;
       u = v;
       v = u;
    }
    gmap.get(id).dsu[u]+= gmap.get(id).dsu[v];
    gmap.get(id).dsu[v] = u;
    return 1;
  }



  public void printMsg() throws RemoteException{
    System.out.println("This is an RMI Program!!");
  }
  
  public void addGraph(String id, Integer v) throws RemoteException{
    Ugraph graph = new Ugraph(v); 
    gmap.put(id,graph);
 //   System.out.println("Graph with id: "+id+" added!!");
  }

  public void addEdge(String id, Integer u, Integer v, Integer w) throws RemoteException{
    gmap.get(id).adj.add(new Edge(u,v,w));    
 //   System.out.println("Edge Added");
  }
  


  public Integer getMst(String id) throws RemoteException{
    Ugraph graph = gmap.get(id);
    Edge edge[] = new Edge[graph.adj.size()];
    int k = 0;
    for(Edge x : graph.adj){
      edge[k] = x;
      k++;
    }
    Arrays.sort(edge);
    for(int i=1;i<=graph.v;i++){
      graph.dsu[i] = -1;
    }
    int mst = 0;
    int ch = 0 ;
    for(int i=0;i<graph.adj.size();i++){
       if(par(id,edge[i].v)!=par(id,edge[i].u)){
          mst += edge[i].w;
          join(id, edge[i].v, edge[i].u);
          ch ++;
       }
    }
 //   System.out.println("Answer Sent!!");
    if(ch == graph.v - 1) return mst;
    else return -1;
  }
  public Server() throws RemoteException{}

  public static void main(String args[]) throws Exception{
    
    int servp = Integer.parseInt(args[0]);

    try{
      
      Server obj = new Server();

      Servintf stub = (Servintf) UnicastRemoteObject.exportObject(obj, 0);

      LocateRegistry.createRegistry(servp);

      Naming.rebind("rmi://localhost:"+args[0]+"/mst",obj);
      System.out.println("Server Ready");

    } catch (Exception e){
      System.err.println("Server exception: "+e.toString());
      e.printStackTrace();
    }
  }
}


