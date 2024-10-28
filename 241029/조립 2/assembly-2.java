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
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			edge[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < k; j++) {
				int sub = Integer.parseInt(st.nextToken());
				edge[sub].add(a);
				indegree[a]++;
			}
		}
		
		int L = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < L; i++) {
			int l = Integer.parseInt(st.nextToken());
			queue.add(l);
		}
		
		int count = 0;
		List<Integer> parts = new ArrayList<>();
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			count++;
			parts.add(cur);
			
			for (int ad : edge[cur]) {
				if (--indegree[ad] == 0 && !parts.contains(ad)) queue.add(ad);
			}
		}
		Collections.sort(parts);
		
		StringBuilder answer = new StringBuilder();
		answer.append(count).append("\n");
		for (int part : parts) {
			answer.append(part).append(" ");
		}
		System.out.println(answer.toString());
	}

}