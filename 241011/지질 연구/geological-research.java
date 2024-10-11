import java.io.*;
import java.util.*;

public class Main {
	static class Pressure {
		int level, count;

		public Pressure(int level, int count) {
			this.level = level;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Pressure[] dp = new Pressure[N+1];
		int[] indegree = new int[N+1];
		List<Integer>[] edges = new ArrayList[N+1];
		
		for (int node = 1; node <= N; node++) {
			edges[node] = new ArrayList<>();
			dp[node] = new Pressure(0, 0);
		}
		
		for (int edge = 0; edge < M; edge++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int node = 1; node <= N; node++) {
			if (indegree[node] == 0) {
				dp[node].level = 1;
				queue.add(node);
			}
		}
		
		int max = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (dp[cur].count >= 2) {
				dp[cur].level++;
			}
			max = Math.max(max, dp[cur].level);
			
			for (int ad : edges[cur]) {
				if (dp[ad].level < dp[cur].level) {
					dp[ad].level = dp[cur].level;
					dp[ad].count = 1;
				} else if (dp[ad].level == dp[cur].level) {
					dp[ad].count++;
				}
				
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}
		
		System.out.println(max);
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int MAX_N = 1000;
    
    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // pressure[i] : i번 지층의 압력도를 관리합니다.
    public static int[] pressure = new int[MAX_N + 1];
    
    // maxPressure[i] : i번 지층이 받는 압력 중 최대 압력입니다.
    // maxPressureCnt[i] : i번 지층이 최대 압력으로 받는 지층 개수입니다.
    public static int[] maxPressure = new int[MAX_N + 1];
    public static int[] maxPressureCnt = new int[MAX_N + 1];
    
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

        // 처음 indegree 값이 0인 곳이 시작점이 됩니다.
        // 이 노드들을 queue에 넣어줍니다.
        for(int i = 1; i <= n; i++)
            if(indegree[i] == 0) {
                q.add(i);
                
                // 아무 압력도 받지 않는 지층입니다.
                // 해당 지층은 압력도가 1입니다.
                pressure[i] = 1;
            }
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();
            
            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);

                // y번 압력의 정보를 갱신합니다.
                if(maxPressure[y] < pressure[x]) {
                    maxPressure[y] = pressure[x];
                    maxPressureCnt[y] = 1;
                }
                else if(maxPressure[y] == pressure[x]) {
                    maxPressureCnt[y]++;
                }

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // 비로소 indegree가 0이 되었다면
                // pressure 정보를 갱신하고
                // queue에 새로 넣어줍니다.
                if(indegree[y] == 0) {
                    if(maxPressureCnt[y] == 1)
                        pressure[y] = maxPressure[y];
                    else
                        pressure[y] = maxPressure[y] + 1;
                    
                    q.add(y);
                }
            }
        }

        // 모든 지층의 압력도 중에서
        // 가장 큰 값을 출력합니다.
        int ans = 0;
        for(int i = 1; i <= n; i++)
            ans = Math.max(ans, pressure[i]);
        
        System.out.print(ans);
    }
}
*/