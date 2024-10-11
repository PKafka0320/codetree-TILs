import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] dp = new int[N+1];
		int[] indegree = new int[N+1];
		List<Integer>[] edges = new ArrayList[N+1];
		int MOD = (int) 1e9 + 7;
		
		for (int node = 1; node <= N; node++) {
			edges[node] = new ArrayList<>();
		}
		
		for (int edge = 0; edge < M; edge++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(1);
		dp[1] = 1;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int ad : edges[cur]) {
				dp[ad] = (dp[ad] + dp[cur]) % MOD;
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}
		
		System.out.println(dp[N]);
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int MAX_N = 100000;
    public static final int MOD = 1000000007;

    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // way[i] : 1번 노드에서 i번 노드로 이동 가능한 경로의 수
    public static int[] way = new int[MAX_N + 1];
    
    // 위상정렬을 위한 큐를 관리합니다.
    public static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        // 인접리스트로 관리합니다.
        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            edges[x].add(y); 
            indegree[y]++; // 진입차수를 갱신합니다.  
        }

        way[1] = 1;

        // 처음 indegree 값이 0인 곳이 시작점이 됩니다.
        // 이 노드들을 queue에 넣어줍니다.
        for(int i = 1; i <= n; i++)
            if(indegree[i] == 0) {
                q.add(i);
            }
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();
            
            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);

                // y번 정점으로 갈 수 있는 경우의 수 정보를 갱신합니다.
                way[y] += way[x];
                way[y] %= MOD;

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // 비로소 indegree가 0이 되었다면
                // pressure 정보를 갱신하고
                // queue에 새로 넣어줍니다.
                if(indegree[y] == 0) {
                    q.add(y);
                }
            }
        }
        
        // n번 노드로 가는 경우의 수를 출력합니다.
        System.out.print(way[n]);
    }
}
*/