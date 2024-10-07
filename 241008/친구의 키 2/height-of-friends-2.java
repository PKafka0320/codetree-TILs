import java.io.*;
import java.util.*;

public class Main {
	static int N, M, indegree[];
	static List<Integer> edges[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1];
		edges = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) queue.add(i);
		}

		int count = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			count++;
			
			for (int next : edges[cur]) {
				if (--indegree[next] <= 0) queue.add(next);
			}
		}

		System.out.println(count == N ? "Consistent" : "Inconsistent");
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
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
            if(indegree[i] == 0)
                q.add(i);
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        int cnt = 0;
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();
            
            // 큐에서 뽑힌 원소의 수를 세줍니다.
            cnt++;

            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // 비로소 indegree가 0이 되었다면
                // queue에 새로 넣어줍니다.
                if(indegree[y] == 0)
                    q.add(y);
            }
        }

        // 큐에 총 n개의 수가 들어갔다면 사이클 없이 정상적으로 topological sort가 구해졌다는 뜻이므로
        // 모순이 없게 됩니다.
        if(cnt == n)
            System.out.print("Consistent");
        else
            System.out.print("Inconsistent");
    }
}
*/