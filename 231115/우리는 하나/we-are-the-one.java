import java.util.*;

// 2차원 좌표 클래스
class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, k, u, d;
    static int[][] cityHight;
    static boolean[][] visited;
    static ArrayList<Integer> numbers = new ArrayList<>(); // 갈 수 있는 도시의 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        u = sc.nextInt();
        d = sc.nextInt();
        cityHight = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cityHight[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bfs(i, j);
            }
        }

        numbers.sort(Comparator.reverseOrder());
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += numbers.get(i);
        }

        System.out.println(sum);
    }

    // [x,y] 에서 갈 수 있는 도시의 수를 구한다.
    public static void bfs(int x, int y) {
        if (visited[x][y]) {
            return;
        }

        // 갈 수 있는 방향
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int count = 1;
        Queue<Pair> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Pair(x, y));
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (canMove(nx, ny, cityHight[p.x][p.y])) {
                    visited[nx][ny] = true;
                    count++;
                    q.add(new Pair(nx, ny));
                }
            }
        }
        numbers.add(count);
    }

    public static boolean canMove(int x, int y, int hight) {
        if (x < 0 || x >= n || y < 0 || y >= n) {
            return false;
        }
        int hightDiff = Math.abs(cityHight[x][y] - hight);
        if (visited[x][y] || hightDiff < u || hightDiff > d) {
            return false;
        }
        return true;
    }
}