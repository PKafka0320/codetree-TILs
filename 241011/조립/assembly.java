import java.io.*;
import java.util.*;

public class Main {
	static class Edge {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][N+1];
		int[] indegree = new int[N+1];
		List<Edge>[] edges = new ArrayList[N+1];
		
		for (int node = 1; node <= N; node++) {
			edges[node] = new ArrayList<>();
		}
		
		for (int edge = 0; edge < M; edge++) {
			st = new StringTokenizer(br.readLine());
			
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[from].add(new Edge(to, weight));
			indegree[to]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int node = 1; node <= N; node++) {
			if (indegree[node] == 0) {
				dp[node][node] = 1;
				queue.add(node);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (Edge edge : edges[cur]) {
				int ad = edge.node;
				int weight = edge.weight;
				
				for (int piece = 1; piece <= N; piece++) {
					dp[ad][piece] += dp[cur][piece] * weight;
				}
				
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}

		StringBuilder answer = new StringBuilder();
		for (int piece = 1; piece <= N; piece++) {
			if (dp[N][piece] > 0) {
				answer.append(piece).append(" ").append(dp[N][piece]).append("\n");
			}
		}
		System.out.println(answer.toString());
	}
}