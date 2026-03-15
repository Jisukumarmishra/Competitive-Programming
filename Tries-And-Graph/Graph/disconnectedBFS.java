package Graph;
import java.util.*;
public class disconnectedBFS {
    // jab disconnected graph ho to usme ham ek loop chalate hai 0 to V
    //vis[i] = false jab i false ho jye to i ko phir se starting bna de te hai
    static  class Edge {
        int src; 
        int dest;
        public Edge ( int s, int d ) {
            this.src = s ;
            this.dest = d ; 

        }
    }
    static void createGraph( ArrayList<Edge> graph[]) {
        for ( int i=0; i<graph.length; i++ ) {
            graph[i] = new ArrayList<Edge>();
        }
            graph[0].add(new Edge (0,1));
            graph[0].add(new Edge (0,2));

            graph[1].add(new Edge(1, 0));
            graph[1].add(new Edge (3, 1));

            graph[2].add(new Edge (2,0));
            graph[2].add(new Edge (2,4));

            graph[3].add(new Edge(3,1));
            graph[3].add(new Edge (3,4));
            graph[3].add(new Edge(3,5));

            graph[4].add(new Edge (4,2));
            graph[4].add(new Edge (4,3));
            graph[4].add(new Edge(4,5));

            graph[5].add(new Edge(5,3));
            graph[5].add(new Edge(5,4));
            graph[5].add(new Edge(5,6));
        }
    public static void bfs (ArrayList<Edge> graph [] , int V,boolean vis[], int start) {// t.c =O(v+e)
        Queue<Integer> q = new LinkedList<>();
        
        q.add(start);
        while (!q.isEmpty()) {
            int curr = q.remove();
            if ( vis[curr] == false){
               System.out.println(curr+ "");
               vis[curr] = true; 
               for ( int i=0; i<graph[curr].size(); i++) {
               Edge e = graph[curr].get(i);
               q.add(e.dest);
               }
            }
        }
    }
    public static void main(String[] args) {
        int V = 7; // total niuimber of vertices

        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        boolean vis[] = new boolean[V]; 
        for ( int i=0;i<V;i++) {
            if (vis[i] == false) {
               bfs(graph, V,vis,i); 
            }
        }
    
        System.out.println();
    }
 }
    

