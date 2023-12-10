import java.util.*;

class Pair {
    int row, col, dist;
    public Pair(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}

public class Main {
    static int n, m;
    static int[][] grid;
    static boolean[][] visited;
    static Queue<Pair> q = new LinkedList<>();
    static int minDist = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = sc.nextInt();
            }
        }

        q.add(new Pair(0, 0, 0));
        visited[0][0] = true;
        find();
        System.out.println(minDist);
    }

    public static void find() {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int pRow = pair.row;
            int pCol = pair.col;
            int pDist = pair.dist;
            if ((pRow == n - 1 && pCol == n - 1) && (minDist == -1 || minDist > pDist)) {
                minDist = pDist;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nRow = pRow + dr[dir];
                int nCol = pCol + dc[dir];

                if (inValidPosition(nRow, nCol)) continue;
                visited[pRow][pCol] = true;
                q.add(new Pair(nRow, nCol, pDist + 1));
            }
        }
    }

    public static boolean inValidPosition(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n) return true;
        if (grid[r][c] == 0 || visited[r][c]) return true;
        return false;
    }
}