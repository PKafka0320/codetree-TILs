// import java.io.*;
// import java.util.*;

// public class Main {
//     public static void main(String[] args) throws Exception {
        
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;
        
//         st = new StringTokenizer(br.readLine());
//         int n = Integer.parseInt(st.nextToken());
        
//         int[] parents = new int[n];
        
//         for (int i = 0; i < n - 1; i++) {
//             st = new StringTokenizer(br.readLine());
//             int parent = Integer.parseInt(st.nextToken()) - 1;
//             int child = Integer.parseInt(st.nextToken()) - 1;
            
//             parents[child] = parent;
//         }
        
//         StringBuilder sb = new StringBuilder();
//         for (int i = 1; i < n; i++) {
//             sb.append(parents[i] + 1).append("\n");
//         }
        
//         System.out.println(sb);
//     }

// }

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언:
    public static int n;
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];
    public static int[] parent = new int[MAX_N + 1];
    
    // 트리 순회를 진행합니다.
    // DFS 방식으로 진행하며,
    // 진행되는 간선에 대해 (부모, 자식) 관계를 정의해줍니다.
    public static void traversal(int x) {
        // 노드 x에 연결된 간선을 살펴봅니다.
        for(int i = 0; i < edges[x].size(); i++) {
            int y = edges[x].get(i);
            // 아직 방문해본적이 없는 노드라면
            // 트리의 부모-자식 관계가 결정됩니다.
            // 부모는 x, 자식이 y가 됩니다.
            if(!visited[y]) {
                visited[y] = true;
                parent[y] = x;
    
                // 추가적으로 탐색을 더 진행합니다.
                traversal(y);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        
        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        // n - 1개의 간선 정보를 입력받습니다.
        for(int i = 1; i<= n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            
            // 간선 정보를 인접리스트에 넣어줍니다.
            edges[x].add(y);
            edges[y].add(x);
        }

        // 1번부터 트리 순회를 진행합니다.
        visited[1] = true;
        traversal(1);

        // 부모 노드를 출력합니다.
        for(int i = 2; i <= n; i++)
            System.out.println(parent[i]);
    }
}