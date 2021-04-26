import java.util.*; 
public class LCA { 
  
    // ArrayList to store tree 
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>(); 
    static int dp[][], lev[], log; 
    
    // Pre-processing to calculate values of dp[][] 
    static void dfs(int u, int p) 
    { 
  
        // Using recursion formula to calculate 
        // the values of dp[][] 
        dp[u][0] = p; 
        for (int i = 1; i <= log; i++) 
            dp[u][i] = dp[dp[u][i - 1]][i - 1]; 
        for (int v : map.get(u)) { 
            if (v != p) { 
  
                // Calculating the level of each node 
                lev[v] = lev[u] + 1; 
                dfs(v, u); 
            } 
        } 
    } 
  
    // Function to return the LCA of nodes u and v 
    static int lca(int u, int v) 
    { 
        // The node which is present farthest 
        // from the root node is taken as u 
        // If v is farther from root node 
        // then swap the two 
        if (lev[u] < lev[v]) { 
            int temp = u; 
            u = v; 
            v = temp; 
        } 
  
        // Finding the ancestor of u 
        // which is at same level as v 
        for (int i = log; i >= 0; i--) { 
            if ((lev[u] - (int)Math.pow(2, i)) >= lev[v]) 
                u = dp[u][i]; 
        } 
  
        // If v is the ancestor of u 
        // then v is the LCA of u and v 
        if (u == v) 
            return u; 
  
        // Finding the node closest to the root which is 
        // not the common ancestor of u and v i.e. a node 
        // x such that x is not the common ancestor of u 
        // and v but dp[x][0] is 
        for (int i = log; i >= 0; i--) { 
            if (dp[u][i] != dp[v][i]) { 
                u = dp[u][i]; 
                v = dp[v][i]; 
            } 
        } 
  
        // Returning the first ancestor 
        // of above found node 
        return dp[u][0]; 
    } 
  
    // Driver code 
    public static void main(String args[]) 
    { 
  
        // Number of nodes 
        int n = 9; 
        
        // log(n) with base 2 
        log = (int)Math.ceil(Math.log(n) / Math.log(2)); 
        dp = new int[n + 1][log + 1]; 
  
        // Stores the level of each node 
        lev = new int[n + 1]; 
  
        // Initialising dp values with -1 
        for (int i = 0; i <= n; i++) 
            Arrays.fill(dp[i], -1); 
  
        // Add edges  
        dfs(1, 1); 
        System.out.println("The LCA of 6 and 9 is " + lca(6, 9)); 
        System.out.println("The LCA of 5 and 9 is " + lca(5, 9)); 
        System.out.println("The LCA of 6 and 8 is " + lca(6, 8)); 
        System.out.println("The LCA of 6 and 1 is " + lca(6, 1)); 
    } 
} 