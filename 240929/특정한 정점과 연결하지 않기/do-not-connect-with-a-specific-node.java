import java.io.*;
import java.util.*;

public class Main {
	static int N, M, A, B, K, root[], size[];
	static boolean isRoot[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		size = new int[N+1];
		isRoot = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			root[i] = i;
			size[i] = 1;
			isRoot[i] = true;
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			union(node1, node2);
		}
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int rootA = find(A);
		int rootB = find(B);
		int count = size[rootA];
		
		List<Integer> sizes = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (i == rootA || i == rootB) continue;
			if (!isRoot[i]) continue;
			
			sizes.add(size[i]);
		}
		
		Collections.sort(sizes, Collections.reverseOrder());
		
		for (int i = 0; i < sizes.size(); i++) {
			if (i >= K) break;
			count += sizes.get(i);
		}
		
		System.out.println(count);
	}
	
	public static void union(int superNode, int subNode) {
		int superRoot = find(superNode);
		int subRoot = find(subNode);
		
		if (superRoot == subRoot) return;
		
		isRoot[subRoot] = false;
		size[superRoot] += size[subRoot]; 
		root[subRoot] = superRoot;
	}
	
	public static int find(int node) {
		if (root[node] == node) return node;
		return root[node] = find(root[node]);
	}

}