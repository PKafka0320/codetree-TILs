import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] dp = new int[N+1];
		int[] indegree = new int[N+1];
		List<Integer>[] edges = new ArrayList[N+1];
		int MOD = (int) 1e9 + 7;
		
		for (int node = 1; node <= N; node++) {
			edges[node] = new ArrayList<>();
		}
		
		for (int edge = 0; edge < M; edge++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(1);
		dp[1] = 1;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int ad : edges[cur]) {
				dp[ad] = (dp[ad] + dp[cur]) % MOD;
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}
		
		System.out.println(dp[N]);
	}
}