import java.io.*;
import java.util.*;

class Path {
    int node, distance;

    public Path(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class Main {
    static boolean[] visited;
    static int lastNode, maxDist;
    static List<Path>[] graph;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 정점의 개수
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());

            graph[node1].add(new Path(node2, distance));
            graph[node2].add(new Path(node1, distance));
        }
        
        int a; // 가장 먼 길이의 노드 1
        int b; // 가장 먼 길이의 노드 2
        int ans = 0;

        // 임의 위치에서 가장 먼 노드 탐색
        visited = new boolean[n];
        dist = new int[n];
        visited[0] = true;
        maxDist = 0;
        dfs(0, -1);
        a = lastNode;

        // 첫 번째로 가장 먼 길이의 상대 노드 탐색
        visited = new boolean[n];
        dist = new int[n];
        visited[a] = true;
        maxDist = 0;
        dfs(a, -1);
        b = lastNode;
        
        // a에서 두번째로 가장 먼 노드 탐색 및 정답 갱신
        visited = new boolean[n];
        dist = new int[n];
        visited[a] = true;
        maxDist = 0;
        dfs(a, b);
        ans = Math.max(ans, maxDist);
        
        // b에서 두번째로 가장 먼 노드 탐색 및 정답 갱신
        visited = new boolean[n];
        dist = new int[n];
        visited[b] = true;
        maxDist = 0;
        dfs(b, a);
        ans = Math.max(ans, maxDist);

        System.out.println(ans);
    }

    public static void dfs(int node, int ignoreNode) {
        for (Path nextPath : graph[node]) {
            int nextNode = nextPath.node;
            int nextDistance = nextPath.distance;

            if (visited[nextNode])
                continue;

            visited[nextNode] = true;
            dist[nextNode] = dist[node] + nextDistance;

            if (maxDist < dist[nextNode] && nextNode != ignoreNode) {
                maxDist = dist[nextNode];
                lastNode = nextNode;
            }

            dfs(nextNode, ignoreNode);
        }
    }
}

// import java.util.Scanner;
// import java.util.ArrayList;

// class Pair {
//     int y, d;

//     public Pair(int y, int d) {
//         this.y = y;
//         this.d = d;
//     }
// }

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n;
//     public static ArrayList<Pair>[] edge = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] dist = new int[MAX_N + 1];
//     public static int maxDist;
//     public static int lastNode;
//     public static int a, b;
//     public static int ans;
    
//     // 모든 노드의 정점을 탐색하는 DFS를 진행합니다.
//     public static void dfs(int x, int ignoreNum) {
//         for(int i = 0; i < edge[x].size(); i++) {
//             int y = edge[x].get(i).y;
//             int d = edge[x].get(i).d;
    
//             // 이미 방문한 정점이면 스킵합니다.
//             if(visited[y]) continue;
    
//             visited[y] = true;
//             dist[y] = dist[x] + d;
    
//             // 현재 정점을 기준으로 가장 먼 노드를 찾습니다.
//             // 단, ignoreNum이면 무시합니다.
//             if(dist[y] > maxDist && y != ignoreNum) {
//                 maxDist = dist[y];
//                 lastNode = y;
//             }
    
//             dfs(y, ignoreNum);
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         for(int i = 1; i <= n; i++)
//             edge[i] = new ArrayList<>();
        
//         // n - 1개의 간선 정보를 입력받습니다.
//         for(int i = 1; i <= n - 1; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();
//             int d = sc.nextInt();

//             edge[x].add(new Pair(y, d));
//             edge[y].add(new Pair(x, d));
//         }

//         // DFS를 통해 가장 먼 노드를 찾습니다. 이 노드를 a라 합니다.
//         visited[1] = true;
//         dfs(1, -1);
//         a = lastNode;

//         // 다시 한번 먼 노드를 찾아줍니다. 이 위치를 b라 합니다.
//         for(int i = 1; i <= n; i++) {
//             visited[i] = false;
//             dist[i] = 0;
//         }
//         maxDist = -1;
//         visited[a] = true;
//         dfs(a, -1);
//         b = lastNode;

//         // 이제 a에서 b를 제외한 노드 중 가장 먼 노드까지의 거리를 구합니다.
//         // 이후 답을 갱신합니다.
//         for(int i = 1; i <= n; i++) {
//             visited[i] = false;
//             dist[i] = 0;
//         }
//         maxDist = -1;
//         visited[a] = true;
//         dfs(a, b);
//         ans = Math.max(ans, maxDist);

//         // 이제 b에서 a를 제외한 노드 중 가장 먼 노드까지의 거리를 구합니다.
//         // 이후 답을 갱신합니다.
//         for(int i = 1; i <= n; i++) {
//             visited[i] = false;
//             dist[i] = 0;
//         }
//         maxDist = -1;
//         visited[b] = true;
//         dfs(b, a);
//         ans = Math.max(ans, maxDist);

//         System.out.print(ans);
//     }
// }