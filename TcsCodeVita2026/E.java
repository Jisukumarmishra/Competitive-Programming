import java.util.Scanner;

public class E {
    static int N, M, T, I;
    static char[][] grid;
    static boolean[][][] valid;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static String foundKey = "";
    static boolean multipleKeys = false;

    static void dfs(int r, int c, int t, boolean[][] visited, StringBuilder currentKey) {
        if (multipleKeys) return;

        if (t == T) {
            if (foundKey.isEmpty()) {
                foundKey = currentKey.toString();
            } else if (!foundKey.equals(currentKey.toString())) {
                multipleKeys = true;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];

            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if (!visited[nr][nc] && valid[t + 1][nr][nc]) {
                    visited[nr][nc] = true;
                    currentKey.append(grid[nr][nc]);
                    
                    dfs(nr, nc, t + 1, visited, currentKey);
                    
                    currentKey.deleteCharAt(currentKey.length() - 1);
                    visited[nr][nc] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        N = sc.nextInt();
        M = sc.nextInt();

        grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = sc.next().charAt(0);
            }
        }

        T = sc.nextInt();
        I = sc.nextInt();

        valid = new boolean[T + 1][N][M];
        for (int t = 1; t <= T; t++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    valid[t][i][j] = true;
                }
            }
        }

        for (int i = 0; i < I; i++) {
            int t = sc.nextInt();
            int x1 = sc.nextInt() - 1;
            int y1 = sc.nextInt() - 1;
            int x2 = sc.nextInt() - 1;
            int y2 = sc.nextInt() - 1;
            
            for (int r = x1; r <= x2; r++) {
                for (int c = y1; c <= y2; c++) {
                    if (r >= 0 && r < N && c >= 0 && c < M) {
                        valid[t][r][c] = false;
                    }
                }
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (valid[1][r][c]) {
                    boolean[][] visited = new boolean[N][M];
                    visited[r][c] = true;
                    StringBuilder currentKey = new StringBuilder();
                    currentKey.append(grid[r][c]);
                    dfs(r, c, 1, visited, currentKey);
                }
            }
        }

        if (multipleKeys || foundKey.isEmpty()) {
            System.out.print("Not enough clues");
        } else {
            System.out.print(foundKey);
        }
        
        sc.close();
    }
}