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
    public static final int MAX_N = 10000;
    
    // 변수 선언:
    public static int n;
    public static ArrayList<Pair>[] edge = new ArrayList[MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];
    public static int[] dist = new int[MAX_N + 1];
    public static int maxDist;
    public static int lastNode;
    
    // 모든 노드의 정점을 탐색하는 DFS를 진행합니다.
    public static void dfs(int x) {
        for(int i = 0; i < edge[x].size(); i++) {
            int y = edge[x].get(i).y;
            int d = edge[x].get(i).d;
    
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        for(int i = 1; i <= n; i++)
            edge[i] = new ArrayList<>();
        
        // n - 1개의 간선 정보를 입력받습니다.
        for(int i = 1; i <= n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();

            edge[x].add(new Pair(y, d));
            edge[y].add(new Pair(x, d));
        }

        // DFS를 통해 가장 먼 노드를 찾습니다.
        visited[1] = true;
        dfs(1);

        // 가장 먼 노드에서 시작해 다시 한번 DFS를 돌려 트리의 가장 긴 거리를 찾습니다.
        for(int i = 1; i <= n; i++) {
            visited[i] = false;
            dist[i] = 0;
        }

        visited[lastNode] = true;
        dfs(lastNode);

        // 트리의 가장 긴 거리를 출력합니다.
        System.out.print(maxDist);
    }
}