import java.io.*;
import java.util.*;

public class Main {
	static class Work {
		int node, time;

		public Work(int node, int time) {
			this.node = node;
			this.time = time;
		}
	}
	static int N, times[], indegree[], maxPreTimes[];
	static List<Integer> edges[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		times = new int[N+1];
		indegree = new int[N+1];
		maxPreTimes = new int[N+1];
		edges = new ArrayList[N+1];

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			int preceedCount = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < preceedCount; j++) {
				int node = Integer.parseInt(st.nextToken());
				edges[node].add(i);
				indegree[i]++;
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) queue.add(i);
		}
		
		int answer = 0;
		while (!queue.isEmpty()) {
			int curNode = queue.poll();
			times[curNode] += maxPreTimes[curNode];
			answer = Math.max(answer, times[curNode]);
			
			for (int adNode : edges[curNode]) {
				maxPreTimes[adNode] = Math.max(maxPreTimes[adNode], times[curNode]);
				if (--indegree[adNode] == 0) {
					queue.add(adNode);
				}
			}
		}
		System.out.println(answer);
	}
}