import java.io.*;
import java.util.*;

public class Main {
	static int N, M, remains, root[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		remains = N;
		
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		StringBuilder answer = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			for (int idx = start+1; idx <= end; idx++) {
				union(start, idx);
			}
			answer.append(remains).append("\n");
		}
		System.out.println(answer.toString());
	}
	
	public static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return;
		
		if (root2 == node2) {
			remains--;
		}
		root[root2] = root1;
	}
	
	public static int find(int node) {
		if (root[node] == node) return node;
		return root[node] = find(root[node]);
	}
}