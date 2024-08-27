import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] tree;
	static int[] datas;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		tree = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			tree[i] = new ArrayList<>();
		}
		datas = new int[n];
		dp = new int[n];
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int data = Integer.parseInt(st.nextToken());
			int parentIndex = Integer.parseInt(st.nextToken()) - 1;
			
			tree[parentIndex].add(i);
			
			datas[i] = (type == 1) ? data : -data;
		}
		
		dfs(0);
		System.out.println(dp[0]);
	}
	
	public static void dfs(int index) {
		for (int childIndex : tree[index]) {
			dfs(childIndex);
		}
		
		dp[index] = datas[index];
		for (int childIndex : tree[index]) {
			if (dp[childIndex] > 0) {
				dp[index] += dp[childIndex];
			}
		}
	}
	
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
//     public static int[] arr = new int[MAX_N + 1];
//     public static int[] dp = new int[MAX_N + 1]; // dp[i] : i번째 노드에 최종적으로 적히게 되는 값
    
//     // DFS를 통해 연결된 모든 정점을 순회합니다.
//     // 동시에 dp값을 계산해줍니다.
//     public static void dfs(int x) {
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
//             dfs(y);
//         }
    
//         // 이제 퇴각하기 전에
//         // 각각의 자식들을 다시 순회하며 
//         // dp[x] 값을 갱신해줍니다.
//         dp[x] = arr[x]; // 초기 값을 설정해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
//             // 자식에 적힌 값이 양수인 경우에만 그 값을 더해줍니다.
//             if(dp[y] > 0)
//                 dp[x] += dp[y];
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();
//         // n - 1개의 간선 정보를 입력받습니다.
//         for(int i = 1; i <= n; i++)
//             edges[i] = new ArrayList<>();
        
//         for(int i = 2; i <= n; i++) {
//             int t = sc.nextInt();
//             int a = sc.nextInt();
//             int p = sc.nextInt();
            
//             // 간선 정보를 인접리스트에 넣어줍니다.
//             // 부모가 이미 주어진 상황이므로 
//             // p -> i 에 해당하는 간선만 만들어도 충분합니다.
//             edges[p].add(i);

//             // 각 노드에 해당하는 값을 기록합니다.
//             arr[i] = (t == 1) ? a : -a;
//         }

//         // 1번 정점을 시작으로 DFS를 진행하며 값을 갱신합니다.
//         dfs(1);

//         // 1번 노드에 적혀있는 값을 출력합니다.
//         System.out.print(dp[1]);
//     }
// }