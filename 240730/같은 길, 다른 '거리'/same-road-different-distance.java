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
    public static final int MAX_N = 10000;
    
    // 변수 선언
    public static int n, m;
    public static ArrayList<Node>[] revAGraph = new ArrayList[MAX_N + 1];
    public static ArrayList<Node>[] revBGraph = new ArrayList[MAX_N + 1];
    public static ArrayList<Node>[] aGraph = new ArrayList[MAX_N + 1];
    public static ArrayList<Node>[] bGraph = new ArrayList[MAX_N + 1];
    public static PriorityQueue<Element> pq = new PriorityQueue<>();

    public static int[] dist = new int[MAX_N + 1];
    public static int[] aDist = new int[MAX_N + 1];
    public static int[] bDist = new int[MAX_N + 1];
    
    // k를 시작점으로 하는 다익스트라 알고리즘을 진행합니다.
    public static void dijkstra(int k, int[] dist, ArrayList<Node>[] graph) {
        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= n; i++)
            dist[i] = (int)1e9;
    
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
    
    // 변형된 Dijkstra 알고리즘을 이용하여
    // 최단거리를 계산합니다.
    public static void modifiedDijkstra(int[] dis, int[] aDist, int[] bDist, 
                          ArrayList<Node>[] aGraph,
                          ArrayList<Node>[] bGraph) {
        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= n; i++)
            dist[i] = (int)1e9;
    
        // 시작위치에는 dist값을 0으로 설정
        dist[1] = 0;
    
        // 우선순위 큐에 시작점을 넣어줍니다.
        // 거리가 가까운 곳이 먼저 나와야 하며
        // 해당 지점이 어디인지에 대한 정보도 필요하므로
        // (거리, 정점 번호) 형태로 넣어줘야 합니다.
        pq.add(new Element(0, 1));
    
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
            for(int j = 0; j < aGraph[minIndex].size(); j++) {
                int targetIndex = aGraph[minIndex].get(j).index;
                int aCost = aGraph[minIndex].get(j).dist;
                int bCost = bGraph[minIndex].get(j).dist;
                
                // 그림 A, B에 대해
                // 최단거리에 맞는 간선이 아닌 경우
                // 경고 횟수를 1씩 세줍니다.
                int targetDist = 0;
                if(aDist[targetIndex] + aCost != aDist[minIndex])
                    targetDist++;
                if(bDist[targetIndex] + bCost != bDist[minIndex])
                    targetDist++;
                
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

        // 그래프를 인접리스트로 표현합니다.
        for(int i = 1; i <= n; i++) {
            aGraph[i] = new ArrayList<>();
            bGraph[i] = new ArrayList<>();
            revAGraph[i] = new ArrayList<>();
            revBGraph[i] = new ArrayList<>();
        }

        while(m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z1 = sc.nextInt();
            int z2 = sc.nextInt();
            aGraph[x].add(new Node(y, z1));
            bGraph[x].add(new Node(y, z2));

            // 간선을 뒤집은 형태의 
            // 그래프도 미리 만들어둡니다.
            revAGraph[y].add(new Node(x, z1));
            revBGraph[y].add(new Node(x, z2));
        }
        
        // 그래프 간선 방향을 거꾸로 뒤집은 그래프에 대해
        // n번 정점을 시작으로 하는 다익스트라를
        // A번 그래프, B번 그래프에 대해 각각 1번씩 수행합니다.
        dijkstra(n, aDist, revAGraph);
        dijkstra(n, bDist, revBGraph);

        // 처음 주어진 그래프 방향대로 그대로 이용하되
        // 그래프 간선의 가중치의 경우
        // 그림 A, B에 대해
        // 최단거리가 아닌 횟수로
        // 0, 1, 2 중 하나로 계산하여 
        // 변형된 Dijkstra 알고리즘을 이용하여
        // 최종 최단거리를 계산합니다.
        modifiedDijkstra(dist, aDist, bDist, aGraph, bGraph);

        int ans = dist[n];
        System.out.print(ans);
    }
}