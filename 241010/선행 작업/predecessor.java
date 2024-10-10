import java.io.*;
import java.util.*;

public class Main {
	static class Work {
		int node, time;

		public Work(int node, int time) {
			this.node = node;
			this.time = time;
		}
	}
	static int N, times[], indegree[], maxPreTimes[];
	static List<Integer> edges[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		times = new int[N+1];
		indegree = new int[N+1];
		maxPreTimes = new int[N+1];
		edges = new ArrayList[N+1];

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			int preceedCount = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < preceedCount; j++) {
				int node = Integer.parseInt(st.nextToken());
				edges[node].add(i);
				indegree[i]++;
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) queue.add(i);
		}
		
		int answer = 0;
		while (!queue.isEmpty()) {
			int curNode = queue.poll();
			times[curNode] += maxPreTimes[curNode];
			answer = Math.max(answer, times[curNode]);
			
			for (int adNode : edges[curNode]) {
				maxPreTimes[adNode] = Math.max(maxPreTimes[adNode], times[curNode]);
				if (--indegree[adNode] == 0) {
					queue.add(adNode);
				}
			}
		}
		System.out.println(answer);
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int MAX_N = 10000;
    
    // 변수 선언
    public static int n;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // 각 일을 끝내는데 걸리는 시간을 관리합니다.
    public static int[] workingTime = new int[MAX_N + 1];
    
    // dp[i] : i번 일을 완료하기 위해 필요한 최소 시간을 관리합니다.
    public static int[] dp = new int[MAX_N + 1];
    
    // 위상정렬을 위한 큐를 관리합니다.
    public static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            workingTime[i] = sc.nextInt();
            int num = sc.nextInt();
            while(num-- > 0) {
                // 인접리스트로 관리합니다.
                int x = sc.nextInt();
                edges[x].add(i);
                indegree[i]++; // 진입차수를 갱신합니다.  
            }
        }

        // 처음 indegree 값이 0인 곳이 시작점이 됩니다.
        // 이 노드들을 queue에 넣어줍니다.
        for(int i = 1; i <= n; i++)
            if(indegree[i] == 0) {
                q.add(i);
                
                // 이때의 i 값들이 dp의 초기조건이 되며,
                // i번째 작업을 끝내기 위해서는 workingTime[i] 만큼의 시간이 필요합니다.
                dp[i] = workingTime[i];
            }
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();
            
            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);

                // y번째 작업이 완료되기 위해서는
                // 작업 중 가장 오래 걸리는 시간을 계속 기록해줘야하므로
                // 최댓값을 갱신해줍니다.
                // x일이 끝나기 위한 최소 시간이 dp[x]이므로
                // 이 일 직후 y일을 진행했을 때 걸리는 시간은
                // dp[x] + workingTime[y]가 됩니다.
                dp[y] = Math.max(dp[y], dp[x] + workingTime[y]);

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // 비로소 indegree가 0이 되었다면
                // queue에 새로 넣어줍니다.
                if(indegree[y] == 0)
                    q.add(y);
            }
        }

        // 각 일을 끝내는 데 걸리는 시간 중
        // 최댓값이
        // 모든 작업을 완료하기 위한 시간이 됩니다.
        int ans = 0;
        for(int i = 1; i <= n; i++)
            ans = Math.max(ans, dp[i]);
        
        System.out.print(ans);
    }
}
*/