import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int x, y;
    public Pair(int x, int y) { 
        this.x = x; 
        this.y = y; 
    }
}

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 100;
    
    // 전역 변수 선언:
    public static int n, h, m;
    
    public static int[][] a = new int[MAX_N][MAX_N];

    public static ArrayList<Pair> sPos = new ArrayList<>();
    
    // bfs에 필요한 변수들 입니다.
    public static Queue<Pair> q = new LinkedList<>();
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];
    public static int[][] step = new int[MAX_N][MAX_N]; // step[i][j] : 시작점으로부터 
                            // (i, j) 지점에 도달하기 위한 
                            // 최단거리를 기록합니다.
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    // 격자를 벗어나지 않으면서, 벽도 없고, 아직 방문한 적이 없는 곳이라면
    // 지금 이동하는 것이 최단거리임을 보장할 수 있으므로 가야만 합니다. 
    public static boolean canGo(int x, int y) {
        return inRange(x, y) && a[x][y] != 1 && !visited[x][y];
    }
    
    // queue에 새로운 위치를 추가하고
    // 방문 여부를 표시해줍니다.
    // 시작점으로 부터의 최단거리 값도 갱신해줍니다.
    public static void push(int nx, int ny, int newStep) {
        q.add(new Pair(nx, ny));
        visited[nx][ny] = true;
        step[nx][ny] = newStep;
    }
    
    // bfs를 통해 최소 이동 횟수를 구합니다.
    public static void BFS() {
        // queue에 남은 것이 없을때까지 반복합니다.
        while(!q.isEmpty()) {
            // queue에서 가장 먼저 들어온 원소를 뺍니다.
            Pair currPos = q.poll();
            int x = currPos.x, y = currPos.y;
    
            int[] dx = new int[]{-1, 1, 0, 0};
            int[] dy = new int[]{0, 0, -1, 1};
    
            // queue에서 뺀 원소의 위치를 기준으로 4방향을 확인해봅니다.
            for(int dir = 0; dir < DIR_NUM; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];
    
                // 아직 방문한 적이 없으면서 갈 수 있는 곳이라면
                // 새로 queue에 넣어줍니다.
                if(canGo(nx, ny))
                    // 최단 거리는 이전 최단거리에 1이 증가하게 됩니다. 
                    push(nx, ny, step[x][y] + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        h = sc.nextInt();
        m = sc.nextInt();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
                if(a[i][j] == 3)
                    sPos.add(new Pair(i, j));
            }
        
        // 비를 피할 수 있는 공간들을 전부 시작점으로 하는
        // bfs를 진행합니다.
        // 이는 각 사람마다 가장 가까운 쉘터까지의 거리를 
        // 단 한번의 bfs로 구할 수 있도록 합니다.
        for(int i = 0; i < (int) sPos.size(); i++)
            push(sPos.get(i).x, sPos.get(i).y, 0);
        
        BFS();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(a[i][j] != 2)
                    System.out.print(0 + " ");
                else {
                    if(!visited[i][j])
                        System.out.print(-1 + " ");
                    else
                        System.out.print(step[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}