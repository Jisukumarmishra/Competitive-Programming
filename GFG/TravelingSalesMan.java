public class TravelingSalesMan {
  class Solution {
    public int tsp(int[][] cost) {
        // code here
        int n = cost.length();
        
        boolean[] visited = new boolean[n];
        visited[0] = true; 

        return solve(0, visited, 1, cost);
        
    }
    
     private int solve(int curr, boolean[] visited, int count, int[][] cost) {

        int n = cost.length;

        
        if (count == n) {
            return cost[curr][0]; 
        }

        int ans = Integer.MAX_VALUE;

        for (int next = 0; next < n; next++) {

         if (!visited[next]) {

                visited[next] = true;

                int currCost = cost[curr][next]
                        + solve(next, visited, count + 1, cost);

                ans = Math.min(ans, currCost);

                visited[next] = false; 
            }
            
        }
        
        return ans;
     }
    
}
}
