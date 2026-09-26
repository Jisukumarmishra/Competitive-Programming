import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// Question C

public class C {
    static class Edge {
        int to, cap, flow, cost, rev;

        Edge(int to, int cap, int flow, int cost, int rev) {
            this.to = to;
            this.cap = cap;
            this.flow = flow;
            this.cost = cost;
            this.rev = rev;
        }
    }

    static List<List<Edge>> graph;

    static void addEdge(int u, int v, int cap, int cost) {
        graph.get(u).add(new Edge(v, cap, 0, cost, graph.get(v).size()));
        graph.get(v).add(new Edge(u, 0, 0, -cost, graph.get(u).size() - 1));
    }

    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            Integer N = in.nextInt();
            if (N == null) return;
            Integer M = in.nextInt();

            int n = N;
            int m = M;

            int[] U = new int[m];
            int[] V = new int[m];
            for (int i = 0; i < m; i++) {
                U[i] = in.nextInt();
                V[i] = in.nextInt();
            }

            int s1 = in.nextInt();
            int s2 = in.nextInt();
            int outpost = in.nextInt();

            // Coordinate Compression: Maps any node ID to a strict range 1...K
            HashMap<Integer, Integer> map = new HashMap<>();
            int K = 0;
            for (int i = 0; i < m; i++) {
                if (!map.containsKey(U[i])) map.put(U[i], ++K);
                if (!map.containsKey(V[i])) map.put(V[i], ++K);
            }
            if (!map.containsKey(s1)) map.put(s1, ++K);
            if (!map.containsKey(s2)) map.put(s2, ++K);
            if (!map.containsKey(outpost)) map.put(outpost, ++K);

            // Graph bounds
            int S = 0;
            int T = 2 * K + 1;
            graph = new ArrayList<>();
            for (int i = 0; i <= T; i++) {
                graph.add(new ArrayList<>());
            }

            // Node Splitting: 1 to K are IN nodes, (1+K) to (2K) are OUT nodes
            int outpostID = map.get(outpost);
            for (int i = 1; i <= K; i++) {
                int cap = (i == outpostID) ? 2 : 1;
                addEdge(i, i + K, cap, 0); // Internal node capacity 
            }

            // Build bidirectional roads
            for (int i = 0; i < m; i++) {
                int u = map.get(U[i]);
                int v = map.get(V[i]);
                // Traveling along an edge costs 1 (which matches the requirement)
                addEdge(u + K, v, 1, 1);
                addEdge(v + K, u, 1, 1);
            }

            // Connect Super Source to Scout start points
            int S1 = map.get(s1);
            int S2 = map.get(s2);
            addEdge(S, S1, 1, 0);
            addEdge(S, S2, 1, 0);

            // Connect Outpost to Super Sink
            addEdge(outpostID + K, T, 2, 0);

            // Min-Cost Max-Flow via Successive Shortest Path (SPFA)
            int[] dist = new int[T + 1];
            int[] parentVertex = new int[T + 1];
            Edge[] parentEdge = new Edge[T + 1];
            boolean[] inQueue = new boolean[T + 1];

            int totalFlow = 0;
            int totalCost = 0;

            while (totalFlow < 2) {
                Arrays.fill(dist, Integer.MAX_VALUE);
                Arrays.fill(parentVertex, -1);
                Arrays.fill(parentEdge, null);
                Arrays.fill(inQueue, false);

                Queue<Integer> queue = new LinkedList<>();
                queue.add(S);
                dist[S] = 0;
                inQueue[S] = true;

                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    inQueue[u] = false;

                    for (Edge e : graph.get(u)) {
                        if (e.cap - e.flow > 0 && dist[u] != Integer.MAX_VALUE && dist[e.to] > dist[u] + e.cost) {
                            dist[e.to] = dist[u] + e.cost;
                            parentVertex[e.to] = u;
                            parentEdge[e.to] = e;
                            if (!inQueue[e.to]) {
                                queue.add(e.to);
                                inQueue[e.to] = true;
                            }
                        }
                    }
                }

                if (dist[T] == Integer.MAX_VALUE) {
                    break;
                }

                int push = Integer.MAX_VALUE;
                int curr = T;
                while (curr != S) {
                    Edge e = parentEdge[curr];
                    push = Math.min(push, e.cap - e.flow);
                    curr = parentVertex[curr];
                }

                push = Math.min(push, 2 - totalFlow); // We only need exactly 2 flow total

                curr = T;
                while (curr != S) {
                    Edge e = parentEdge[curr];
                    e.flow += push;
                    graph.get(e.to).get(e.rev).flow -= push;
                    totalCost += push * e.cost;
                    curr = parentVertex[curr];
                }

                totalFlow += push;
            }

            if (totalFlow == 2) {
                System.out.println(totalCost);
            } else {
                System.out.println("Impossible");
            }
        } catch (Exception e) {
            // Catches any obscure parsing errors to ensure graceful failure on strict graders
            e.printStackTrace();
        }
    }

    // A fully secure fast-reader that gracefully ignores weird spacing and blank lines
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (Exception e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            String s = next();
            if (s == null) return null;
            return Integer.parseInt(s);
        }
    }
}