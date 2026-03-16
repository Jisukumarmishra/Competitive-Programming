import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraph {
  public static void main(String[] args) {
    // undurected graph with diffrent component print adjList
    // print bfs on a graph and print all connected component
    // count the number of connected component
    // give Any 2 idx which are not in same group
    // print adj matrix

    // graph 0-1-2-3-1 4

    int V = 5;
    int [][] edges = {{0,1}, {1,2}, {2,3},{3,1} };
    
    //1) adjList Print
   
    // create adjList (convert given 2d edge matrix toadjList)
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i =0; i<V; i++) {// here ham size adj.size() nhi le sakte beacause intilaly ArrayList ki size 0 hoti hai
      adj.add(new ArrayList<>());
    }

    for (int [] edge :edges) {
      int  u = edge[0];
      int v = edge[1];
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

  // for print adjList
    for (int i= 0; i<adj.size(); i++) {
      for(int neigh : adj.get(i)) {
        System.out.print(neigh+" ");
      }
      System.out.println();
    }


    // 2) print bfs
    Queue<Integer> q = new LinkedList<>();
    boolean [] vis = new boolean [V];
    q.add(0);
    vis[0] = true;

    while (!q.isEmpty()) {
      int curr = q.poll();
      System.out.println(curr + " ");

      for (int neigh :adj.get(curr)) {
        if (vis[neigh] == false) {
          q.add(neigh);
          vis[neigh] = true;
        }
      }
      // 4 will not appear in output because itys from the diffrent compinet to print that we habve to call a bfs function for each vertex;
    }


    // print all component
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

    boolean [] vis2 = new boolean[V];
    // Queue<Integer> q2 = new LinkedList<>(); //wrong Hai = For each component BFS we should create a fresh queue.

    // now iterate  every vertex thright bfs and check it and add it
    // its like a function call for every vertex = check for a every vertex Because here Constrains are less

    for (int i = 0;  i<V; i++) {
    
      if (vis2[i] == false) {
       
        ArrayList<Integer> comp = new ArrayList<>();
        Queue<Integer> q2 = new LinkedList<>();

        q2.add(i);
        vis2[i] = true;

        while (!q2.isEmpty()){
          int curr = q2.poll();
          comp.add(curr);

          for(int neigh : adj.get(curr)) {
            if (vis2[neigh] == false) {
             q2.add(neigh);
             vis2[neigh] = true;
            }
          }
        }
      ans.add(comp);

      }

    }

    System.out.println("All Components");
    System.out.println(ans);
    

    // 3) no of connected component
    System.out.println("Components " + ans.size());
    

    // 4) any 2 idx which is not form same component

    if (ans.size() >= 2) { //  means = If there are at least two components
      int a = ans.get(0).get(0);// first vertex of first component
      int b = ans.get(1).get(0);//first vertex of second component

      System.out.println("Two idx from diffrent compomemt " + a + " " + b);
    }

    // print adj matrix
    
    int [][] mat = new int[V][V];

    for(int i =0; i<V; i++) {
      for (int neigh : adj.get(i)) {
        mat[i][neigh] = 1;
      }
    }

    System.out.println("AdjMatrix");
   // print adjMatrix
    for (int i=0; i<V; i++){
      for(int j =0;j<V; j++){
        System.out.print(mat[i][j]+ " ");
      }
      System.out.println();
    }
    
  }
}
