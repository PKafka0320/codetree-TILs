import java.io.*;
import java.util.*;

public class Main{
	static int N, numbers[], parents[], dp[][];
	static List<Integer>[] edges;
	static List<Integer> select;
    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];
        edges = new ArrayList[N + 1];
        parents = new int[N + 1];
        dp = new int[N + 1][2];
        select = new ArrayList<>();
        
        for (int i = 1; i <= N; i++) {
        	edges[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
        	numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < N-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int node1 = Integer.parseInt(st.nextToken());
        	int node2 = Integer.parseInt(st.nextToken());
        	
        	edges[node1].add(node2);
        	edges[node2].add(node1);
        }
        
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
        
        if (dp[1][0] > dp[1][1]) {
        	dfsSelect(1, 0);
        } else {
        	dfsSelect(1, 1);
        }
        
        Collections.sort(select);
        for (int i : select) {
        	System.out.printf("%d ", i);
        }
    }
    
    public static void dfsSelect(int node, int print) {
    	if (print == 1) {
    		select.add(node);
    	}
    	
    	for (int ad : edges[node]) {
    		if (parents[node] == ad) continue;
    		dfsSelect (ad, 1- print);
    	}
    }
    
    public static void dfs(int node) {
    	for (int ad : edges[node]) {
    		if (parents[node] == ad) continue;
    		parents[ad] = node;
    		dfs(ad);
    	}
    	
    	dp[node][1] = numbers[node];
    	
    	for (int ad : edges[node]) {
    		if (parents[node] == ad) continue;
    		dp[node][1] += dp[ad][0];
    		dp[node][0] += Math.max(dp[ad][0], dp[ad][1]);
    	}
    }
    
}