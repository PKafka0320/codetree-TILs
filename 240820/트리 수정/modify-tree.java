import java.util.Scanner;
import java.util.ArrayList;

class Pair {
    int y, d;

    public Pair(int y, int d) {
        this.y = y;
        this.d = d;
    }
}

public class Main {
    public static final int MAX_N = 2000;
    
    // 변수 선언:
    public static int n;
    public static ArrayList<Pair>[] edge = new ArrayList[MAX_N];
    public static boolean[][] removed = new boolean[MAX_N][MAX_N];
    public static int[] edgeX = new int[MAX_N];
    public static int[] edgeY = new int[MAX_N];
    public static int[] edgeDist = new int[MAX_N];
    
    public static boolean[] visited = new boolean[MAX_N];
    public static int[] dist = new int[MAX_N];
    
    public static int maxDist, lastNode;
    public static int ans;
    
    // 모든 노드의 정점을 탐색하는 DFS를 진행합니다.
    public static void dfs(int x) {
        for(int i = 0; i < edge[x].size(); i++) {
            int y = edge[x].get(i).y;
            int d = edge[x].get(i).d;
    
            // 지워진 간선이면 스킵합니다.
            if(removed[x][y]) continue;
    
            // 이미 방문한 정점이면 스킵합니다.
            if(visited[y]) continue;
    
            visited[y] = true;
            dist[y] = dist[x] + d;
    
            // 현재 정점을 기준으로 가장 먼 노드를 찾습니다.
            if(dist[y] > maxDist) {
                maxDist = dist[y];
                lastNode = y;
            }
    
            dfs(y);
        }
    }
    
    public static int getTreeDiameter(int x) {
        // 전역변수들을 초기화합니다.
        for(int i = 0; i < n; i++) visited[i] = false;
        for(int i = 0; i < n; i++) dist[i] = 0;
        maxDist = 0;
        lastNode = x;
    
        // dfs를 통해 가장 멀리 있는 정점을 찾습니다.
        visited[x] = true;
        dfs(x);
    
        // 전역변수들을 초기화합니다.
        for(int i = 0; i < n; i++) visited[i] = false;
        for(int i = 0; i < n; i++) dist[i] = 0;
        maxDist = 0;
    
        // dfs를 통해 가장 멀리 있는 정점에서 트리의 지름을 찾습니다. 
        visited[lastNode] = true;   
        dfs(lastNode);
    
        return maxDist;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        for(int i = 0; i < n; i++)
            edge[i] = new ArrayList<>();

        // n - 1개의 간선 정보를 입력받습니다.
        for(int i = 0; i < n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();

            edgeX[i] = x;
            edgeY[i] = y;
            edgeDist[i] = d;
            edge[x].add(new Pair(y, d));
            edge[y].add(new Pair(x, d));
        }

        // n - 1개의 간선을 하나씩 지우고 생긴 두 트리 각각의 지름을 찾습니다.
        for(int i = 0; i < n - 1; i++) {
            removed[edgeX[i]][edgeY[i]] = true;
            removed[edgeY[i]][edgeX[i]] = true;

            // i번 간선을 지우고 새로 그었을 때, 만들어질 수 있는 최대 지름을 찾습니다.
            // i번 간선을 지운 뒤 생긴 두 트리에서의 지름을 새로 연결할 때 최대 지름이 됩니다.
            int maxDiameter = edgeDist[i] + getTreeDiameter(edgeX[i]) + getTreeDiameter(edgeY[i]);
            ans = Math.max(ans, maxDiameter);

            removed[edgeX[i]][edgeY[i]] = false;
            removed[edgeY[i]][edgeX[i]] = false;
        }

        // 만들 수 있는 트리의 가장 큰 지름을 출력합니다.
        System.out.print(ans);
    }
}