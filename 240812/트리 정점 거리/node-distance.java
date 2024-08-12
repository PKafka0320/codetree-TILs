import java.io.*;
import java.util.*;

class Path {
    int node, dist;
    
    public Path(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}

public class Main {
    static boolean[] visited;
    static List<Path>[] tree;
    static int n, m, result;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 구하ㅗ자 하는 정점쌍의 개수
        
        tree = new ArrayList[n]; // 트리 생성
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        
        // 트리 데이터 입력, 순회를 위해 양방향으로 생성
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());
            
            tree[node1].add(new Path(node2, dist));
            tree[node2].add(new Path(node1, dist));
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            visited = new boolean[n];
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            
            result = -1;
            dfs(node1, node2, 0);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
    
    public static void dfs(int currentNode, int targetNode, int totalDist) {
        if (currentNode == targetNode) { // 목적 노드에 도달하면 종료
            result = totalDist;
            return;
        }
        
        visited[currentNode] = true;
        
        for (Path nextPath : tree[currentNode]) {
            int nextNode = nextPath.node;
            int nextDist = nextPath.dist;
            
            if (visited[nextNode]) continue;
            
            dfs(nextNode, targetNode, totalDist + nextDist);
        }
        
    }

}