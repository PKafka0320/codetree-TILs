import java.io.*;
import java.util.*;

public class Main {
	static int N, M, roots[], counts[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		roots = new int[N+1];
		counts = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
			counts[i] = 1;
		}
		
		StringBuilder answer = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			char op = st.nextToken().charAt(0);
			if (op == 'x') {
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				
				union(node1, node2);
			} else {
				int node = Integer.parseInt(st.nextToken());
				answer.append(counts[roots[node]]).append("\n");
			}
		}
		System.out.println(answer);
	}
	
	public static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return;
		
		counts[root1] += counts[root2];
		roots[root2] = root1;
	}
	
	public static int find(int node) {
		if (roots[node] == node) return node;
		return roots[node] = find(roots[node]);
	}

}