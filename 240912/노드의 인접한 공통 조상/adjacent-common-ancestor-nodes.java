import java.io.*;
import java.util.*;

public class Main {
	static int N, depth[], parents[];
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/* input & initialize */
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		depth = new int[N+1];
		parents = new int[N+1];
		int[] indegree = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			
			edges[parent].add(child);
			indegree[child]++;
		}
		
		st = new StringTokenizer(br.readLine());
		int fnode1 = Integer.parseInt(st.nextToken());
		int fnode2 = Integer.parseInt(st.nextToken());
		int root = -1;
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				root = i;
				break;
			}
		}
		
		/* logic */
		depth[root] = 1;
		dfs(root);
		
		while (depth[fnode1] != depth[fnode2]) {
			if (depth[fnode1] > depth[fnode2]) {
				fnode1 = parents[fnode1];
			} else {
				fnode2 = parents[fnode2];
			}
		}
		
		while (fnode1 != fnode2) {
			fnode1 = parents[fnode1];
			fnode2 = parents[fnode2];
		}
		
		System.out.println(fnode1);
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			depth[ad] = depth[node] + 1;
			parents[ad] = node;
			dfs(ad);
		}
	}
	
}