import java.io.*;
import java.util.*;

class Path {
	int node, distance;
	
	public Path (int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
}

class Route implements Comparable<Route> {
	int node, distance;

	public Route(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}

	@Override
	public int compareTo(Route o) {
		return this.distance - o.distance;
	}
}

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 정점의 개수, 간선의 개수 입력
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	// a, b, c 에서 시작해서 각 정점까지의 최단거리 배열 생성 및 초기화
    	st = new StringTokenizer(br.readLine());
    	int startA = Integer.parseInt(st.nextToken()) - 1;
    	int startB = Integer.parseInt(st.nextToken()) - 1;
    	int startC = Integer.parseInt(st.nextToken()) - 1;
    	int distanceA[] = new int[n];
    	int distanceB[] = new int[n];
    	int distanceC[] = new int[n];
    	Arrays.fill(distanceA, -1);
    	Arrays.fill(distanceB, -1);
    	Arrays.fill(distanceC, -1);
    	distanceA[startA] = 0;
    	distanceB[startB] = 0;
    	distanceC[startC] = 0;
    	
    	// 인접 리스트 양방향 그래프 생성 및 데이터 입력
    	ArrayList<Path>[] graph = new ArrayList[n];
    	for (int idx = 0; idx < n; idx++) {
    		graph[idx] = new ArrayList<>();
    	}
    	
    	for (int idx = 0; idx < m; idx++) {
    		st = new StringTokenizer(br.readLine());
    		int node1 = Integer.parseInt(st.nextToken()) - 1;
    		int node2 = Integer.parseInt(st.nextToken()) - 1;
    		int distance = Integer.parseInt(st.nextToken());
    		
    		graph[node1].add(new Path(node2, distance));
    		graph[node2].add(new Path(node1, distance));
    	}

    	// a에서 시작하는 다익스트라
    	PriorityQueue<Route> routesA = new PriorityQueue<>();
    	routesA.add(new Route(startA, 0));
    	while (!routesA.isEmpty()) {
    		Route currentRoute = routesA.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (currentDistance != distanceA[currentNode]) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			
    			if (distanceA[nextNode] == -1 ||
    					distanceA[nextNode] > distanceA[currentNode] + nextDistance) {
    				distanceA[nextNode] = distanceA[currentNode] + nextDistance;
    				routesA.add(new Route(nextNode, distanceA[nextNode]));
    			}
    		}
    	}
    	
    	// b에서 시작하는 다익스트라
    	PriorityQueue<Route> routesB = new PriorityQueue<>();
    	routesB.add(new Route(startB, 0));
    	while (!routesB.isEmpty()) {
    		Route currentRoute = routesB.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (currentDistance != distanceB[currentNode]) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			
    			if (distanceB[nextNode] == -1 ||
    					distanceB[nextNode] > distanceB[currentNode] + nextDistance) {
    				distanceB[nextNode] = distanceB[currentNode] + nextDistance;
    				routesB.add(new Route(nextNode, distanceB[nextNode]));
    			}
    		}
    	}
    	
    	// c에서 시작하는 다익스트라
    	PriorityQueue<Route> routesC = new PriorityQueue<>();
    	routesC.add(new Route(startC, 0));
    	while (!routesC.isEmpty()) {
    		Route currentRoute = routesC.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (currentDistance != distanceC[currentNode]) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			
    			if (distanceC[nextNode] == -1 ||
    					distanceC[nextNode] > distanceC[currentNode] + nextDistance) {
    				distanceC[nextNode] = distanceC[currentNode] + nextDistance;
    				routesC.add(new Route(nextNode, distanceC[nextNode]));
    			}
    		}
    	}
    	
    	// 각 정점까지의 거리중 가장 가까운 거리의 최댓값 계산
    	int maxDistance = 0;
    	for (int idx = 0; idx < n; idx++) {
    		int tmpDistance = Integer.MAX_VALUE; 
    		tmpDistance = Math.min(tmpDistance, distanceA[idx]);
    		tmpDistance = Math.min(tmpDistance, distanceB[idx]);
    		tmpDistance = Math.min(tmpDistance, distanceC[idx]);
    		maxDistance = Math.max(maxDistance, tmpDistance);
    	}
    	
    	System.out.println(maxDistance);
    }
}

// import java.util.Scanner;
// import java.util.ArrayList;
// import java.util.PriorityQueue;

// class Node {
//     int index, dist;
    
//     public Node(int index, int dist) {
//         this.index = index;
//         this.dist = dist;
//     }
// };

// class Element implements Comparable<Element> {
//     int dist, index;
    
//     public Element(int dist, int index) {
//         this.dist = dist;
//         this.index = index;
//     }

//     @Override
//     public int compareTo(Element e) {
//         return this.dist - e.dist;   // dist 기준 오름차순 정렬
//     }
// };

// public class Main {
//     public static final int INT_MIN = Integer.MIN_VALUE;
//     public static final int INT_MAX = Integer.MAX_VALUE;
//     public static final int MAX_N = 100000;
    
//     // 변수 선언
//     public static int n, m;
//     public static int a, b, c;
//     public static ArrayList<Node>[] graph = new ArrayList[MAX_N + 1];
//     public static PriorityQueue<Element> pq = new PriorityQueue<>();
    
//     public static int[] dist = new int[MAX_N + 1];
//     public static int[] abcDist = new int[MAX_N + 1];
    
//     public static int ans = INT_MIN;
    
//     // k를 시작점으로 하는 다익스트라 알고리즘을 진행합니다.
//     public static void dijkstra(int k) {
//         // 그래프에 있는 모든 노드들에 대해
//         // 초기값을 전부 아주 큰 값으로 설정
//         // INT_MAX 그 자체로 설정하면
//         // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
//         // 적당히 큰 숫자로 적어줘야함에 유의!
//         for(int i = 1; i <= n; i++)
//             dist[i] = (int)1e9;
    
//         // 시작위치에는 dist값을 0으로 설정
//         dist[k] = 0;
    
//         // 우선순위 큐에 시작점을 넣어줍니다.
//         // 거리가 가까운 곳이 먼저 나와야 하며
//         // 해당 지점이 어디인지에 대한 정보도 필요하므로
//         // (거리, 정점 번호) 형태로 넣어줘야 합니다.
//         pq.add(new Element(0, k));
    
//         // O(|E|log|V|) 다익스트라 코드
//         // 우선순위 큐에
//         // 원소가 남아있다면 계속 진행해줍니다.
//         while(!pq.isEmpty()) {
//             // 가장 거리가 가까운 정보를 받아온 뒤, 원소를 제거해줍니다.
//             int minDist = pq.peek().dist;
//             int minIndex = pq.peek().index;
//             pq.poll();
    
//             // 우선순위 큐를 이용하면
//             // 같은 정점의 원소가 
//             // 여러 번 들어가는 문제가 발생할 수 있어
//             // minDist가 최신 dist[minIndex]값과 다르다면
//             // 계산할 필요 없이 패스해줍니다.
//             if(minDist != dist[minIndex])
//                 continue;
    
//             // 최솟값에 해당하는 정점에 연결된 간선들을 보며
//             // 시작점으로부터의 최단거리 값을 갱신해줍니다.
//             for(int j = 0; j < graph[minIndex].size(); j++) {
//                 int targetIndex = graph[minIndex].get(j).index;
//                 int targetDist = graph[minIndex].get(j).dist;
                
//                 // 현재 위치에서 연결된 간선으로 가는 것이 더 작다면
//                 int newDist = dist[minIndex] + targetDist;
//                 if(dist[targetIndex] > newDist) {
//                     // 값을 갱신해주고, 우선순위 큐에 해당 정보를 넣어줍니다.
//                     dist[targetIndex] = newDist;
//                     pq.add(new Element(newDist, targetIndex));
//                 }
//             }
//         }
    
//         // 각 지점에 대한 최단거리 값 중 최솟값을 갱신해줍니다. 
//         for(int i = 1; i <= n; i++)
//             abcDist[i] = Math.min(abcDist[i], dist[i]);
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력
//         n = sc.nextInt();
//         m = sc.nextInt();
//         a = sc.nextInt();
//         b = sc.nextInt();
//         c = sc.nextInt();

//         // 그래프를 인접리스트로 표현합니다.
//         for(int i = 1; i <= n; i++)
//             graph[i] = new ArrayList<>();

//         // 양방향 그래프이므로
//         // 양쪽에 추가해줘야함에 유의합니다.
//         while(m-- > 0) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();
//             int z = sc.nextInt();

//             graph[x].add(new Node(y, z));
//             graph[y].add(new Node(x, z));
//         }

//         // a, b, c로부터 각 정점까지의 최단거리 중 최솟값을 저장할 
//         // minDist 배열을 전부 INT_MAX로 초기화해줍니다.
//         for(int i = 1; i <= n; i++)
//             abcDist[i] = INT_MAX;
        
//         // 시작점을 a, b, c로 하는 다익스트라를 각각 1번씩 수행합니다.
//         dijkstra(a);
//         dijkstra(b);
//         dijkstra(c);

//         // a, b, c로부터 각 정점까지의 최단거리 중 최솟값을 전부 순회하며
//         // 이 중 최댓값을 계산해줍니다.
//         for(int i = 1; i <= n; i++)
//             ans = Math.max(ans, abcDist[i]);

//         System.out.print(ans);
//     }
// }