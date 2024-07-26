import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 1000;
    
    // 변수 선언
    public static int n, m;
    public static int[][] graph = new int[MAX_N + 1][MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];
    
    public static int[] dist = new int[MAX_N + 1];
    public static int[] path = new int[MAX_N + 1];
    
    public static int ans;
    
    public static void dijkstra() {
        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= n; i++)
            dist[i] = (int)1e9;
    
        // 시작위치에는 dist값을 0으로 설정
        dist[1] = 0;
    
        // visited 값을 초기화해줍니다.
        for(int i = 1; i <= n; i++)
            visited[i] = false;
    
        // O(|V|^2) 다익스트라 코드
        for(int i = 1; i <= n; i++) {
            // V개의 정점 중 
            // 아직 방문하지 않은 정점 중
            // dist값이 가장 작은 정점을 찾아줍니다.
            int minIndex = -1;
            for(int j = 1; j <= n; j++) {
                if(visited[j])
                    continue;
                
                if(minIndex == -1 || dist[minIndex] > dist[j])
                    minIndex = j;
            }
    
            // 최솟값에 해당하는 정점에 방문 표시를 진행합니다.
            visited[minIndex] = true;
    
            // 최솟값에 해당하는 정점에 연결된 간선들을 보며
            // 시작점으로부터의 최단거리 값을 갱신해줍니다.
            for(int j = 1; j <= n; j++) {
                // 간선이 존재하지 않는 경우에는 넘어갑니다.
                if(graph[minIndex][j] == 0)
                    continue;
    
                if(dist[j] > dist[minIndex] + graph[minIndex][j]) {
                    dist[j] = dist[minIndex] + graph[minIndex][j];
                    // path값을 갱신해줍니다.
                    path[j] = minIndex;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        n = sc.nextInt();
        m = sc.nextInt();

        // 그래프를 인접행렬로 표현
        // 양방향 그래프이므로 양쪽 다 표시해줍니다.
        while(m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            graph[x][y] = z;
            graph[y][x] = z;
        }
        
        dijkstra();

        // 도착지에서 시작하여
        // 시작점가 나오기 전까지
        // path를 따라 움직여줍니다.
        int idx = n;
        ArrayList<Integer> vertices = new ArrayList<>();
        vertices.add(idx);
        while(idx != 1) {
            idx = path[idx];
            vertices.add(idx);
        }

        // 초기 최단거리를 기록합니다.
        int origDist = dist[n];

        // 최단거리에 해당하는
        // 간선을 하나 잡아 2배로 한 뒤
        // 최단거리를 구해봅니다.
        // 그 중 최대로 변한 정도를 갱신해줍니다.
        for(int i = vertices.size() - 1; i >= 1; i--) {
            // 가중치를 2배로 만들어줍니다.
            int x = vertices.get(i);
            int y = vertices.get(i - 1);
            graph[x][y] *= 2;
            graph[y][x] *= 2;

            // 최단거리를 구해줍니다.
            dijkstra();

            // 기존 최단거리와의 차이 중
            // 최댓값을 갱신해줍니다.
            ans = Math.max(ans, dist[n] - origDist);

            // 간선 가중치를 원상복구 시킵니다.
            graph[x][y] /= 2;
            graph[y][x] /= 2;
        }

        System.out.print(ans);
    }
}