import java.io.*;
import java.util.*;

public class Main {
	static class Work implements Comparable<Work> {
		int node, time;

		public Work(int node, int time) {
			this.node = node;
			this.time = time;
		}
		
		@Override
		public int compareTo(Work o) {
			return this.time - o.time;
		}
	}
	static int N, times[], indegree[];
	static List<Integer> edges[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		times = new int[N+1];
		indegree = new int[N+1];
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
		
		Queue<Work> queue = new PriorityQueue<>();
		Queue<Work> nextQueue = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) queue.add(new Work(i, times[i]));
		}
		
		int answer = 0;
		while (!queue.isEmpty() || !nextQueue.isEmpty()) {
			Work curWork = queue.poll();
			int curNode = curWork.node;
			int curTime = curWork.time;
			answer += curTime;
			
			for (int ad : edges[curNode]) {
				if (--indegree[ad] == 0) {
					nextQueue.add(new Work(ad, times[ad]));
				}
			}
			
			while (!queue.isEmpty()) {
				Work remainWork = queue.poll();
				int remainNode = remainWork.node;
				int remainTime = remainWork.time;
				
				nextQueue.add(new Work(remainNode, remainTime - curTime));
			}
			
			Queue<Work> temp = queue;
			queue = nextQueue;
			nextQueue = temp;
		}
		System.out.println(answer);
	}
}