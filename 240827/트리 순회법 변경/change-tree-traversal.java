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

// import java.util.Scanner;

// public class Main {
//     public static final int MAX_N = 10000;
    
//     // 변수 선언
//     public static int n;
//     public static int[] preOrder = new int[MAX_N + 1];
//     public static int[] postOrder = new int[MAX_N + 1];
//     public static int cnt = 1;
    
//     // 트리의 전위 순회 결과를 바탕으로 해당 서브트리에서
//     // 루트, 왼쪽 서브트리, 오른쪽 서브트리를 잘 관리하여 DFS합니다.
//     // 전위 순회의 [l, r] 정보를 이용한 탐색을 진행합니다.
//     public static void dfs(int l, int r) {
//         // 탐색이 불가능한 종료조건에 해당합니다.
//         if(l > r) 
//             return;
    
//         // l = r이 되면
//         // 전위, 중위, 후위 순회 결과가 전부 단일 노드로서 동일해집니다.
//         if(l == r) {
//             postOrder[cnt++] = preOrder[l];
//             return;
//         }
    
//         // 전위 순회이므로
//         // preOrder[l]은 루트 노드임이 자명합니다.
//         // 전위 순회는 루트, 왼쪽 서브트리, 오른쪽 서브트리 순이고
//         // 이진 검색 트리를 기반으로 하고 있기에 
//         // 오른쪽 서브트리의 모든 노드는 루트 노드 값보다 커야하므로
//         // 최초로 루트 노드보다 값이 큰 index를 찾습니다.
//         int rightSubtreeSt = r + 1;
//         for(int i = l + 1; i <= r; i++) {
//             if(preOrder[l] < preOrder[i]) {
//                 rightSubtreeSt = i;
//                 break;
//             }
//         }
    
//         // 전위 순회의 왼쪽 서브 트리 정보를 이용하여 재귀적으로 탐색합니다.
//         dfs(l + 1, rightSubtreeSt - 1);
    
//         // 전위 순회의 오른쪽 서브 트리 정보를 이용하여 재귀적으로 탐색합니다.
//         dfs(rightSubtreeSt, r);
    
//         // 후위 순회의 경우 (왼쪽 서브 트리, 오른쪽 서브 트리, 현재 노드)
//         // 순으로 순서가 구성되어야 하므로
//         // 위의 두 재귀 함수 (왼쪽, 오른쪽) 탐색을 끝내고 난 이후에
//         // 현재 노드의 위치 값을 적어줍니다.
//         postOrder[cnt++] = preOrder[l];
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         // 트리를 전위순회한 결과를 입력받습니다.
//         for(int i = 1; i <= n; i++)
//             preOrder[i] = sc.nextInt();

//         // 트리의 전위 순회 결과를 바탕으로 해당 서브트리에서
//         // 루트, 왼쪽 서브트리, 오른쪽 서브트리를 잘 관리하여 DFS합니다.
//         dfs(1, n);

//         // 후위순회의 번호를 순서대로 출력합니다.
//         for(int i = 1; i <= n; i++)
//             System.out.println(postOrder[i]);
//     }
// }