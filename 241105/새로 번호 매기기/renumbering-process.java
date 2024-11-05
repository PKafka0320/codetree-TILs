import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] indegree = new int[N+1];
		List<Integer>[] edge = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edge[i] = new ArrayList<>();
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			
			edge[from].add(to);
			indegree[to]++;
		}
		
		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) pq.add(i);
		}
		
		int[] num = new int[N+1];
		int currentNum = N;
		int count = 0;
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			num[cur] = currentNum--;
			count++;
			
			for (int next : edge[cur]) {
				if (--indegree[next] == 0) {
					pq.add(next);
				}
			}
		}
		
		if (count != N) {
			System.out.println(-1);
		} else {
			StringBuilder answer = new StringBuilder();
			for (int i = 1; i <= N; i++) {
				answer.append(num[i]).append(" ");
			}
			System.out.println(answer.toString());
		}
	}
}