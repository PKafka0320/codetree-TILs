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

// import java.util.Scanner;

// public class Main {
//     public static final int MAX_N = 26;
    
//     // 변수 선언
//     public static int n;
//     public static int[] leftNum = new int[MAX_N];
//     public static int[] rightNum = new int[MAX_N];
    
//     public static void preOrder(int x) {
//         // -1이면 존재하지 않으므로 빠져나갑니다.
//         if(x == -1)
//             return;
    
//         System.out.print((char)(x + 'A'));
//         preOrder(leftNum[x]);
//         preOrder(rightNum[x]);
//     }
    
//     public static void inOrder(int x) {
//         // -1이면 존재하지 않으므로 빠져나갑니다.
//         if(x == -1)
//             return;
    
//         inOrder(leftNum[x]);
//         System.out.print((char)(x + 'A'));
//         inOrder(rightNum[x]);
//     }
    
//     public static void postOrder(int x) {
//         // -1이면 존재하지 않으므로 빠져나갑니다.
//         if(x == -1)
//             return;
    
//         postOrder(leftNum[x]);
//         postOrder(rightNum[x]);
//         System.out.print((char)(x + 'A'));
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         // 자식이 없는 경우 -1을 넣어줍니다.
//         for(int i = 0; i < n; i++)
//             leftNum[i] = rightNum[i] = -1;

//         for(int i = 0; i < n; i++) {
//             char x = sc.next().charAt(0);
//             char l = sc.next().charAt(0);
//             char r = sc.next().charAt(0);

//             if(l != '.')
//                 leftNum[x - 'A'] = l - 'A';
//             if(r != '.')
//                 rightNum[x - 'A'] = r - 'A';
//         }

//         // 전위 순회를 진행합니다.
//         preOrder(0);
//         System.out.println();
//         // 중위 순회를 진행합니다.
//         inOrder(0);
//         System.out.println();
//         // 후위 순회를 진행합니다.
//         postOrder(0);
//         System.out.println();
//     }
// }