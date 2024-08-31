import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] edges;
    static boolean[] visited;
    static int[] parents, maxLen;
    static int n, s, d, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        edges = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        parents = new int[n + 1];
        maxLen = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            
            edges[node1].add(node2);
            edges[node2].add(node1);
        }
        
        visited[s] = true;
        dfs(s);
        
        System.out.println(result);
    }
    
    public static void dfs(int node) {
        for (int next : edges[node]) {
            if (visited[next]) continue;
            visited[next] = true;
            parents[next] = node;
            dfs(next);
        }
        
        for (int next : edges[node]) {
            if (parents[node] == next) continue;
            maxLen[node] = Math.max(maxLen[node], maxLen[next] + 1);
            
            if (parents[node] == next || maxLen[next] < d) continue;
            result += 2;
        }
    }
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n, s, d;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] dist = new int[MAX_N + 1];
    
//     public static int ans;
    
//     // DFS를 통해 연결된 모든 정점을 순회합니다.
//     // 동시에 dp값을 계산해줍니다.
//     public static void dfs(int x) {
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 만약 y번 정점을 이미 방문했다면 스킵합니다.
//             if(visited[y]) 
//                 continue;
    
//             visited[y] = true;
//             dfs(y);
    
//             // 가장 멀리 있는 리프노드까지의 거리를 저장합니다.
//             dist[x] = Math.max(dist[x], 1 + dist[y]);
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();
//         s = sc.nextInt();
//         d = sc.nextInt();

//         for(int i = 1; i <= n; i++)
//             edges[i] = new ArrayList<>();

//         // n - 1개의 간선 정보를 입력받습니다.
//         for(int i = 1; i <= n - 1; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();
            
//             // 간선 정보를 인접리스트에 넣어줍니다.
//             edges[x].add(y);
//             edges[y].add(x);
//         }

//         // 루트를 시작으로 DFS를 진행하며 값을 갱신합니다.
//         visited[s] = true;
//         dfs(s);

//         // s번 정점을 제외한 리프까지의 거리가 d 이상인 정점의 개수를 구합니다.
//         for(int i = 1; i <= n; i++) {
//             if(i == s) continue;
//             if(dist[i] >= d) 
//                 ans++;
//         }

//         // s번 정점을 제외한 정답은 거리가 d이상인 정점의 개수 * 2가 됩니다.
//         System.out.print(ans * 2);
//     }
// }