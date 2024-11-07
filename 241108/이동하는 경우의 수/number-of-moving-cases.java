import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		long totalWeight;
		int count;

		public Node(long totalWeight, int count) {
			this.totalWeight = totalWeight;
			this.count = count;
		}
	}

	static class Path {
		int node, weight;

		public Path(int node, int weight) {
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

		Node[] nodes = new Node[N + 1];
		List<Path>[] edges = new ArrayList[N + 1];
		int[] indegrees = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			nodes[i] = new Node(-1, -1);
		}
		nodes[1] = new Node(0, 0);

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[from].add(new Path(to, weight));
			indegrees[to]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (indegrees[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			Node curNode = nodes[cur];

			for (Path path : edges[cur]) {
				int next = path.node;
				int nextWeight = path.weight;

				if (curNode.totalWeight != -1) {
					Node nextNode = nodes[next];
					if (nextNode.totalWeight == -1 || nextNode.totalWeight < curNode.totalWeight + nextWeight) {
						nextNode.totalWeight = curNode.totalWeight + nextWeight;
						nextNode.count = curNode.count + 1;
					} else if (nextNode.totalWeight == curNode.totalWeight + nextWeight) {
						nextNode.count += curNode.count + 1;
					}
				}
				
				if (--indegrees[next] == 0) {
					queue.add(next);
				}
			}
		}

		Node Nnode = nodes[N];
		System.out.printf("%d %d", Nnode.totalWeight, Nnode.count);
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int y, d;

    public Pair(int y, int d) {
        this.y = y;
        this.d = d;
    }
}

public class Main {
    public static final int INT_MIN = Integer.MIN_VALUE;
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Pair>[] edges = new ArrayList[MAX_N + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_N + 1];
    
    // dist[i] : 1번 노드에서 i번 노드까지의 최대 거리
    public static int[] dist = new int[MAX_N + 1];
    
    // before[i] : 1번 노드에서 i번 노드까지 최대 거리로 이동하기 위해
    // i번 노드 바로 직전에 방문할 수 있는 노드 번호 리스트
    public static ArrayList<Integer>[] before = new ArrayList[MAX_N + 1];
    
    // 위상정렬을 위한 큐를 관리합니다.
    public static Queue<Integer> q = new LinkedList<>();
    
    // 마지막에 경로를 역순으로 돌아 정점을 방문하기 위해
    // 방문 여부를 저장합니다.
    public static boolean[] visited = new boolean[MAX_N + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
            before[i] = new ArrayList<>();
        }

        // 인접리스트로 관리합니다.
        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();

            edges[x].add(new Pair(y, d));
            indegree[y]++; // 진입차수를 갱신합니다.   
        }

        // 모든 거리를 매우 작은 값으로 초기화합니다.
        for(int i = 1; i <= n; i++)
            dist[i] = INT_MIN;

        // 1번 노드만 거리를 0으로 해줍니다.
        dist[1] = 0;

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
                int y = edges[x].get(i).y;
                int d = edges[x].get(i).d;

                // dist 정보를 갱신합니다.
                if(dist[x] != INT_MIN) {
                    if(dist[y] < dist[x] + d) {
                        dist[y] = dist[x] + d;
                        before[y] = new ArrayList<>();
                        before[y].add(x);
                    }
                    // 최적의 경로를 만들어 내기 위해
                    // 직전에 방문할 수 있는 위치가 여러 개라면 전부 관리합니다.
                    else if(dist[y] == dist[x] + d) {
                        before[y].add(x);
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

        // n번 노드에서부터 before을 타고 가
        // 경로에 해당하는 모든 간선의 수를 구합니다.
        // 최장 경로를 만들어 내면서 방문할 수 있는 모든 정점을
        // 전부 큐에 넣으며 순회합니다. 
        int cur = n;
        int cnt = 0;

        q.add(n);
        visited[n] = true;

        while(!q.isEmpty()) {
            int x = q.poll();

            for(int i = 0; i < before[x].size(); i++) {
                int y = before[x].get(i);

                // x번 - y번 노드 간의 간선은 정답에 해당하므로
                // 답을 갱신해줍니다.
                cnt++;

                // 가능한 노드가 겹치지 않게 해줍니다.
                if(visited[y]) 
                    continue;

                visited[y] = true;
                q.add(y);
            }
        }

        System.out.print(dist[n] + " " + cnt);
    }
}
*/