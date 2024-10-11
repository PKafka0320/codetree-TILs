import java.io.*;
import java.util.*;

public class Main {
	static class Edge {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][N+1];
		int[] indegree = new int[N+1];
		List<Edge>[] edges = new ArrayList[N+1];
		
		for (int node = 1; node <= N; node++) {
			edges[node] = new ArrayList<>();
		}
		
		for (int edge = 0; edge < M; edge++) {
			st = new StringTokenizer(br.readLine());
			
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[from].add(new Edge(to, weight));
			indegree[to]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int node = 1; node <= N; node++) {
			if (indegree[node] == 0) {
				dp[node][node] = 1;
				queue.add(node);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (Edge edge : edges[cur]) {
				int ad = edge.node;
				int weight = edge.weight;
				
				for (int piece = 1; piece <= N; piece++) {
					dp[ad][piece] += dp[cur][piece] * weight;
				}
				
				if (--indegree[ad] == 0) queue.add(ad);
			}
		}

		StringBuilder answer = new StringBuilder();
		for (int piece = 1; piece <= N; piece++) {
			if (dp[N][piece] > 0) {
				answer.append(piece).append(" ").append(dp[N][piece]).append("\n");
			}
		}
		System.out.println(answer.toString());
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int y, num;

    public Pair(int y, int num) {
        this.y = y;
        this.num = num;
    }
}

public class Main {
    public static final int MAX_N = 100;

    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Pair>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // needs[i][j] : i번 조각을 만들기 위해 필요한 j번 조각의 수 (가장 작은 조각만)
    public static int[][] needs = new int[MAX_N + 1][MAX_N + 1];
    
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
            int z = sc.nextInt();

            edges[y].add(new Pair(x, z));
            indegree[x]++; // 진입차수를 갱신합니다.  
        }

        // 처음 indegree 값이 0인 곳이 시작점이 됩니다.
        // 이 노드들을 queue에 넣어줍니다.
        for(int i = 1; i <= n; i++)
            if(indegree[i] == 0) {
                q.add(i);

                // 해당 조각은 작은 조각이므로 dp를 갱신해줍니다.
                needs[i][i] = 1;
            }
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();
            
            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i).y;
                int num = edges[x].get(i).num;

                // y번 조각을 만들기 위해 필요한
                // 작은 조각의 수 정보를 갱신합니다.
                for(int j = 1; j <= n; j++) {
                    needs[y][j] += num * needs[x][j];
                }
                
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
        
        // n번 조각을 완성하는데 필요한 작은 조각들의 정보를 출력합니다.
        for(int i = 1; i <= n; i++) {
            if(needs[n][i] > 0) {
                System.out.println(i + " " + needs[n][i]);
            }
        }
    }
}
*/