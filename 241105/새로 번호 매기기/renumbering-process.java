import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] indegree = new int[N+1];
		List<Integer>[] edge = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edge[i] = new ArrayList<>();
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			
			edge[from].add(to);
			indegree[to]++;
		}
		
		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) pq.add(i);
		}
		
		int[] num = new int[N+1];
		int currentNum = N;
		int count = 0;
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			num[cur] = currentNum--;
			count++;
			
			for (int next : edge[cur]) {
				if (--indegree[next] == 0) {
					pq.add(next);
				}
			}
		}
		
		if (count != N) {
			System.out.println(-1);
		} else {
			StringBuilder answer = new StringBuilder();
			for (int i = 1; i <= N; i++) {
				answer.append(num[i]).append(" ");
			}
			System.out.println(answer.toString());
		}
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // 모든 정점의 방문 여부를 관리합니다.
    public static boolean[] visited = new boolean[MAX_N + 1];
    
    // 위상정렬을 위한 우선순위 큐를 관리합니다.
    public static PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public static int[] ans = new int[MAX_N + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        // 인접리스트로 관리합니다.
        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();
        
        // 단, 최솟값을 뽑는 방식으로 풀면 반례가 있기에
        // 간선을 거꾸로 뒤집고
        // 최댓값을 먼저 뽑게끔 하며
        // 뽑히는 순서대로 n부터 1까지 감소하게 번호를 매겨주는 것이
        // 최선임을 이용합니다.
        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            // 간선을 y -> x로 뒤집어서 생각합니다. 
            edges[y].add(x);
            indegree[x]++;
        }

        // 처음 indegree 값이 0인 곳이 시작점이 됩니다.
        // 이 노드들을 priority queue에 넣어줍니다.
        // 큰 값이 먼저 뽑히도록 -를 붙여 관리합니다.
        for(int i = 1; i <= n; i++)
            if(indegree[i] == 0)
                pq.add(-i);
        
        // 위상정렬을 진행합니다.
        // 큰 값이 먼저 뽑히도록 우선순위큐를 관리합니다. 
        // priority queue에 원소가 남아있다면 계속 진행합니다.
        int cnt = n;
        while(!pq.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = -pq.poll();
            
            ans[x] = cnt--;
            visited[x] = true;

            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // 비로소 indegree가 0이 되었다면
                // priority queue에 새로 넣어줍니다.
                if(indegree[y] == 0)
                    pq.add(-y);
            }
        }

        // 모든 노드를 방문했다면 그래프 내에 사이클이 존재하지 않는다는 뜻입니다.
        boolean isCycle = false;
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) 
                isCycle = true;
        }

        // 사이클이 존재한다면 가능한 답이 없다는 뜻이므로 -1을 출력합니다.
        if(isCycle)
            System.out.print("-1");
        else {
            for(int i = 1; i <= n; i++)
                System.out.print(ans[i] + " ");
        }
    }
}
*/