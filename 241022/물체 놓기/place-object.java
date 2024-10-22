import java.io.*;
import java.util.*;

public class Main {
	static int N, cost[][], dist[];
	static boolean visited[];
	static int INF = (int) 1e9;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		cost = new int [N+1][N+1];
		dist = new int[N+1];
		visited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			cost[0][i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 1; i <= N; i++) {
			dist[i] = INF;
		}
		
		for (int j = 1; j <= N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				cost[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		dist[0] = 0;
		
		int answer = 0;
		for (int i = 0; i <= N; i++) {
			int minIndx = -1;
			
			for (int j = 0; j <= N; j++) {
				if (visited[j]) continue;
				
				if (minIndx == -1 || dist[minIndx] > dist[j]) {
					minIndx = j;
				}
			}
			
			visited[minIndx] = true;
			
			answer += dist[minIndx];
			
			for (int j = 1; j <= N; j++) {
				dist[j] = Math.min(dist[j], cost[minIndx][j]);
			}
		}
		
		System.out.println(answer);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 300;
    
    // 변수 선언
    public static int n;
    public static int[][] graph = new int[MAX_N + 1][MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];
    
    public static int[] dist = new int[MAX_N + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        n = sc.nextInt();

        // 그래프를 인접행렬로 표현
        // 더미 노드(0번 노드)를 만들어서 1번부터 n번까지 노드를 (직접 물체를 놓는 비용) 으로 연결합니다.
        for(int i = 1; i <= n; i++)
            graph[0][i] = sc.nextInt();

        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                graph[i][j] = sc.nextInt();
        
        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= n; i++)
            dist[i] = (int)1e9;

        // 시작위치(더미노드)에는 dist값을 0으로 설정
        dist[0] = 0;

        // O(|V|^2) 프림 코드
        int ans = 0;
        for(int i = 0; i <= n; i++) {
            // V개의 정점 중 
            // 아직 방문하지 않은 정점 중
            // dist값이 가장 작은 정점을 찾아줍니다.
            int minIndex = -1;
            for(int j = 0; j <= n; j++) {
                if(visited[j])
                    continue;
                
                if(minIndex == -1 || dist[minIndex] > dist[j])
                    minIndex = j;
            }

            // 최솟값에 해당하는 정점에 방문 표시를 진행합니다.
            visited[minIndex] = true;

            // mst 값을 갱신해줍니다.
            ans += dist[minIndex];

            // 최솟값에 해당하는 정점에 연결된 간선들을 보며
            // 시작점으로부터의 최솟값을 갱신해줍니다.
            for(int j = 1; j <= n; j++) {
                dist[j] = Math.min(dist[j], graph[minIndex][j]);
            }
        }

        System.out.print(ans);
    }
}
*/