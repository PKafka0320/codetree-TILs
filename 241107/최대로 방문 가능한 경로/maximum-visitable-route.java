import java.io.*;
import java.util.*;

public class Main {
	static class Pair implements Comparable<Pair> {
		int node, count;

		public Pair(int node, int count) {
			super();
			this.node = node;
			this.count = count;
		}
		
		@Override
		public int compareTo(Pair o) {
			return this.node - o.node;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] edges = new ArrayList[N+1];
		int[] maxCount = new int[N+1];
		int[] beforeNode = new int[N+1];
		
		for (int i = 0; i <= N; i++) {
			edges[i] = new ArrayList<>();
			beforeNode[i] = -1;
			maxCount[i] = -1;
		}
		maxCount[N] = 1;
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			edges[from].add(to);
		}
		
		Queue<Pair> queue = new PriorityQueue<>();
		queue.add(new Pair(N, 1));
		
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			
			if (cur.count != maxCount[cur.node]) continue;
			
			for (int next : edges[cur.node]) {
				if (maxCount[next] == -1 || maxCount[next] < maxCount[cur.node] + 1) {
					maxCount[next] = maxCount[cur.node] + 1;
					beforeNode[next] = cur.node;
					queue.add(new Pair(next, maxCount[next]));
				}
			}
		}
		
		if (beforeNode[1] == -1) {
			System.out.println(-1);
			return;
		}
		
		StringBuilder answer = new StringBuilder();
		answer.append(maxCount[1]).append("\n");
		int current = 1;
		while (current != -1) {
			answer.append(current).append(" ");
			current = beforeNode[current];
		}
		System.out.println(answer.toString());
	}
}