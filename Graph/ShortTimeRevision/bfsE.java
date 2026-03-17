package Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class bfsE {
  public static void main(String[] args) {
    int V = 5;
    int[][] edges = {
        { 0, 1 },
        { 1, 2 },
        { 2, 3 },
        { 3, 4 }
    };

    // create adjList
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
    // add edges
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];

      adj.get(u).add(v);
      adj.get(v).add(u);// this line because unidirected

    }

    // BFS
    ArrayList<Integer> bfsOrder = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();
    boolean[] isVis = new boolean[V];

    q.add(0);
    isVis[0] = true;

    while (!q.isEmpty()) { // jab tak container khali na ho jaye

      int curr = q.poll();
      bfsOrder.add(curr);

      for (int x : adj.get(curr)) { // har ek vertes ke all neighbour pe iterate kar rhe hai

        if (!isVis[x]) {
          q.add(x);
          isVis[x] = true;
        }

      }
    }
    System.out.println(bfsOrder);
  }
}

// second way // adj list is hardcoded

// public class bfsE {

// public static void main(String[] args) {

// ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

// adj.add(new ArrayList<>(Arrays.asList(1))); // 0
// adj.add(new ArrayList<>(Arrays.asList(0,2))); // 1
// adj.add(new ArrayList<>(Arrays.asList(1,3))); // 2
// adj.add(new ArrayList<>(Arrays.asList(2,4))); // 3
// adj.add(new ArrayList<>(Arrays.asList(3))); // 4

// ArrayList<Integer> bfsOrder = new ArrayList<>();
// Queue<Integer> q = new LinkedList<>();

// boolean[] visited = new boolean[adj.size()];

// q.add(0);
// visited[0] = true;

// while(!q.isEmpty()){

// int curr = q.poll();
// bfsOrder.add(curr);

// for(int x : adj.get(curr)){
// if(!visited[x]){
// q.add(x);
// visited[x] = true;
// }
// }
// }

// System.out.println(bfsOrder);
// }
// }