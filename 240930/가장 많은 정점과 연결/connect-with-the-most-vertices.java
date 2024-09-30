import java.io.*;
import java.util.*;

public class Main {
	static class Pair implements Comparable<Pair> {
		int index, minNumber;

		public Pair(int index, int minNumber) {
			this.index = index;
			this.minNumber = minNumber;
		}
		
		@Override
		public int compareTo(Pair o) {
			return this.minNumber - o.minNumber;
		}
	}
	static int N, M, K, A, B, roots[], minNumbers[];
	static boolean isRoots[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		roots = new int[N+1];
		minNumbers = new int[N+1];
		isRoots = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
			isRoots[i] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			minNumbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			union(node1, node2);
		}
		
		List<Pair> roots = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (isRoots[i]) {
				roots.add(new Pair(i, minNumbers[i]));
			}
		}
		Collections.sort(roots);
		
		long result = 0;
		int minNumber = roots.get(0).minNumber;
		for (int i = 1; i < roots.size(); i++) {
			result += minNumber + roots.get(i).minNumber;
		}
		
		System.out.println(result > K ? "NO" : result);
	}
	
	public static boolean union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return false;
		
		isRoots[root1] = false;
		minNumbers[root2] = Math.min(minNumbers[root1], minNumbers[root2]);
		roots[root1] = root2;
		return true;
	}
	
	public static int find(int node) {
		if (roots[node] == node) return node;
		return roots[node] = find(roots[node]);
	}

}