import java.util.Scanner;
import java.util.ArrayList;

class Pair {
    long cost, time;

    public Pair(long cost, long time) {
        this.cost = cost;
        this.time = time;
    }

    public boolean isGreaterThan(Pair p) {
        return this.cost > p.cost || (this.cost == p.cost && this.time > p.time);
    }
}

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final long INF = (long)1e17 + 1;
    public static final int MAX_M = 1000;
    
    // 변수 선언
    public static int a, b, n, m = 1000;
    public static Pair[][] graph = new Pair[MAX_M + 1][MAX_M + 1]; // (비용, 시간)을 기록
    public static boolean[] visited = new boolean[MAX_M + 1];
    
    public static Pair[] dist = new Pair[MAX_M + 1];               // (비용, 시간)을 기록

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        a = sc.nextInt();
        b = sc.nextInt();
        n = sc.nextInt();

        // 초기 graph 값에 전부 큰 값 기록
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= m; j++) {
                graph[i][j] = new Pair(INF, 0);
            }
            // 자기 자신은 비용과 시간이 전혀 소요되지 않음
            graph[i][i] = new Pair(0, 0);
        }

        // 그래프를 인접행렬로 표현
        for(int i = 1; i <= n; i++) {
            int cost = sc.nextInt();
            int stopNum = sc.nextInt();
            
            ArrayList<Integer> stops = new ArrayList<>();
            for(int j = 0; j < stopNum; j++) {
                int x = sc.nextInt();
                stops.add(x);
            }

            for(int j = 0; j < stopNum; j++) {
                for(int k = j + 1; k < stopNum; k++) {
                    Pair newP = new Pair(cost, k - j);
                    if(graph[stops.get(j)][stops.get(k)].isGreaterThan(newP))
                        graph[stops.get(j)][stops.get(k)] = newP;
                }
            }
        }
        
        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= m; i++)
            dist[i] = new Pair(INF, 0);

        // 시작위치에는 dist값을 0으로 설정
        dist[a] = new Pair(0, 0);

        // O(|V|^2) 다익스트라 코드
        for(int i = 1; i <= m; i++) {
            // V개의 정점 중 
            // 아직 방문하지 않은 정점 중
            // dist값이 가장 작은 정점을 찾아줍니다.
            int minIndex = -1;
            for(int j = 1; j <= m; j++) {
                if(visited[j])
                    continue;
                
                if(minIndex == -1 || dist[minIndex].isGreaterThan(dist[j]))
                    minIndex = j;
            }

            // 최솟값에 해당하는 정점에 방문 표시를 진행합니다.
            visited[minIndex] = true;

            long minCost = dist[minIndex].cost;
            long minTime = dist[minIndex].time;

            // 최솟값에 해당하는 정점에 연결된 간선들을 보며
            // 시작점으로부터의 최단거리 값을 갱신해줍니다.
            for(int j = 1; j <= m; j++) {
                long cost = graph[minIndex][j].cost;
                long time = graph[minIndex][j].time;
                Pair newP = new Pair(minCost + cost, minTime + time);
                if(dist[j].isGreaterThan(newP))
                    dist[j] = newP;
            }
        }

        // 만약 도달이 불가능하다면 -1 -1이 답이 됩니다.
        if(dist[b].cost == INF)
            dist[b] = new Pair(-1, -1);
        
        long minCost = dist[b].cost;
        long minTime = dist[b].time;

        System.out.print(minCost + " " + minTime);
    }
}