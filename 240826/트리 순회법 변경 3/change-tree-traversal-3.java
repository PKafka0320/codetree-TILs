import java.io.*;
import java.util.*;

public class Main {
	static int[] inorder, postorder, preorder;
	static int index, n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		preorder = new int[n];
		inorder = new int[n];
		postorder = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, n - 1, 0, n - 1);
		index = 0;
		
		for (int i = 0; i < n; i++) {
			System.out.printf("%d ", preorder[i]);
		}
	}
	
	public static void dfs(int ps, int pe, int is, int ie) {
		if (ps > pe) return;
		
		preorder[index++] = postorder[pe];
		
		if (ps == pe) return;
		
		int im = -1;
		for (int i = is; i <= ie; i++) {
			if (inorder[i] != postorder[pe]) continue;
			im = i;
			break;
		}
		
		int ls = im - is - 1;
		int rs = ie - im;
		
		dfs(ps, ps + ls, is, im - 1);
		dfs(pe - rs, pe - 1, im + 1, ie);
	}
}

// import java.util.Scanner;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언
//     public static int n;
//     public static int[] preOrder = new int[MAX_N + 1];
//     public static int[] inOrder = new int[MAX_N + 1];
//     public static int[] postOrder = new int[MAX_N + 1];
//     public static int cnt = 1;
    
//     // 트리의 후위 순회, 중위 순회 결과를 바탕으로 해당 서브트리에서
//     // 왼쪽 서브트리, 오른쪽 서브트리, 루트를 잘 관리하여 DFS합니다.
//     // 후위 순회의 [l, r] 정보와
//     // 중위 순회의 [l2, r2] 정보를 이용한 탐색을 진행합니다.
//     public static void dfs(int l, int r, int l2, int r2) {
//         // 탐색이 불가능한 종료조건에 해당합니다.
//         if(l > r) 
//             return;
    
//         // l = r이 되면
//         // 전위, 중위, 후위 순회 결과가 전부 단일 노드로서 동일해집니다.
//         if(l == r) {
//             preOrder[cnt++] = postOrder[l];
//             return;
//         }
    
//         // 후위 순회는 (왼쪽 서브 트리, 오른쪽 서브 트리, 현재 노드)
//         // 중위 순회는 (왼쪽 서브 트리, 현재 노드, 오른쪽 서브 트리)
//         // 순이므로 후위 순회의 맨 뒤 노드인 postOrder[r]과 일치하는
//         // 중위 순회의 노드인 inOrder[x]를 찾으면
//         // 각각의 후위 순회 / 중위 순회 정보 중
//         // 왼쪽 서브 트리 정보와 오른쪽 서브 트리 정보를 구분지어줄 수 있습니다.
//         int rootNum = 0;
//         for(int i = l2; i <= r2; i++) {
//             if(postOrder[r] == inOrder[i])
//                 rootNum = i;
//         }
    
//         // 왼쪽 서브트리  현재 노드   오른쪽 서브트리 
//         // --------- --------  ------------
//         // [l2, ..., rootNum, ..., ..., r2]
//         // 왼쪽 / 오른쪽 서브 트리의 크기를 계산합니다.
//         int lefSz = rootNum - l2;
//         int rigSz = r2 - rootNum;
    
//         // 전위 순회의 경우 (현재 노드, 왼쪽 서브 트리, 오른쪽 서브 트리)
//         // 순으로 순서가 구성되어야 하므로
//         // 위의 두 재귀 함수 (왼쪽, 오른쪽) 탐색을 진행하기 전에
//         // 현재 노드의 위치 값을 적어줍니다.
//         preOrder[cnt++] = postOrder[r];
    
//         // 후위 순회 / 중위 순회의 왼쪽 서브 트리 정보를 이용하여 재귀적으로 탐색합니다.
//         dfs(l, l + lefSz - 1, l2, rootNum - 1);
    
//         // 후위 순회 / 중위 순회의 오른쪽 서브 트리 정보를 이용하여 재귀적으로 탐색합니다.
//         dfs(l + lefSz, r - 1, rootNum + 1, r2);
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         // 트리를 중위 순회한 결과와 후위 순회한 결과를 입력받습니다.
//         for(int i = 1; i <= n; i++)
//             inOrder[i] = sc.nextInt();

//         for(int i = 1; i <= n; i++)
//             postOrder[i] = sc.nextInt();

//         // 트리의 후위 순회, 중위 순회 결과를 바탕으로 해당 서브트리에서
//         // 왼쪽 서브트리, 오른쪽 서브트리, 루트를 잘 관리하여 DFS합니다.
//         dfs(1, n, 1, n);

//         // 전위순회의 번호를 순서대로 출력합니다.
//         for(int i = 1; i <= n; i++)
//             System.out.print(preOrder[i] + " ");
//     }
// }