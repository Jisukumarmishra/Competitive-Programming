package Graph.DirectedGraph;

import java.util.ArrayList;

// this is main function
public class cycleDfs {
  public static boolean isCycle (int V, int [][] edges) {
   // convert a graph(adjList)
   ArrayList<ArrayList<Integer>> list = new ArrayList<>();

   for(int i =0; i<V; i++) {
   list.add(new ArrayList<>());
   }

   for (int [] edge: edges) {
    int  u = edge[0];
    int  v = edge[1];
    list.get(u).add(v);
   }

   // final code hre 
   boolean vis [] = new boolean [V];
   boolean [] stack = new boolean[V];
   
   for(int i =0; i<V; i++) {
   
    if (vis[i] == false) { // means agar kahi bhi unvisited mil rha hai to 

    if (dfs(i, list, vis, stack)) { // usko dfs se visit karke check kar lo cycle ban rhai hai ki nhi
      return true;
    }
   }
  }
  return false;
}

static boolean dfs (int s, ArrayList<ArrayList<Integer>> list, boolean [] vis , boolean [] stack) {
    vis[s] = true;
    stack[s] = true;

    for (int neigh : list.get(s)) {

      // if(!vis[neigh]) {

      //   if(dfs (neigh, list, vis, stack)) {
      //     return true;
      //   }

      // } else if (stack[neigh] == true) {
      //   return true;
      // } 

      // or

      if (!vis[neigh]) {
        if(dfs(neigh, list, vis, stack)) {
          return true;
        }
      }

      if ( vis[neigh] == true && stack[neigh] == true) {
        return true;
      }

    }
    stack[s] = false;
    return false;
   }
}


// visit node
// mark stack true

// for neighbour

//     if not visited → DFS

//     if already in stack → cycle

// DFS end → stack false