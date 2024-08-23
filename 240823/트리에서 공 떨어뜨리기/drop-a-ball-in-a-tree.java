import java.io.*;
import java.util.*;

public class Main {
	static int[] leftNode, rightNode;
	static int lastNode;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
     
        int n = Integer.parseInt(br.readLine());
        leftNode = new int[n];
        rightNode = new int[n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int left = Integer.parseInt(st.nextToken()) - 1;
        	int right = Integer.parseInt(st.nextToken()) - 1;
        	
        	leftNode[i] = left;
        	rightNode[i] = right;
        }
        
        long k = Long.parseLong(br.readLine());
        
        dropBall(0, k);
        
        System.out.println(lastNode + 1);
    }
    
    public static void dropBall(int node, long remainBall) {
    	int left = leftNode[node];
    	int right = rightNode[node];

    	if (left == -2 && right == -2) {
    		lastNode = node;
    		return;
    	}
    	else if (left == -2 || right == -2) {
    		if (left == -2) {
    			dropBall(right, remainBall);
    		} else if (right == -2) {
    			dropBall(left, remainBall);
    		}
    	} else {
    		if (remainBall % 2 == 0) {
    			dropBall(right, remainBall / 2);
    		} else {
    			dropBall(left, remainBall / 2 + 1);
    		}
    	}
    }
}

// import java.util.Scanner;

// public class Main {
//     public static final int MAX_N = 200000;
    
//     // 변수 선언
//     public static int n;
//     public static long k;
//     public static int[] leftNum = new int[MAX_N + 1];
//     public static int[] rightNum = new int[MAX_N + 1];
//     public static int ans;
    
//     // 트리의 루트 노드에서부터 시작해
//     // k번째 공이 어느 노드로 내려갈지 DFS로 계산합니다.
//     public static void dfs(int x, long ballNum) {
//         // 리프 노드에 도착하면 해당 위치가 답이 됩니다.
//         if(leftNum[x] == -1 && rightNum[x] == -1) {
//             ans = x;
//             return;
//         }
    
//         // 왼쪽 노드가 비어있다면 오른쪽으로 이동합니다.
//         if(leftNum[x] == -1)
//             dfs(rightNum[x], ballNum);
//         // 오른쪽 노드가 비어있다면 왼쪽으로 이동합니다.
//         else if(rightNum[x] == -1)
//             dfs(leftNum[x], ballNum);
//         // 홀수 개의 공이 남았다면
//         // 왼쪽으로 1개 더 공이 떨어지게 되며
//         // 우리는 왼쪽으로 이동해야 합니다.
//         else if(ballNum % 2 == 1)
//             dfs(leftNum[x], (ballNum + 1) / 2);
//         // 짝수 개의 공이 남았다면
//         // 왼쪽, 오른쪽 공이 동일하게 떨어지게 되며
//         // 우리는 오른쪽으로 이동해야 합니다.
//         else
//             dfs(rightNum[x], ballNum / 2);
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         // 각 노드의 왼쪽 자식노드와 오른쪽 자식노드를 입력받습니다.
//         for(int i = 1; i <= n; i++) {
//             leftNum[i] = sc.nextInt();
//             rightNum[i] = sc.nextInt();
//         }

//         k = sc.nextLong();

//         // 트리의 루트 노드에서부터 시작해
//         // k번째 공이 어느 노드로 내려갈지 DFS로 계산합니다.
//         dfs(1, k);

//         // 공이 마지막에 멈추는 노드의 번호를 출력합니다.
//         System.out.print(ans);
//     }
// }