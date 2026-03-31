package Graph.TopologicalSorting;

import java.util.ArrayList;
import java.util.Stack;

import Graph.dfs;

public class TopoLogicalSort {

    static class Edge {

      int src;
      int dest;
      
      public Edge (int s, int d) {
        this.src = s;
        this.dest = d;

      }
    }

  public static void main(String[] args) {

    int V = 6;
    ArrayList<Edge> graph [] = new ArrayList[V];
    createGraph(graph);
    topSort(graph);

  }

  // O(V+E)
  public static void topSort (ArrayList<Edge> [] graph) {
    boolean vis [] = new boolean[graph.length];
    Stack<Integer> s = new Stack<>(); 

    for (int i =0; i<graph.length; i++) {
      if (!vis[i]) {
        dfs(graph, i, vis, s); // modified dfs called
      }
    }
    while (!s.isEmpty()) {
      System.out.println(s.pop()+"");
    }
  }

  public static void dfs (ArrayList<Edge>[] graph,int curr, boolean [] vis, Stack<Integer> s) {
  vis [curr] = true;

  // now loop for neighbour 
  for (int i=0; i<graph[curr].size(); i++) {
    Edge e = graph[curr].get(i);
    // e.dist = neigh
    if(!vis[e.dest]) {
     dfs(graph, e.dest, vis, s);
    }
  }
  s.push(curr);
  }

  static void createGraph(ArrayList<Edge> graph []) {
    for (int i = 0; i<graph.length; i++) {
      graph[i] = new ArrayList<>();
    }
    graph[2].add(new Edge(2,3));

    graph[3].add(new Edge(3,1));

    graph[4].add(new Edge(4,0));
    graph[4].add(new Edge(4,1));

    graph[5].add(new Edge(5,0));
    graph[5].add(new Edge(5,2));
    /*
    5 __ 0 __ 4
    |         |
    2         1
    |         |
    3_________| // not that graph is directed 5-> 0,5->2, 4->0,4->1, 2->3, 3->1

    */
  }
}
