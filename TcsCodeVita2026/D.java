import java.util.*;
import java.io.*;

public class D {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // Read N
        line = nextValidLine(br);
        if (line == null)
            return;
        int n = Integer.parseInt(line);

        Map<String, Set<String>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            line = nextValidLine(br);
            if (line == null)
                break;
            // Split dynamically by comma or spaces to avoid token issues
            String[] parts = line.split("[,\\s]+");
            if (parts.length > 0) {
                String u = parts[0];
                graph.putIfAbsent(u, new HashSet<>());
                for (int j = 1; j < parts.length; j++) {
                    String v = parts[j];
                    graph.putIfAbsent(v, new HashSet<>());
                    graph.get(u).add(v);
                    graph.get(v).add(u);
                }
            }
        }

        // Read Q
        line = nextValidLine(br);
        if (line == null)
            return;
        int q = Integer.parseInt(line);

        String[] queries = new String[q];
        for (int i = 0; i < q; i++) {
            queries[i] = nextValidLine(br);
        }

        // Read R
        line = nextValidLine(br);
        if (line == null)
            return;
        int r = Integer.parseInt(line);

        Map<String, Set<String>> restrictions = new HashMap<>();
        for (int i = 0; i < r; i++) {
            line = nextValidLine(br);
            if (line == null)
                break;
            String[] parts = line.split("[,\\s]+");
            if (parts.length > 0) {
                String u = parts[0];
                restrictions.putIfAbsent(u, new HashSet<>());
                for (int j = 1; j < parts.length; j++) {
                    restrictions.get(u).add(parts[j]);
                }
            }
        }

        // Process Queries and Store Results
        List<String> results = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            if (queries[i] == null)
                continue;
            String[] parts = queries[i].split("[,\\s]+");
            if (parts.length < 3)
                continue;

            String u = parts[0];
            String op = parts[1];
            String v = parts[2];

            if (op.equals("to")) {
                results.add(canTravel(graph, restrictions, u, v) ? "yes" : "no");
            } else if (op.equals("connects")) {
                graph.putIfAbsent(u, new HashSet<>());
                graph.putIfAbsent(v, new HashSet<>());
                graph.get(u).add(v);
                graph.get(v).add(u);
            } else if (op.equals("disconnects")) {
                if (graph.containsKey(u))
                    graph.get(u).remove(v);
                if (graph.containsKey(v))
                    graph.get(v).remove(u);
            }
        }

        // Output safely to avoid Presentation Error
        // This avoids trailing newlines at the end of the file which cause PE
        for (int i = 0; i < results.size(); i++) {
            System.out.print(results.get(i));
            if (i < results.size() - 1) {
                System.out.println();
            }
        }
    }

    // Safely reads next line ignoring invisible BOM characters & blank lines
    static String nextValidLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("\uFEFF")) {
                line = line.substring(1);
            }
            if (!line.isEmpty()) {
                return line;
            }
        }
        return null;
    }

    static boolean canTravel(Map<String, Set<String>> graph, Map<String, Set<String>> restrictions, String src,
            String dst) {
        if (!graph.containsKey(src) || !graph.containsKey(dst))
            return false;
        if (src.equals(dst))
            return true;

        Set<String> blocked = restrictions.get(src);
        if (blocked != null && blocked.contains(dst))
            return false;

        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();

        visited.add(src);
        stack.push(src);

        while (!stack.isEmpty()) {
            String cur = stack.pop();

            if (cur.equals(dst))
                return true;

            for (String next : graph.getOrDefault(cur, Collections.emptySet())) {
                if (visited.contains(next))
                    continue;
                // If a path hits a restricted node, do not continue on this path
                if (blocked != null && blocked.contains(next))
                    continue;

                visited.add(next);
                stack.push(next);
            }
        }

        return false;
    }
}