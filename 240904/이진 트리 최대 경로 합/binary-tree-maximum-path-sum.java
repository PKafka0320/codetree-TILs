import java.io.*;
import java.util.*;

public class Main{
	static List<Integer>[] edges;
	static int n, numbers[], parents[], dp[][];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        
        edges = new ArrayList[n + 1]; // [i]: i번 정점과 연결된 정점 번호
        numbers = new int[n + 1]; // [i]: i번 정점의 숫자
        parents = new int[n + 1]; // [i]: i번 정점의 부모 정점 번호
        dp = new int[n + 1][2]; // [i][j]: i번 정점의 선택 여부(j=0:미선택, j=1:선택)에 따른 최댓값
        
        /* 입력  */
        for (int i = 1; i <= n; i++) {
        	edges[i] = new ArrayList<>();
        }
        
        for (int i = 0 ; i < n - 1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int node1 = Integer.parseInt(st.nextToken());
        	int node2 = Integer.parseInt(st.nextToken());
        	
        	edges[node1].add(node2);
        	edges[node2].add(node1);
        }
        
        for (int i = 1; i <= n; i++) {
        	numbers[i] = Integer.parseInt(br.readLine());
        	dp[i][0] = -1000;
        }
        
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
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
    		dp[node][0] = Math.max(dp[node][0], Math.max(dp[ad][0], dp[ad][1]));
    		dp[node][1] = Math.max(dp[node][1], dp[node][1] + dp[ad][1]);
    	}
    }
    
}