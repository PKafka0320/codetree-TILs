import java.io.*;
import java.util.*;

public class Main {

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
		
		Queue<Integer> queue = new PriorityQueue<>();
		queue.add(N);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int next : edges[cur]) {
				if (maxCount[next] == -1 || maxCount[next] < maxCount[cur] + 1) {
					maxCount[next] = maxCount[cur] + 1;
					beforeNode[next] = cur;
					queue.add(next);
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