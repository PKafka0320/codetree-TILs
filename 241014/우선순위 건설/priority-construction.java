import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] indegree = new int[N+1];
		int[] time = new int[N+1];
		int[] nextTime = new int[N+1];
		List<Integer>[] edges = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			time[i] = t;
			
			int pre = Integer.parseInt(st.nextToken());
			while (pre != -1) {
				edges[pre].add(i);
				indegree[i]++;
				pre = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) queue.add(i);
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			time[cur] += nextTime[cur];
			
			for (int ad : edges[cur]) {
				nextTime[ad] = Math.max(nextTime[ad], time[cur]);
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			answer.append(time[i]).append("\n");
		}
		System.out.println(answer.toString());
	}
}