import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M1 = Integer.parseInt(st.nextToken());
		int M2 = Integer.parseInt(st.nextToken());
		
		int[] indegree = new int[N+1];
		List<Integer>[] edges = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		for (int i = 0; i < M2; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		int count = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			count++;
			
			for (int ad : edges[cur]) {
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}
		
		System.out.println(count == N ? "Yes" : "No");
	}

}