import java.io.*;
import java.util.*;

public class Main {
	static int N, M, roots[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		roots = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
		}
		
		for (int i = 0; i < N-2; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			union(node1, node2);
		}
		
		for (int i = 1; i <= N-1; i++) {
			for (int j = i+1; j <= N; j++) {
				if (find(i) != find(j)) {
					System.out.printf("%d %d", i, j);
					return;
				}
			}
		}
	}
	
	public static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return;
		
		roots[root2] = root1;
	}
	
	public static int find(int node) {
		if (roots[node] == node) return node;
		return roots[node] = find(roots[node]);
	}

}

// import java.util.Scanner;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언
//     public static int n;
    
//     // 번호별 그룹을 관리합니다.
//     public static int[] uf = new int[MAX_N + 1];
    
//     // x의 대표 번호를 찾아줍니다.
//     public static int find(int x) {
//         // x가 루트 노드라면 x값을 반환합니다.
//         if(uf[x] == x)
//             return x;
//         // x가 루트 노드가 아니라면
//         // x의 부모인 uf[x]에서 탐색을 더 진행한 뒤
//         // 찾아낸 루트 노드를 uf[x]에 넣어줌과 동시에
//         // 해당 노드값을 반환합니다.
//         return uf[x] = find(uf[x]);
//     }
    
//     // x, y가 같은 집합이 되도록 합니다.
//     public static void union(int x, int y) {
//         // x, y의 대표 번호를 찾은 뒤
//         // 연결해줍니다.
//         int X = find(x);
//         int Y = find(y);
        
//         uf[X] = Y;
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         // 초기 uf 값을 설정합니다.
//         for(int i = 1; i <= n; i++)
//             uf[i] = i;

//         for(int i = 1; i <= n - 2; i++) {
//             int a = sc.nextInt();
//             int b = sc.nextInt();

//             // 합치는 명령입니다.
//             union(a, b);
//         }

//         // n - 2개의 간선을 이었을 시, 그래프는 총 두 개의 컴퍼넌트로 분리됩니다.
//         // 사전순으로 가장 앞서도록 만들려면, 1번 노드와, 그리고 1번 노드의 컴퍼넌트에 있지 않은
//         // 가장 작은 노드를 찾으면 됩니다.
//         for(int i = 2; i <= n; i++) {
//             int I = find(i);
//             if(I != find(1)) {
//                 System.out.print(1 + " " + i);
//                 break;
//             }
//         }
//     }
// }