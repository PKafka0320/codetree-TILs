import java.io.*;
import java.util.*;

class Node {
	char value;
	Node leftChild, rightChild;
	
	public Node(char value) {
		this.value = value;
	}
}

public class Main {
	static int n;
	static List<Node> tree;
	static Map<Character, Integer> vti;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		vti = new HashMap<>(); // node to index
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			char leftChild = st.nextToken().charAt(0);
			char rightChild = st.nextToken().charAt(0);
			
			int index = vti.getOrDefault(parent, tree.size());
			if (index == tree.size()) {
				vti.put(parent, index);
				tree.add(new Node(parent));
			}
			
			Node parentNode = tree.get(index);
			
			if (leftChild != '.') {
				int leftIndex = vti.getOrDefault(leftChild, tree.size());
				if (leftIndex == tree.size()) {
					vti.put(leftChild, leftIndex);
					tree.add(new Node(leftChild));
				}
				
				Node leftNode = tree.get(leftIndex);
				parentNode.leftChild = leftNode;
			}
			if (rightChild != '.') {
				int rightIndex = vti.getOrDefault(rightChild, tree.size());
				if (rightIndex == tree.size()) {
					vti.put(rightChild, rightIndex);
					tree.add(new Node(rightChild));
				}
				
				Node rightNode = tree.get(rightIndex);
				parentNode.rightChild = rightNode;
			}
		}
		
		preorder('A');
		System.out.println();
		inorder('A');
		System.out.println();
		postorder('A');
		System.out.println();
	}
	
	public static void preorder(char value) {
		int index = vti.get(value);
		Node node = tree.get(index);
		
		System.out.print(value);
		if (node.leftChild != null) preorder(node.leftChild.value);
		if (node.rightChild != null) preorder(node.rightChild.value);
	}
	
	public static void inorder(char value) {
		int index = vti.get(value);
		Node node = tree.get(index);
		
		if (node.leftChild != null) inorder(node.leftChild.value);
		System.out.print(value);
		if (node.rightChild != null) inorder(node.rightChild.value);
	}
	
	public static void postorder(char value) {
		int index = vti.get(value);
		Node node = tree.get(index);
		
		if (node.leftChild != null) postorder(node.leftChild.value);
		if (node.rightChild != null) postorder(node.rightChild.value);
		System.out.print(value);
	}

}