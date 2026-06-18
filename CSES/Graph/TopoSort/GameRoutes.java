import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameRoutes {
  static ArrayList<Integer> graph [] ;
  static long count ;
  static void dfs ( int currNode, int n) {
    
    if(currNode == n) {
      count ++;
      return;
    }

    for (int next : graph[currNode]) {
      dfs(next, n);
    }
  }

  static int topo (int i, int n) {
    Queue<Integer> q = new LinkedList<>();
    int []  indegree = new int[n];
    for (int neigh : graph[neigh]) {
      int u = graph[neigh];
      int v = graph[];
      indegree[v]++;
    }

    while (!q.isEmpty()) {
      int curr = q.poll();
      
    }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    // int a = sc.nextInt();
    // int b = sc.nextInt();

    graph = new ArrayList[n+1];
    for (int i =1; i<=n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int i =0; i<m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      graph[a].add(b);
    }

    // count =0;
    // dfs(1, n);
    // System.out.println(count);

  }
}