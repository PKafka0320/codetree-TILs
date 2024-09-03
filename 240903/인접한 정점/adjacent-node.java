import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] numbers, parents;
	static int[][] dp;
	static boolean[] visited;
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 정점의 개수
		numbers = new int[n + 1]; // [i]: i번 정점의 숫자
		parents = new int[n + 1]; // [i]: i번 정점의 부모 정점의 번호
		visited = new boolean[n + 1]; // [i] : i번 정점의 방문 여부
		edges = new ArrayList[n + 1]; // [i]: i번 정점과 연결되는 정점의 번호들
		dp = new int[n + 1][2]; // [i][j]: i번 정점의 선택 여부에 따른 숫자의 합 (j=0: 미선택, j=1: 선택) 
		
		/* 입력 및 초기화 */
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
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
		
		visited[1] = true;
		dfs(1);
		
		System.out.println(dp[1][0] > dp[1][1] ? dp[1][0] : dp[1][1]);
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			parents[ad] = node;
			visited[ad] = true;
			dfs(ad);
		}
		
		// 정점을 선택하지 않은 경우 다른 인접한 정점들은 선택하지 않아야 하고,
		// 정점을 선택하는 경우 다른 인접한 정점들은 선택되어야 한다.
		dp[node][1] = numbers[node]; // 정점을 선택한 경우의 숫자를 초기화
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			dp[node][0] += dp[ad][1];
			dp[node][1] += dp[ad][0];
		}
	}
}