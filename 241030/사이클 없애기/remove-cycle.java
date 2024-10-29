import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M1 = Integer.parseInt(st.nextToken());
		int M2 = Integer.parseInt(st.nextToken());
		
		int[] indegree = new int[N+1];
		List<Integer>[] edges = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges[from].add(to);
			indegree[to]++;
		}
		
		for (int i = 0; i < M2; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		int count = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			count++;
			
			for (int ad : edges[cur]) {
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}
		
		System.out.println(count == N ? "Yes" : "No");
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
    public static int n, m1, m2;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // 각 정점을 방문했는지 판단합니다.
    public static boolean[] visited = new boolean[MAX_N + 1];
    
    // 위상정렬을 위한 큐를 관리합니다.
    public static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m1 = sc.nextInt();
        m2 = sc.nextInt();

        // 인접리스트로 관리합니다.
        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        for(int i = 1; i <= m1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            edges[x].add(y); 
            indegree[y]++; // 진입차수를 갱신합니다.  
        }

        // 양방향 간선은 생각하지 않아도 괜찮습니다.
        // 단방향 간선에 사이클이 없을 경우
        // 양방향 간선은 위상정렬된 순서에 맞게 방향을 맞춰주면
        // 항상 사이클이 없는 그래프가 됩니다.
        // 반면 단방향 간선에 사이클이 있을 경우
        // 양방향 간선을 어떻게 방향을 설정하더라도
        // 해당 그래프에는 사이클이 존재합니다.
        for(int i = 1; i <= m2; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
        }

        // 처음 indegree 값이 0인 곳이 시작점이 됩니다.
        // 이 노드들을 queue에 넣어줍니다.
        for(int i = 1; i <= n; i++)
            if(indegree[i] == 0)
                q.add(i);
        
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

                // 비로소 indegree가 0이 되었다면
                // queue에 새로 넣어줍니다.
                if(indegree[y] == 0)
                    q.add(y);
            }
        }

        // 모든 노드를 방문했다면 그래프 내에 사이클이 존재하지 않는다는 뜻입니다.
        boolean isCycle = false;
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) 
                isCycle = true;
        }

        // 사이클이 존재하는지 여부를 출력합니다.
        if(isCycle)
            System.out.print("No");
        else
            System.out.print("Yes");
    }
}
*/