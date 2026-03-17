package Graph.ShortTimeRevision;
import java.util.ArrayList;

public class adjListE {
  public static void main(String[] args) {
    // 0-1-2-3-4
    int V = 5;
     int[][] edges = {
    {0,1},
    {1,2},
    {2,3},
    {3,4}
    };

    ArrayList<ArrayList<Integer>> list = new ArrayList<>();// initlayy empty
  
    // create vertex of empty list
    for (int i =0; i<V; i++) {
      list.add(new ArrayList<>());
    }

    for (int i =0; i<edges.length; i++) {
      int [] edge = edges[i];
      int u = edge[0];
      int v = edge[1];
     
      // insert edges
      list.get(u).add(v);
      list.get(v).add(u);

    }
    
    // for  printing
    // for (int i =0; i<V; i++) {
    //   System.out.print(list.get(i));
    // }
    
    // or
    for (int  i=0;i<list.size(); i++) { // hamlog vertex ki size ke brabar hi list bnate hai
      System.out.println(i+ "-->" + list.get(i));

    }

  }
}
