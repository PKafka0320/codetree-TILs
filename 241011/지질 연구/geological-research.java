import java.io.*;
import java.util.*;

public class Main {
	static class Pressure {
		int level, count;

		public Pressure(int level, int count) {
			this.level = level;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Pressure[] dp = new Pressure[N+1];
		int[] indegree = new int[N+1];
		List<Integer>[] edges = new ArrayList[N+1];
		
		for (int node = 1; node <= N; node++) {
			edges[node] = new ArrayList<>();
			dp[node] = new Pressure(0, 0);
		}
		
		for (int edge = 0; edge < M; edge++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int node = 1; node <= N; node++) {
			if (indegree[node] == 0) {
				dp[node].level = 1;
				queue.add(node);
			}
		}
		
		int max = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (dp[cur].count >= 2) {
				dp[cur].level++;
			}
			max = Math.max(max, dp[cur].level);
			
			for (int ad : edges[cur]) {
				if (dp[ad].level < dp[cur].level) {
					dp[ad].level = dp[cur].level;
					dp[ad].count = 1;
				} else if (dp[ad].level == dp[cur].level) {
					dp[ad].count++;
				}
				
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}
		
		System.out.println(max);
	}
}