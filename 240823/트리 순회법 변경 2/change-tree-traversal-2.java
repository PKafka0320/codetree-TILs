import java.io.*;
import java.util.*;

public class Main {
	static int[] preorder, inorder, postorder;
	static int n, postIdx;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        preorder = new int[n];
        inorder = new int[n];
        postorder = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	preorder[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	inorder[i] = Integer.parseInt(st.nextToken());
        }
        
        dfs(0, n - 1, 0, n - 1);
        
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
        	answer.append(postorder[i]).append(" ");
        }
        System.out.println(answer);
    }
    
    public static void dfs(int ls, int le, int rs, int re) {
    	// 탐색이 불가능한 경우 종료
    	if (ls > le) return;
    	
    	// 노드가 1개라면 전위, 중위, 후위 순회 결과가 단일 노드로서 동일해진다.
    	if (ls == le) {
    		postorder[postIdx++] = preorder[ls];
    		return;
    	}
    	
    	// 서브 트리의 루트 노드 탐색
    	int rootIdx = -1;
    	for (int i = rs; i <= re; i++) {
    		if (preorder[ls] == inorder[i]) {
    			rootIdx = i;
    			break;
    		}
    	}
    	
    	// 서브 트리의 크기 계산
    	int leftSz = rootIdx - rs;
    	
    	// 서브 트리 탐색
    	dfs(ls + 1, ls + leftSz, rs, rootIdx - 1); // 왼쪽 서브 트리
    	dfs(ls + 1 + leftSz, le, rootIdx + 1, re); // 오른쪽 서브 트리
    	
    	// 루트 노드 추가
    	postorder[postIdx++] = preorder[ls];
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
    
//     // 트리의 전위 순회, 중위 순회 결과를 바탕으로 해당 서브트리에서
//     // 왼쪽 서브트리, 오른쪽 서브트리, 루트를 잘 관리하여 DFS합니다.
//     // 전위 순회의 [l, r] 정보와
//     // 중위 순회의 [l2, r2] 정보를 이용한 탐색을 진행합니다.
//     public static void dfs(int l, int r, int l2, int r2) {
//         // 탐색이 불가능한 종료조건에 해당합니다.
//         if(l > r) 
//             return;
    
//         // l = r이 되면
//         // 전위, 중위, 후위 순회 결과가 전부 단일 노드로서 동일해집니다.
//         if(l == r) {
//             postOrder[cnt++] = preOrder[l];
//             return;
//         }
    
//         // 전위 순회는 (현재 노드, 왼쪽 서브 트리, 오른쪽 서브 트리)
//         // 중위 순회는 (왼쪽 서브 트리, 현재 노드, 오른쪽 서브 트리)
//         // 순이므로 전위 순회의 맨 앞 노드인 preOrder[l]과 일치하는
//         // 중위 순회의 노드인 inOrder[x]를 찾으면
//         // 각각의 전위 순회 / 중위 순회 정보 중
//         // 왼쪽 서브 트리 정보와 오른쪽 서브 트리 정보를 구분지어줄 수 있습니다.
//         int rootNum = 0; // 중위 순회 결과에서 현재 노드 위치를 찾아줍니다.
//         for(int i = l2; i <= r2; i++) {
//             if(preOrder[l] == inOrder[i])
//                 rootNum = i;
//         }
    
//         // 왼쪽 서브트리  현재 노드   오른쪽 서브트리 
//         // --------- --------  ------------
//         // [l2, ..., rootNum, ..., ..., r2]
//         // 왼쪽 / 오른쪽 서브 트리의 크기를 계산합니다.
//         int lefSz = rootNum - l2;
//         int rigSz = r2 - rootNum;
    
//         // 전위 순회 / 중위 순회의 왼쪽 서브 트리 정보를 이용하여 재귀적으로 탐색합니다.
//         dfs(l + 1, l + lefSz, l2, rootNum - 1);
    
//         // 전위 순회 / 중위 순회의 오른쪽 서브 트리 정보를 이용하여 재귀적으로 탐색합니다.
//         dfs(l + lefSz + 1, r, rootNum + 1, r2);
    
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

//         // 트리를 전위 순회한 결과와 중위 순회한 결과를 입력받습니다.
//         for(int i = 1; i <= n; i++)
//             preOrder[i] = sc.nextInt();

//         for(int i = 1; i <= n; i++)
//             inOrder[i] = sc.nextInt();

//         // 트리의 전위 순회, 중위 순회 결과를 바탕으로 해당 서브트리에서
//         // 왼쪽 서브트리, 오른쪽 서브트리, 루트를 잘 관리하여 DFS합니다.
//         dfs(1, n, 1, n);

//         // 후위순회의 번호를 순서대로 출력합니다.
//         for(int i = 1; i <= n; i++)
//             System.out.print(postOrder[i] + " ");
//     }
// }