import java.io.*;
import java.util.*;

public class Main {
	static int N, roots[], counts[];
	static int MAX = 100_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		roots = new int[MAX+1];
		counts = new int[MAX+1];
		
		for (int i = 1; i <= MAX; i++) {
			roots[i] = i;
			counts[i] = 1;
		}
		
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
			answer.append(counts[find(a)]).append("\n");
		}
		System.out.println(answer);
	}
	
	public static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if (A == B) return;
		
		roots[B] = A;
		counts[A] += counts[B];
	}
	
	public static int find(int a) {
		if (roots[a] == a) return a;
		return roots[a] = find(roots[a]);
	}

}

// import java.util.Scanner;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언
//     public static int n;
    
//     // 번호별 그룹을 관리합니다.
//     public static int[] uf = new int[MAX_N + 1];
    
//     // 그룹별 노드 개수를 관리합니다.
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
//             sz[Y] += sz[X];
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         // 입력:
//         n = sc.nextInt();

//         // 초기 uf, sz 값을 설정합니다.
//         for(int i = 1; i <= MAX_N; i++) {
//             uf[i] = i;
//             sz[i] = 1;
//         }

//         // n개의 간선 정보를 입력받습니다.
//         for(int i = 1; i <= n; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();

//             union(x, y);
            
//             // 간선을 합친 뒤, 그 간선에 속한 두 정점과 연결된 정점의 총 개수를 출력합니다.
//             int X = find(x);
//             System.out.println(sz[X]);
//         }
//     }
// }