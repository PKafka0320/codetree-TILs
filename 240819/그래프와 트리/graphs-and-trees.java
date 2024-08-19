import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visited;
	static List<Integer>[] graph;
	static boolean isTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 정점의 개수
		int m = Integer.parseInt(st.nextToken()); // 간선의 개수

		graph = new ArrayList[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			
			graph[node1].add(node2);
			graph[node2].add(node1);
		}
		
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			isTree = true;
			check(i, -1);
			if (isTree) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	// dfs 탐색
	// 이전 노드가 아닌다른 노드를 방문한 적이 있다면 트리가 아니다.
	public static void check(int node, int beforeNode) {
		for (int nextNode : graph[node]) {
			if (visited[nextNode]) {
				if (nextNode != beforeNode) {
					isTree= false;
				}
				continue;
			}
			visited[nextNode] = true;
			check(nextNode, node);
		}
	}

}