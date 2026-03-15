package ClassRoomQuestions;
import java.util.*;

public class adjList {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt();  
    // int[][] edges = {
    // {0,1},
    // {1,2},
    // {2,3},
    // {1,3}
    // };

    int E = sc.nextInt();

    ArrayList<ArrayList<Integer>> adjlist = new ArrayList<>();

    // list for every V

    for (int i = 0; i < V; i++) {
      adjlist.add(new ArrayList<>());
    }

    for (int i = 0; i < E; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();

      adjlist.get(u).add(v); // u---> v one graph
      adjlist.get(v).add(u);// v--->u one graph

    }

    for (int i = 0; i < V; i++) {
      for (int neigh : adjlist.get(i)) {
        System.out.print(neigh + " ");
      }
      System.out.println();
    }
  }
}
