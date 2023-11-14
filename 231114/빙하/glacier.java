import java.util.*;

// x, y 좌표 객체
class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// 2개의 큐를 교대로 bfs를 시행한다.
// bfs를 시행하지 않는 큐에 다음에 녹을 위치를 추가한다.
public class Main {
    static int n;
    static int m;
    static int[][] grid;
    static boolean[][] visited;
    static Queue<Pair> q1 = new LinkedList<>();
    static Queue<Pair> q2 = new LinkedList<>();
    static int time = 0;
    static int number = 1;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        visited = new boolean[n][m];

        // 배열 초기화
        // 가장자리의 0을 큐에 추가한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
                visited[i][j] = false;
                if (grid[i][j] == 0 && isEdge(i, j)) {
                    q1.add(new Pair(i, j));
                    visited[i][j] = true;
                }
            }
        }

        int count = 0;
        // 다음에 녹을 빙하가 없을 경우 종료
        while (true) {
            count = bfs(q1, q2);
            // System.out.println("------------------count: " + count);
            if (count == 0) {
                break;
            }
            time++;
            number = count;

            count = bfs(q2, q1);
            // System.out.println("------------------count: " + count);
            if (count == 0) {
                break;
            }
            time++;
            number = count;
        }

        System.out.printf("%d %d", time, number);
    }

    // 가장자리인지 판단한다.
    public static boolean isEdge(int x, int y) {
        return (x == 0 || x == n - 1 || y == 0 || y == m - 1);
    }

    // q1의 위치에서 다음에 녹는 위치를 q2에 추가한다.
    public static int bfs(Queue<Pair> q1, Queue<Pair> q2) {
        int count = 0;

        while (!q1.isEmpty()) {
            Pair pair = q1.poll();
            int x = pair.x;
            int y = pair.y;
            // System.out.printf("visit %d, %d\n", x, y);
            visited[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (canMove(nx, ny)) {
                    if (grid[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        q1.add(new Pair(nx, ny));
                    }
                    if (grid[nx][ny] == 1) {
                        count++;
                        visited[nx][ny] = true;
                        grid[nx][ny] = 0;
                        q2.add(new Pair(nx, ny));
                    }
                }
            }
        }
        return count;
    }

    // 유효한 범위의 x, y 값인지 검사한다.
    public static boolean canMove(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        if (visited[x][y]) {
            return false;
        }
        return true;
    }
}