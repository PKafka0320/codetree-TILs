import java.io.*;
import java.util.*;

public class Main {
	static int root[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		int lastA = -1;
		int lastB = -1;
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (find(a) == find(b)) {
				System.out.println("0");
				return;
			}
			
			if (lastA == -1 && lastB == -1) {
				lastA = a;
				lastB = b;
			} else {
				if (find(lastA) == find(a)) {
					union(lastA, a);
					union(lastB, b);
					lastA = a;
					lastB = b;
				} else if (find(lastB) == find(a)) {
					union(lastA, b);
					union(lastB, a);
					lastA = b;
					lastB = a;
				} else {
					if (find(lastB) == find(b)) {
						union(lastA, a);
						union(lastB, b);
						lastA = a;
						lastB = b;
					} else if (find(lastA) == find(b)) {
						union(lastA, b);
						union(lastB, a);
						lastA = b;
						lastB = a;
					} else {
						union(lastA, a);
						union(lastB, b);
						lastA = a;
						lastB = b;
					}
				}
			}
		}
		System.out.println("1");
	}
	
	public static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return;
		
		root[root1] = root2;
	}
	
	public static int find(int node) {
		if (root[node] == node) return node;
		return root[node] = find(root[node]);
	}
}