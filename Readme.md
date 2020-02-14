#Distributed System Assignment 2

##Architecture
- RMI (Remote Method Invocation) API is used in this assignment. RMI creates a public remote server object that enables client and server side communications through simple method calls on the server object.
- For this, there are two sides - server and client where server can handle multiple
  clients together.
-Graph is represented as a Linked List of weighted edges. 

##Algorithm Implementation
- For Minimum Spanning Tree, Kruskal's algorithm is used. 
- For which, disjoint union is used, after sorting out the edges according to
  the weight.
- Then, one by one edges are added into the MST (if adding it doesn't form any
  cycle in current spanning tree) { which is checked by DSU }.
- Finally if the number of edges in the formed spanning is less the V - 1 (
  V = number of vertices in the graph ), -1 is returned else the sum of edges is
  returned (in the MST).

##Results
- Program was working fine on the given sample test case.
- Kruskal's algorithm is used for MST so it's O(E.log(V)) { where E = number of
  edges and V = number of vertices }

##Observations
- Working for multiple client concurrently with single server.
- Input is taken through std.in untill EOF.


Rishabh Singhal, 20171213



