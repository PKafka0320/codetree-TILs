import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer> edges[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] indegree = new int[N+1];
		edges = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges[from].add(to);
			
			if (cycle(to)) {
				System.out.println(i);
				return;
			}
		}
		System.out.println("Consistent");
	}
	
	public static boolean cycle(int start) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(start);
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int next : edges[cur]) {
				if (visited[next]) {
					return true;
				}
				visited[next] = true;
				queue.add(next);
			}
		}
		
		return false;
	}

}