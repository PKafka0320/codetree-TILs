import java.io.*;
import java.util.*;

public class Main {
	static int size, currentPointer;
	static int[] tree, inorder;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int k = Integer.parseInt(br.readLine());
		size = (int) Math.pow(2, k);
		tree = new int[size]; // 생성할 트리 정보
		inorder = new int[size]; // 중위 탐색 결과 정보
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size - 1; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		
		currentPointer = 0; // 현재 추가할 중위 탐색 결과의 위치
		inorderSet(1); // 중위 탐색
		
        // 출력
		StringBuilder answer = new StringBuilder();
		for (int i = 1, depth = 1; i < size; i++) {
            // 트리의 깊이가 변하는지 확인
			if (i == depth * 2) {
				answer.append("\n");
				depth *= 2; // 현재 깊이 갱신
			}
			answer.append(tree[i]).append(" ");
		}
		System.out.println(answer);
	}
	
	public static void inorderSet(int index) {
		if (index * 2 >= size) { // 리프노드
            // 중위 탐색 결과의 현재 위치값을 저장
			tree[index] = inorder[currentPointer++];
		} else {
            // 중위 탐색을 진행하면서 트리 생성
			inorderSet(index * 2);
			tree[index] = inorder[currentPointer++];
			inorderSet(index * 2 + 1);
		}
	}

}

// import java.util.Scanner;

// public class Main {
//     public static final int MAX_N = 1024;
    
//     // 변수 선언
//     public static int k, n;
//     public static int[] a = new int[MAX_N + 1];
//     public static int[] treeNum = new int[MAX_N + 1];
//     public static int cnt = 1;
    
//     // 중위 순회를 진행하여 각 트리의 위치에 맞는 번호를 채워 넣습니다.
//     public static void inOrder(int x) {
//         // n보다 큰 노드는 없습니다. 빠져나갑니다.
//         if(x > n)
//             return;
    
//         inOrder(x * 2);
//         treeNum[x] = a[cnt++];
//         inOrder(x * 2 + 1);
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         k = sc.nextInt();

//         // 2^i은 (1 << i)로 쉽계 계산이 가능합니다.
//         n = (1 << k) - 1;

//         // a라는 배열에 중위 순회 순서를 입력받습니다.
//         for(int i = 1; i <= n; i++)
//             a[i] = sc.nextInt();

//         // 중위 순회를 진행하여 각 트리의 위치에 맞는 번호를 채워 넣습니다.
//         inOrder(1);

//         // 트리의 구조에 맞게 출력을 진행합니다.
//         for(int i = 1; i <= k; i++) {
//             for(int j = (1 << (i - 1)); j <= (1 << i) - 1; j++) {
//                 System.out.print(treeNum[j] + " ");
//             }
//             System.out.println();
//         }
//     }
// }