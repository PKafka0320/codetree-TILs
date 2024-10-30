import java.io.*;
import java.util.*;

public class Main {
	static int N, M, indegree[], query[][];
	static List<Integer> edges[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		query = new int[M+1][2];
		indegree = new int[N+1];
		edges = new ArrayList[N+1];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			query[i][0] = from;
			query[i][1] = to;
		}
		
		int lo = 1, hi = M;
		int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if(cycleExist(mid)) {
                ans = mid;
                hi = mid - 1;
            }
            else {
                lo = mid + 1;
            }
        }
		
		System.out.println(ans == 0 ? "Consistent" : ans);
	}
	
	public static boolean cycleExist(int limit) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			indegree[i] = 0;
			edges[i] = new ArrayList<>();
			visited[i] = false;
		}
		
		for (int i = 1; i <= limit; i++) {
			int from = query[i][0];
			int to = query[i][1];
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			visited[cur] = true;
			
			for (int next : edges[cur]) {
				if (--indegree[next] == 0) {
					queue.add(next);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				return true;
			}
		}
		
		return false;
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // 각 정점을 방문했는지 판단합니다.
    public static boolean[] visited = new boolean[MAX_N + 1];
    
    // 위상정렬을 위한 큐를 관리합니다.
    public static Queue<Integer> q = new LinkedList<>();
    
    public static Pair[] query = new Pair[MAX_N + 1];
    
    public static int ans;
    
    public static boolean cycleExist(int limit) {
        // 변수를 초기화합니다.
        for(int i = 1; i <= n; i++) {
            indegree[i] = 0;
            edges[i] = new ArrayList<>();
            visited[i] = false;
        }
    
        // limit번까지의 간선이 존재할 때 모순이 있는지 없는지를 판단합니다.
        // 그래프를 인접 리스트로 관리합니다.
        for(int i = 1; i <= limit; i++) {
            int a = query[i].x;
            int b = query[i].y;
    
            edges[a].add(b); 
            indegree[b]++; // 진입차수를 갱신합니다.  
        }
    
        // 처음 indegree 값이 0인 곳이 루트가 됩니다.
        // 이 노드들을 queue에 넣어주고, 정답으로 미리 저장해 놓습니다.
        for(int i = 1; i <= n; i++)
            if(indegree[i] == 0) {
                q.add(i);
            }
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();
            visited[x] = true;
    
            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);
    
                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;
    
                // indegree가 0이 되었다면
                // queue에 새로 넣어줍니다.
                if(indegree[y] == 0) {
                    q.add(y);
                }
            }
        }
    
        // 모든 노드를 방문했다면 그래프 내에 사이클이 존재하지 않는다는 뜻입니다.
        boolean isCycle = false;
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) 
                isCycle = true;
        }
    
        return isCycle;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        // 각 입력을 순서대로 관리합니다.
        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            query[i] = new Pair(x, y);
        }

        // 답을 결정하고 이분 탐색을 진행합니다.
        int lo = 1, hi = m;

        while(lo <= hi) {
            int mid = (lo + hi) / 2;

            // 1번부터 mid번 정보까지 사용했을 때
            // 사이클이 존재한다면 입력에서 모순이 존재합니다.
            // 정답을 갱신하고 더 작은 답이 있는지 탐색합니다.
            if(cycleExist(mid)) {
                ans = mid;
                hi = mid - 1;
            }
            else {
                lo = mid + 1;
            }
        }

        if(ans == 0)
            System.out.print("Consistent");
        else
            System.out.print(ans);
    }
}
*/