import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int data, index;
		
		public Node(int type, int data, int index) {
			if (type == 1) {
				this.data = data;
			} else {
				this.data = data * -1;
			}
			
			this.index = index;
		}
	}
	static List<Node>[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		tree = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int data = Integer.parseInt(st.nextToken());
			int parentIndex = Integer.parseInt(st.nextToken()) - 1;
			
			tree[parentIndex].add(new Node(type, data, i));
		}
		
		Node root = new Node(1, 0, 0);
		int sum = dfs(root);
		System.out.println(sum);
	}
	
	public static int dfs(Node node) {
		int sum = node.data;
		
		for (Node child : tree[node.index]) {
			sum += dfs(child);
		}
		
		return sum > 0 ? sum : 0;
	}
	
}