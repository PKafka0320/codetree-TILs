import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int number;
		Node left, right;
		
		public Node(int number) {
			this.number = number;
		}
	}
	static int n;
	static int[] preOrder;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		preOrder = new int[n];
		for (int i = 0; i < n; i++) {
			preOrder[i] = Integer.parseInt(br.readLine());
		}
		
		Node root = makeTree(0, n - 1);
		
		postOrder(root);
	}
	
	public static void postOrder(Node node) {
		if (node.left != null) postOrder(node.left);
		if (node.right != null) postOrder(node.right);
		
		System.out.println(node.number);
	}
	
	public static Node makeTree(int start, int end) {
		if (end < start) return null;
		
		Node root = new Node(preOrder[start]);
		int left = start;
		
		for (int i = start + 1; i <= end; i++) {
			if (preOrder[i] >= root.number) break;
			left = i;
		}
		
		root.left = makeTree(start + 1, left);
		root.right = makeTree(left + 1, end);
		
		return root;
	}
	
}