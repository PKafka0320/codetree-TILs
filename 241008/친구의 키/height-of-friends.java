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
		
		StringBuilder answer = new StringBuilder();
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			answer.append(cur).append(" ");
			
			for (int next : edges[cur]) {
				if (--indegree[next] == 0) queue.add(next);
			}
		}
		
		System.out.println(answer);
	}
}

/* DFS
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];
    public static Stack<Integer> reversedOrder = new Stack<>();
    
    // DFS 탐색을 진행합니다.
    public static void dfs(int x) {
        // x에서 갈 수 있는 모든 곳을 탐색합니다.
        // 단, 방문한 적이 없는 경우에만 진행합니다.
        for(int i = 0; i < edges[x].size(); i++) {
            int y = edges[x].get(i);
    
            if(!visited[y]) {
                visited[y] = true;
                dfs(y);
            }
        }
    
        // 퇴각 직전에
        // 현재 노드 번호를 stack에 넣어줍니다.
        reversedOrder.add(x);
    }

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
        }

        // DFS 탐색을 진행합니다.
        // 단, 방문표시가 되지 않은 모든 곳을 시작으로 하여
        // DFS를 진행해야 합니다.
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }

        // 위상정렬 순서대로 출력합니다.
        // stack에 들어있는 순서를 거꾸로 출력해주면 됩니다.
        while(!reversedOrder.isEmpty())
            System.out.print(reversedOrder.pop() + " ");
    }
}
*/

/* indegree
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
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();
            
            // x값을 출력합니다.
            // 뽑히는 순서가 곧 위상정렬 순서가 됩니다.
            System.out.print(x + " ");

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
    }
}
*/