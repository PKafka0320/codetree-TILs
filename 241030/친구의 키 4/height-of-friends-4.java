import java.io.*;
import java.util.*;

public class Main {
	static int N, M, indegree[], query[][];
	static List<Integer> edges[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		query = new int[M+1][2];
		indegree = new int[N+1];
		edges = new ArrayList[N+1];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			query[i][0] = from;
			query[i][1] = to;
		}
		
		int lo = 1, hi = M;
		int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if(cycleExist(mid)) {
                ans = mid;
                hi = mid - 1;
            }
            else {
                lo = mid + 1;
            }
        }
		
		System.out.println(ans == 0 ? "Consistent" : ans);
	}
	
	public static boolean cycleExist(int limit) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			indegree[i] = 0;
			edges[i] = new ArrayList<>();
			visited[i] = false;
		}
		
		for (int i = 1; i <= limit; i++) {
			int from = query[i][0];
			int to = query[i][1];
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			visited[cur] = true;
			
			for (int next : edges[cur]) {
				if (--indegree[next] == 0) {
					queue.add(next);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				return true;
			}
		}
		
		return false;
	}
}