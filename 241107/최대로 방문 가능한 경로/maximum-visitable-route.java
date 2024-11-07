import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] edges = new ArrayList[N + 1];
		int[] maxCount = new int[N + 1];
		int[] beforeNode = new int[N + 1];
		int[] indegree = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			edges[i] = new ArrayList<>();
			beforeNode[i] = -1;
			maxCount[i] = -1;
		}
		maxCount[N] = 1;

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			edges[from].add(to);
			indegree[to]++;
		}

		Queue<Integer> queue = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int next : edges[cur]) {
				if (maxCount[cur] != -1) {
					if (maxCount[next] < maxCount[cur] + 1) {
						maxCount[next] = maxCount[cur] + 1;
						beforeNode[next] = cur;
					} else if (maxCount[next] == maxCount[cur] + 1 && beforeNode[next] > cur) {
						beforeNode[next] = cur;
					}
				}

				if (--indegree[next] == 0) {
					queue.add(next);
				}
			}
		}

		if (maxCount[1] == -1) {
			System.out.println(-1);
			return;
		}

		StringBuilder answer = new StringBuilder();
		answer.append(maxCount[1]).append("\n");
		int current = 1;
		while (current != N) {
			answer.append(current).append(" ");
			current = beforeNode[current];
		}
		answer.append(N);
		System.out.println(answer.toString());
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int INT_MIN = Integer.MIN_VALUE;
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // dist[i] : i번 노드까지의 최대 거리
    public static int[] dist = new int[MAX_N + 1];
    
    // before[i] : i번 노드에서 최대 거리로 이동할 때
    // 다음으로 가야할 노드 번호
    public static int[] before = new int[MAX_N + 1];
    
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

            // 사전순으로 가장 앞선 답을 찾기 위해
            // 그래프를 역순으로 만들고, n번 노드에서 1번 노드로 가겠습니다.
            edges[y].add(x);
            indegree[x]++; // 진입차수를 갱신합니다.  
        }

        // 모든 거리를 매우 작은 값으로 초기화합니다.
        for(int i = 1; i <= n; i++)
            dist[i] = INT_MIN;

        // n번 노드만 거리를 0으로 해줍니다.
        dist[n] = 0;

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

                // dist 정보를 갱신합니다.
                if(dist[x] != INT_MIN) {
                    if(dist[y] < dist[x] + 1) {
                        dist[y] = dist[x] + 1;
                        before[y] = x;
                    }
                    // 값이 동일한 경우에는 
                    // 추후 사전순으로 앞선 답을 만들어주기 위해 더 작은 값을 넣어줍니다.
                    else if(dist[y] == dist[x] + 1 && before[y] > x) {
                        before[y] = x;
                    }
                }

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // 비로소 indegree가 0이 되었다면
                // queue에 새로 넣어줍니다.
                if(indegree[y] == 0) {
                    q.add(y);
                }
            }
        }

        // dist[1]에 값이 들어가 있지 않다면,
        // 1번 노드에서 n번 노드로 가는 길이 없습니다.
        if(dist[1] == INT_MIN) {
            System.out.print(-1);
            System.exit(0);
        }

        // 1번 노드에서부터 before을 타고 가
        // n번 노드까지 경로를 저장합니다.
        ArrayList<Integer> ans = new ArrayList<>();
        int cur = 1;
        ans.add(cur);
        while(cur != n) {
            cur = before[cur];
            ans.add(cur);
        }

        // 정답과 경로를 출력합니다.
        System.out.println(ans.size());
        for(int i = 0; i < ans.size(); i++)
            System.out.print(ans.get(i) + " ");
    }
}
*/