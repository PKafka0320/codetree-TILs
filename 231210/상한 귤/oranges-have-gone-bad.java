import java.util.*;

class Pair {
    int row, col;
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static int n, k;
    static int[][] grid;
    static int[][] result;
    static boolean[][] visited;
    static Queue<Pair> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        grid = new int[n][n];
        result = new int[n][n];
        visited = new boolean[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int num = sc.nextInt();
                grid[row][col] = num;
                if (num == 0) result[row][col] = -1;
                if (num == 2) push(row, col, 0);
            }
        }

        makeResult();
        postTreatment();

        for (int[] line : result) {
            for (int num : line) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void postTreatment() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1 && result[row][col] == 0) {
                    result[row][col] = -2;
                }
            }
        }
    }

    public static void makeResult() {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int pRow = pair.row;
            int pCol = pair.col;

            for (int dir = 0; dir < 4; dir++) {
                int nRow = pRow + dr[dir];
                int nCol = pCol + dc[dir];

                if (invalidPosition(nRow, nCol)) continue;
                push(nRow, nCol, result[pRow][pCol] + 1);
            }
        }
    }

    public static void push(int r, int c, int time) {
        result[r][c] = time;
        visited[r][c] = true;
        q.add(new Pair(r, c));
    }

    public static boolean invalidPosition(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n) return true;
        if (visited[r][c] || grid[r][c] != 1) return true;
        return false;
    }
}