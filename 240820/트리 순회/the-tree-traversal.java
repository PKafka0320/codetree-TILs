import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static char[] tree;
	static Map<Character, Integer> nti;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		tree = new char[1000];
		nti = new HashMap<>(); // node to index
		
		nti.put('A', 1);
		tree[1] = 'A';
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			char leftChild = st.nextToken().charAt(0);
			char rightChild = st.nextToken().charAt(0);
			
			int index = nti.get(parent);
			int leftIndex = index * 2;
			int rightIndex = index * 2 + 1;
			
			nti.put(leftChild, leftIndex);
			tree[leftIndex] = leftChild;
		
			nti.put(rightChild, rightIndex);
			tree[rightIndex] = rightChild;
		}
		
		preorder(1);
		System.out.println();
		inorder(1);
		System.out.println();
		postorder(1);
		System.out.println();
	}
	
	public static void preorder(int index) {
		if (tree[index] == '.') return;
		
		System.out.print(tree[index]);
		preorder(index * 2);
		preorder(index * 2 + 1);
	}
	
	public static void inorder(int index) {
		if (tree[index] == '.') return;
		
		inorder(index * 2);
		System.out.print(tree[index]);
		inorder(index * 2 + 1);
	}
	
	public static void postorder(int index) {
		if (tree[index] == '.') return;
		
		postorder(index * 2);
		postorder(index * 2 + 1);
		System.out.print(tree[index]);
	}

}