import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Node {
    int index, dist;
    
    public Node(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
};

class Element implements Comparable<Element> {
    int dist, index;
    
    public Element(int dist, int index) {
        this.dist = dist;
        this.index = index;
    }

    @Override
    public int compareTo(Element e) {
        return this.dist - e.dist;   // dist 기준 오름차순 정렬
    }
};

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    public static int a, b;
    public static ArrayList<Node>[] graph = new ArrayList[MAX_N + 1];
    public static PriorityQueue<Element> pq = new PriorityQueue<>();
    
    public static int[] redDist1 = new int[MAX_N + 1];
    public static int[] redDist2 = new int[MAX_N + 1];
    
    public static int ans = INT_MAX;
    
    // k를 시작점으로 하는 다익스트라 알고리즘을 진행합니다.
    public static void dijkstra(int k, int[] dist) {
        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= n; i++)
            dist[i] = (int)1e8;
    
        // 시작위치에는 dist값을 0으로 설정
        dist[k] = 0;
    
        // 우선순위 큐에 시작점을 넣어줍니다.
        // 거리가 가까운 곳이 먼저 나와야 하며
        // 해당 지점이 어디인지에 대한 정보도 필요하므로
        // (거리, 정점 번호) 형태로 넣어줘야 합니다.
        pq.add(new Element(0, k));
    
        // O(|E|log|V|) 다익스트라 코드
        // 우선순위 큐에
        // 원소가 남아있다면 계속 진행해줍니다.
        while(!pq.isEmpty()) {
            // 가장 거리가 가까운 정보를 받아온 뒤, 원소를 제거해줍니다.
            int minDist = pq.peek().dist;
            int minIndex = pq.peek().index;
            pq.poll();
    
            // 우선순위 큐를 이용하면
            // 같은 정점의 원소가 
            // 여러 번 들어가는 문제가 발생할 수 있어
            // minDist가 최신 dist[minIndex]값과 다르다면
            // 계산할 필요 없이 패스해줍니다.
            if(minDist != dist[minIndex])
                continue;
    
            // 최솟값에 해당하는 정점에 연결된 간선들을 보며
            // 시작점으로부터의 최단거리 값을 갱신해줍니다.
            for(int j = 0; j < graph[minIndex].size(); j++) {
                int targetIndex = graph[minIndex].get(j).index;
                int targetDist = graph[minIndex].get(j).dist;
                
                // 현재 위치에서 연결된 간선으로 가는 것이 더 작다면
                int newDist = dist[minIndex] + targetDist;
                if(dist[targetIndex] > newDist) {
                    // 값을 갱신해주고, 우선순위 큐에 해당 정보를 넣어줍니다.
                    dist[targetIndex] = newDist;
                    pq.add(new Element(newDist, targetIndex));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        n = sc.nextInt();
        m = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();

        // 그래프를 인접리스트로 표현합니다.
        for(int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        // 양방향 그래프이므로
        // 양쪽에 추가해줘야함에 유의합니다.
        while(m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            graph[x].add(new Node(y, z));
            graph[y].add(new Node(x, z));
        }
        
        // 시작점을 a, b로 하는 다익스트라를 각각 1번씩 수행합니다.
        dijkstra(a, redDist1);
        dijkstra(b, redDist2);

        // 각 시작점 x에 대해
        // 검정점 x -> 빨간점 1, 2 -> 검정점 x
        // 순서로 이동하는 최단거리의 합 중
        // 최솟값을 찾습니다.
        for(int i = 1; i <= n; i++) {
            // i가 검정점이 아니라면
            // 패스합니다.
            if(i == a || i == b)
                continue;

            // 검정점 i -> 빨간점 1 -> 빨간점 2 -> 검정점 i
            // 순으로 돌아오는 경우입니다.
            if(redDist1[i] != (int)1e8 && redDist1[b] != (int)1e8 
            && redDist2[i] != (int)1e8)
                ans = Math.min(ans, redDist1[i] + redDist1[b] + redDist2[i]);

            // 검정점 i -> 빨간점 2 -> 빨간점 1 -> 검정점 i
            // 순으로 돌아오는 경우입니다.
            if(redDist2[i] != (int)1e8 && redDist2[a] != (int)1e8 
            && redDist1[i] != (int)1e8)
            ans = Math.min(ans, redDist2[i] + redDist2[a] + redDist1[i]);
        }

        // 불가능하다면 -1을 출력합니다.
        if(ans == INT_MAX)
            ans = -1;
        
        System.out.print(ans);
    }
}