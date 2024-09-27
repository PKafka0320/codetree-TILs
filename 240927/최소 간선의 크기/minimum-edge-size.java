import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node1, node2, distance;

		public Edge(int node1, int node2, int distance) {
			this.node1 = node1;
			this.node2 = node2;
			this.distance = distance;
		}
		
		public int compareTo(Edge e) {
			return e.distance - this.distance;
		}
	}
	static int N, M, A, B, roots[];
	static List<Edge> edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		roots = new int[N+1];
		edges = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(node1, node2, distance));
		}
		
		Collections.sort(edges);
		
		for (Edge edge : edges) {
			int node1 = edge.node1;
			int node2 = edge.node2;
			int distance = edge.distance;
			
			union(node1, node2);
			
			int roota = find(A);
			int rootb = find(B);
			
			if (roota == rootb) {
				System.out.println(distance);
				break;
			}
		}
	}
	
	public static boolean union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return false;
		
		roots[root1] = root2;
		return true;
	}
	
	public static int find(int node) {
		if (roots[node] == node) return node;
		return roots[node] = find(roots[node]);
	}

}

// import java.util.Scanner;
// import java.util.ArrayList;
// import java.util.Collections;

// class Tuple implements Comparable<Tuple> {
//     int x, y, d;

//     public Tuple(int x, int y, int d) {
//         this.x = x;
//         this.y = y;
//         this.d = d;
//     }

//     public int compareTo(Tuple t) {
//         // 만족도가 내림차순이 되도록 정렬합니다.
//         return t.d - this.d; 
//     }
// }

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언
//     public static int n, m;
//     public static int a, b;
    
//     // 번호별 그룹을 관리합니다.
//     public static int[] uf = new int[MAX_N + 1];
    
//     public static ArrayList<Tuple> edges = new ArrayList<>();
    
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
//         a = sc.nextInt();
//         b = sc.nextInt();

//         // 초기 uf 값을 설정합니다.
//         for(int i = 1; i <= n; i++)
//             uf[i] = i;

//         // m개의 간선 정보를 입력받습니다.
//         for(int i = 1; i <= m; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();
//             int d = sc.nextInt();

//             edges.add(new Tuple(x, y, d));
//         }

//         // 간선을 만족도가 큰 순으로 정렬한뒤, union find를 진행합니다.
//         Collections.sort(edges);

//         // 도중 a와 b가 연결되면 그 순간의 간선의 만족도를 출력합니다.
//         for(int i = 0; i < m; i++) {
//             int x = edges.get(i).x;
//             int y = edges.get(i).y;
//             int d = edges.get(i).d;

//             union(x, y);
//             int A = find(a);
//             int B = find(b);

//             // a와 b가 연결되었다는 것은 
//             // 현재 만족도 이상의 간선만으로 연결관계를 만들 수 있었다는 의미이므로
//             // 현재 값이 답이 됩니다.
//             if(A == B) {
//                 System.out.print(d);
//                 break;
//             }
//         }
//     }
// }