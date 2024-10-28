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
		boolean[] visited = new boolean[N+1];
		List<Integer>[] edge = new ArrayList[N+1];
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			edge[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < k; j++) {
				int sub = Integer.parseInt(st.nextToken());
				edge[sub].add(a);
				indegree[a]++;
			}
		}
		
		int L = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < L; i++) {
			int l = Integer.parseInt(st.nextToken());
			queue.add(l);
			visited[l] = true;
		}
		
		int count = 0;
		List<Integer> parts = new ArrayList<>();
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			count++;
			parts.add(cur);
			
			for (int ad : edge[cur]) {
				if (visited[ad]) continue;
				if (--indegree[ad] == 0) {
					queue.add(ad);
					visited[ad] = true;
				}
			}
		}
		Collections.sort(parts);
		
		StringBuilder answer = new StringBuilder();
		answer.append(count).append("\n");
		for (int part : parts) {
			answer.append(part).append(" ");
		}
		System.out.println(answer.toString());
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    public static int parts;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];
    
    // 위상정렬을 위한 큐를 관리합니다.
    public static Queue<Integer> q = new LinkedList<>();
    
    // 만들 수 있는 조각들을 관리합니다.
    public static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        // 인접리스트로 관리합니다.
        for(int i = 1; i <= m; i++) {
            int a = sc.nextInt();
            int k = sc.nextInt();

            while(k-- > 0) {
                int x = sc.nextInt();

                // a번 조각을 만들기 위해서는 x번 조각이 필요합니다.
                // 간선의 방향을 주의합니다.
                edges[x].add(a); 
                indegree[a]++; // 진입차수를 갱신합니다.  
            }
        }

        // 현재 가지고 있는 조각들을 입력받습니다.
        parts = sc.nextInt();
        for(int i = 1; i <= parts; i++) {
            int x = sc.nextInt();
            q.add(x);

            visited[x] = true;
        }
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        while(!q.isEmpty()) {
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();

            // 만들 수 있는 조각 목록에 x를 추가합니다.
            ans.add(x);

            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);

                // 이미 만들 수 있는 조각이면 넘어갑니다.
                if(visited[y]) 
                    continue;

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // 비로소 indegree가 0이 되었다면
                // 해당 노드는 만들 수 있습니다.
                // queue에 새로 넣어주고, visited 배열을 갱신합니다.
                if(indegree[y] == 0) {
                    q.add(y);
                    visited[y] = true;
                }
            }
        }

        // 정답을 오름차순으로 정렬합니다.
        Collections.sort(ans);

        // 정답을 순서대로 출력합니다.
        System.out.println(ans.size());
        for(int i = 0; i < ans.size(); i++)
            System.out.print(ans.get(i) + " ");
    }
}
*/