import java.util.*;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, k, u, d;
    static int[][] cityHight;
    static boolean[][] visited;
    static ArrayList<Integer> hights = new ArrayList<>();

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

        int selectCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bfs(i, j);
            }
        }

        hights.sort(Comparator.reverseOrder());
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += hights.get(i);
        }

        System.out.println(sum);
    }

    public static void bfs(int x, int y) {
        if (visited[x][y]) {
            return;
        }

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
        hights.add(count);
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