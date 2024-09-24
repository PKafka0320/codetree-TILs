import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, roots[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		roots = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			union(node1, node2);
		}
		
		st = new StringTokenizer(br.readLine());
		int defaultNode = Integer.parseInt(st.nextToken());
		for (int k = 1; k < K; k++) {
			int tempNode = Integer.parseInt(st.nextToken());
			if (find(defaultNode) != find(tempNode)) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(1);
	}
	
	public static int find(int node1) {
		if (roots[node1] == node1) return node1;
		return roots[node1] = find(roots[node1]);
	}
	
	public static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return;
		
		roots[root1] = root2;
	}

}

// import java.util.Scanner;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언
//     public static int n, m, k;
    
//     // 번호별 그룹을 관리합니다.
//     public static int[] uf = new int[MAX_N + 1];
//     public static int[] path = new int[MAX_N + 1];
    
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
//         m = sc.nextInt();
//         k = sc.nextInt();

//         // 초기 uf 값을 설정합니다.
//         for(int i = 1; i <= n; i++)
//             uf[i] = i;

//         // 주어진 간선으로
//         // 연결관계를 만들어줍니다.
//         while(m-- > 0) {
//             int a = sc.nextInt();
//             int b = sc.nextInt();

//             // 합치는 명령입니다.
//             union(a, b);
//         }

//         // 경로를 이동하는 것이 가능하면 true, 아니라면 false를 기록합니다.
//         boolean isPos = true;
//         for(int i = 1; i <= k; i++)
//             path[i] = sc.nextInt();

//         // 만약 경로의 i번째 노드에서 i + 1번째 노드가 연결되어 있지 않으면 이동하는 것이 불가능합니다.
//         // 이는 대표 번호가 동일한지로 판단 가능합니다.
//         for(int i = 1; i <= k - 1; i++) {
//             if(find(path[i]) != find(path[i + 1]))
//                 isPos = false;
//         }
        
//         if(isPos) System.out.print(1);
//         else System.out.print(0);
//     }
// }