import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언:
    public static int n;
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];
    public static int[] depth = new int[MAX_N + 1];
    
    // 리프노드 깊이의 총합
    public static int ans;
    
    // DFS를 통해 리프노드와 리프노드의 깊이를 탐색합니다.
    public static void dfs(int x) {
        boolean isLeaf = true;
    
        for(int i = 0; i < edges[x].size(); i++) {
            int y = edges[x].get(i);
            // 이미 방문한 노드는 스킵합니다.
            if(visited[y]) continue;
    
            visited[y] = true;
    
            // 하나라도 자식 노드가 있다면 리프 노드가 아닙니다.
            isLeaf = false;
            
            // root로부터의 거리를 갱신합니다.
            depth[y] = depth[x] + 1;
    
            dfs(y);
        }
    
        // 리프노드라면, 해당 노드의 깊이를 더합니다.
        if(isLeaf) 
            ans += depth[x];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        
        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        // n - 1개의 간선 정보를 입력받습니다.
        for(int i = 1; i <= n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            // 간선 정보를 인접리스트에 넣어줍니다.
            edges[x].add(y);
            edges[y].add(x);
        }

        // DFS를 통해 리프노드와 리프노드의 깊이를 탐색합니다.
        visited[1] = true;
        dfs(1);

        // 모든 리프노드의 깊이의 합이 짝수인지 홀수인지 판단해 정답을 출력합니다.
        if(ans % 2 == 1) System.out.print(1);
        else System.out.print(0);
    }
}