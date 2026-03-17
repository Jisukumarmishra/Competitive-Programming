package Graph.ShortTimeRevision;
import java.util.ArrayList;

public class adjMatricesE {
  public static void main(String[] args) {
    int V = 5;
    int E = 4;
     int[][] edges = {
    {0,1}, // here node start with 0 so 
    {1,2},
    {2,3},
    {3,4}
    };

    int[][] mat = new int [V][V];
    
    for (int i =0; i<edges.length; i++) {
      int[] edge = edges[i];// gives edge = {0,1} when i =0;

      int  u = edge[0]; // extract first vertex
      int  v = edge[1];
      

      // show that u and v connected to each others
      mat[u][v] = 1;
      mat[v][u] = 1;
      
    }

    // print matrix
    for (int i =0; i<V; i++) {
      for(int j =0; j<V; j++) {
        System.out.print(mat[i][j]+" ");
      }
      System.out.println();
    }
  }
}
