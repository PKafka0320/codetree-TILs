import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int DIR_NUM = 4;
    public static final int MAX_G = 2500;
    public static final int MAX_N = 50;
    
    // 변수 선언
    public static int n, m;
    public static int[][] grid = new int[MAX_N + 1][MAX_N + 1];
    public static int[][] renum = new int[MAX_N + 1][MAX_N + 1];
    
    // MST를 위해 필요한 변수입니다.
    public static int N; // 새로운 그래프에서 정점의 개수를 의미합니다.
    public static int[][] graph = new int[MAX_G + 1][MAX_G + 1];
    public static boolean[] visited = new boolean[MAX_G + 1];
    
    public static int[] dist = new int[MAX_G + 1];
    
    public static boolean inRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= m;
    }
    
    public static void makeGraph() {
        // 새로운 그래프에서는 1의 개수 만큼 정점이 새로 생기게 됩니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= m; j++)
                if(grid[i][j] == 1)
                    renum[i][j] = ++N;
    
        // graph 초기값을 불가능을 뜻하도록 큰 값을 넣어줍니다.
        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++)
                graph[i][j] = (int)1e9;
    
        int[] dx = new int[]{-1, 1,  0, 0};
        int[] dy = new int[]{ 0, 0, -1, 1};
    
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                // 1이라면 4방향으로 쭉 움직이며 (최초로 1이 되는 위치까지의 거리 - 1)이 가중치인 간선을 만들어줍니다.
                // 즉, 인접한 1끼리는 가중치가 0이 됩니다.
                if(grid[i][j] == 1) {
                    for(int k = 0; k < DIR_NUM; k++) {
                        int dist = 1;
                        while(true) {
                            int nx = i + dx[k] * dist, ny = j + dy[k] * dist;
                            if(!inRange(nx, ny))
                                break;
                            
                            if(grid[nx][ny] == 1) {
                                graph[renum[i][j]][renum[nx][ny]] = dist - 1; 
                                break;
                            }
    
                            dist++;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= m; j++)
                grid[i][j] = sc.nextInt();
        
        makeGraph();

        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= N; i++)
            dist[i] = (int)1e9;

        // 시작위치에는 dist값을 0으로 설정
        dist[1] = 0;

        // O(|V|^2) 프림 코드
        int ans = 0;
        for(int i = 1; i <= N; i++) {
            // V개의 정점 중 
            // 아직 방문하지 않은 정점 중
            // dist값이 가장 작은 정점을 찾아줍니다.
            int minIndex = -1;
            for(int j = 1; j <= N; j++) {
                if(visited[j])
                    continue;
                
                if(minIndex == -1 || dist[minIndex] > dist[j])
                    minIndex = j;
            }
            
            // 다 해결하지 못했는데 그 다음 노드가 없다면
            // 답은 -1이 됩니다.
            if(dist[minIndex] == (int)1e9) {
                ans = -1;
                break;
            }

            // 최솟값에 해당하는 정점에 방문 표시를 진행합니다.
            visited[minIndex] = true;

            // mst 값을 갱신해줍니다.
            ans += dist[minIndex];

            // 최솟값에 해당하는 정점에 연결된 간선들을 보며
            // 시작점으로부터의 최솟값을 갱신해줍니다.
            for(int j = 1; j <= N; j++) {
                dist[j] = Math.min(dist[j], graph[minIndex][j]);
            }
        }

        System.out.print(ans);
    }
}