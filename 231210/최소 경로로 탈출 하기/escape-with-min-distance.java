import java.util.*;

class Pair {
    int row, col;
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static int n, m;
    static int[][] grid;
    static boolean[][] visited;
    static int[][] step;
    static Queue<Pair> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        visited = new boolean[n][m];
        step = new int[n][m];

        step[n - 1][m - 1] = -1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                grid[row][col] = sc.nextInt();
            }
        }

        push(0, 0, 0);
        find();
        System.out.println(step[n - 1][m - 1]);
    }

    public static void push(int row, int col, int dist) {
        step[row][col] = dist;
        visited[row][col] = true;
        q.add(new Pair(row, col));
    }

    public static void find() {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int pRow = pair.row;
            int pCol = pair.col;

            for (int dir = 0; dir < 4; dir++) {
                int nRow = pRow + dr[dir];
                int nCol = pCol + dc[dir];

                if (inValidPosition(nRow, nCol)) continue;
                push(nRow, nCol, step[pRow][pCol] + 1);
                if (nRow == n - 1 && nCol == m - 1) return;
            }
        }
    }

    public static boolean inValidPosition(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) return true;
        if (grid[r][c] == 0 || visited[r][c]) return true;
        return false;
    }
}