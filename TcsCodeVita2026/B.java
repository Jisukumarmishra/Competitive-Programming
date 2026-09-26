package TcsCodeVita2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

class B {
    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int size = scanner.nextInt();
        if (size == -1) {
            return;
        }

        int[][] brickAt = new int[size][size];
        for (int[] row : brickAt) {
            Arrays.fill(row, -1);
        }

        List<Character> typeOfBrick = new ArrayList<>();
        List<Integer> sources = new ArrayList<>();
        List<Integer> destinations = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            String description = scanner.next();
            if (description == null) break;
            
            int column = 0;
            for (int index = 0; index < description.length();) {
                int length = 0;
                boolean hasDigit = false;
                
                // Parse the length of the brick segment securely
                while (index < description.length() && Character.isDigit(description.charAt(index))) {
                    length = length * 10 + description.charAt(index++) - '0';
                    hasDigit = true;
                }
                
                if (!hasDigit) length = 1;

                if (index < description.length()) {
                    char type = description.charAt(index++);
                    int brick = typeOfBrick.size();
                    typeOfBrick.add(type);
                    
                    if (type == 'S') {
                        sources.add(brick);
                    } else if (type == 'D') {
                        destinations.add(brick);
                    }

                    for (int cell = 0; cell < length; cell++) {
                        if (column < size) {
                            brickAt[row][column++] = brick;
                        }
                    }
                }
            }
        }

        if (sources.isEmpty() || destinations.isEmpty()) {
            System.out.print(-1); // Changed to print() to avoid Presentation Error
            return;
        }

        int brickCount = typeOfBrick.size();
        List<Set<Integer>> neighbors = new ArrayList<>();
        for (int brick = 0; brick < brickCount; brick++) {
            neighbors.add(new HashSet<>());
        }

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (brickAt[row][column] == -1) continue;
                
                if (row + 1 < size && brickAt[row + 1][column] != -1) {
                    connect(brickAt[row][column], brickAt[row + 1][column], typeOfBrick, neighbors);
                }
                if (column + 1 < size && brickAt[row][column + 1] != -1) {
                    connect(brickAt[row][column], brickAt[row][column + 1], typeOfBrick, neighbors);
                }
            }
        }

        int[] distance = new int[brickCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        
        for (int s : sources) {
            distance[s] = 0;
            deque.add(s);
        }

        // 0-1 BFS
        while (!deque.isEmpty()) {
            int current = deque.pollFirst();
            for (int next : neighbors.get(current)) {
                int breakCost = typeOfBrick.get(next) == 'G' ? 1 : 0;
                int candidate = distance[current] + breakCost;
                
                if (candidate < distance[next]) {
                    distance[next] = candidate;
                    if (breakCost == 0) {
                        deque.addFirst(next);
                    } else {
                        deque.addLast(next);
                    }
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for (int d : destinations) {
            minDistance = Math.min(minDistance, distance[d]);
        }
        
        // Changed to print() to avoid Presentation Error
        System.out.print(minDistance == Integer.MAX_VALUE ? -1 : minDistance); 
    }

    static void connect(int first, int second, List<Character> types, List<Set<Integer>> neighbors) {
        if (first == -1 || second == -1 || first == second || types.get(first) == 'R' || types.get(second) == 'R') {
            return;
        }
        neighbors.get(first).add(second);
        neighbors.get(second).add(first);
    }

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
                    if (line == null) {
                        return null; 
                    }
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            String str = next();
            if (str == null) return -1;
            return Integer.parseInt(str);
        }
    }
}