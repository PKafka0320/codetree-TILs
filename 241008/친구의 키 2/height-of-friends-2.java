import java.io.*;
import java.util.*;

public class Main {
	static int N, M, indegree[];
	static List<Integer> edges[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1];
		edges = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) queue.add(i);
		}

		int count = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			count++;
			
			for (int next : edges[cur]) {
				if (--indegree[next] <= 0) queue.add(next);
			}
		}

		System.out.println(count == N ? "Consistent" : "Inconsistent");
	}
}