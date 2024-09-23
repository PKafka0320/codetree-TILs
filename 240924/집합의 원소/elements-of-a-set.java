import java.io.*;
import java.util.*;

public class Main {
	static int N, M, roots[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		roots = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			if (op == 1) {
				System.out.println(find(node1) == find(node2) ? 1 : 0);
			} else {
				union(node1, node2);
			}
		}
	}
	
	public static int find(int node1) {
		if (roots[node1] == node1) return node1;
		return roots[node1] = find(roots[node1]);
	}
	
	public static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return;
		
		roots[root1] = root2;
	}

}