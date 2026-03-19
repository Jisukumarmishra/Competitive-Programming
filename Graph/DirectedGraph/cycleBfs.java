package Graph.DirectedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class cycleBfs {
  boolean isCycle (int V, int [][] edges) {

    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    for (int  i=0; i<V; i++) {
      list.add(new ArrayList<>());
    }
    // create graph with indegree
    int [] indegree = new int [V];

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      list.get(u).add(v);
      indegree[v] ++; // because u->v here arrow v ki taraph aa rhi hai then indegree[v]++
      // incoming edges

    }

    return bfs (V, list, indegree); // here vis ki jarurat hi nhi hai indegree se kamchalk jayega 
  }

  static boolean bfs (int V, ArrayList<ArrayList<Integer>> list,int [] indegree ) {
    Queue<Integer> q = new LinkedList<>();
     // for bfs start with the min or 0 indegree also element ko 
     // tabhi queue me add karenge jab tak uski indegree 0 na jaye 
     // indegree 0 nhi ho rha means cycler exist
     // start with the element whose indegrree 0

     for (int  i=0; i<V; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
     }

     int count = 0; // node process track karne ke liye
     // count isliye le rhe hai taki pta kar sake kei sare node/ elemnet/vertex process
     // hue ki nhi if not  means some vertex stuck in the cycle show definitely cycyule exist

     while (!q.isEmpty()) {
      int curr = q.poll();
      count++;

      for (int neigh : list.get(curr)) {
      
      indegree[neigh]--;// edge khatam ho rhi haiu

      if (indegree[neigh] == 0) {
        q.add(neigh);
      }
      
      }
     }

     return count != V;
  }
}
