import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] edges;
    static boolean[] visited;
    static int[] parents, maxLen;
    static int n, s, d, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        edges = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        parents = new int[n + 1];
        maxLen = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            
            edges[node1].add(node2);
            edges[node2].add(node1);
        }
        
        visited[s] = true;
        dfs(s);
        
        System.out.println(result);
    }
    
    public static void dfs(int node) {
        for (int next : edges[node]) {
            if (visited[next]) continue;
            visited[next] = true;
            parents[next] = node;
            dfs(next);
        }
        
        for (int next : edges[node]) {
            if (parents[node] == next) continue;
            maxLen[node] = Math.max(maxLen[node], maxLen[next] + 1);
            
            if (parents[node] == next || maxLen[next] < d) continue;
            result += 2;
        }
    }
}