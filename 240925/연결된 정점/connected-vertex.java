import java.io.*;
import java.util.*;

public class Main {
	static int N, M, roots[], counts[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		roots = new int[N+1];
		counts = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
			counts[i] = 1;
		}
		
		StringBuilder answer = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			char op = st.nextToken().charAt(0);
			if (op == 'x') {
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				
				union(node1, node2);
			} else {
				int node = Integer.parseInt(st.nextToken());
				answer.append(counts[find(node)]).append("\n");
			}
		}
		System.out.println(answer);
	}
	
	public static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return;
		
		roots[root2] = root1;
		counts[root1] += counts[root2];
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
//     public static int n, m;
    
//     // 번호별 그룹을 관리합니다.
//     public static int[] uf = new int[MAX_N + 1];
    
//     // 각 유니온 그룹의 사이즈를 관리합니다.
//     public static int[] sz = new int[MAX_N + 1];
    
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
//         if(X != Y) {
//             uf[X] = Y;
    
//             // 사이즈를 누적해줍니다.
//             sz[Y] += sz[X];
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
        
//         // 입력:
//         n = sc.nextInt(); 
//         m = sc.nextInt();

//         // 초기 uf 값을 설정합니다.
//         for(int i = 1; i <= n; i++) {
//             uf[i] = i;
//             sz[i] = 1;
//         }

//         while(m-- > 0) {
//             char query = sc.next().charAt(0);

//             if(query == 'x') {
//                 int a = sc.nextInt();
//                 int b = sc.nextInt();

//                 // 합치는 명령입니다.
//                 union(a, b);
//             }
//             else {
//                 int a = sc.nextInt();

//                 // 사이즈를 반환합니다.
//                 int A = find(a);
//                 System.out.println(sz[A]);
//             }
//         }
//     }
// }