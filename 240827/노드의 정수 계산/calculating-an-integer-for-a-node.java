import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] tree;
	static int[] datas;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		tree = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			tree[i] = new ArrayList<>();
		}
		datas = new int[n];
		dp = new int[n];
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int data = Integer.parseInt(st.nextToken());
			int parentIndex = Integer.parseInt(st.nextToken()) - 1;
			
			tree[parentIndex].add(i);
			
			datas[i] = (type == 1) ? data : -data;
		}
		
		dfs(0);
		System.out.println(dp[0]);
	}
	
	public static void dfs(int index) {
		for (int childIndex : tree[index]) {
			dfs(childIndex);
		}
		
		dp[index] = datas[index];
		for (int childIndex : tree[index]) {
			if (dp[childIndex] > 0) {
				dp[index] += dp[childIndex];
			}
		}
	}
	
}