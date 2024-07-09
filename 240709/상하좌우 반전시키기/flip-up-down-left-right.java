import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 격자의 크기
        grid = new int[n][n]; // [i][j]: i행 j열의 숫자
        int count = 0; // 반전 횟수
        
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 해당 위치의 위쪽에 0이 있을 경우에만 반전
        for (int row = 1; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row - 1][col] == 1) continue;
                count++;
                reverse(row, col);
            }
        }

        // 맨 밑에 0이 있을 경우 불가능하다고 판단
        for (int col = 0; col < n; col++) {
            if (grid[n - 1][col] == 1) continue;
            System.out.println(-1);
            return;
        }
        System.out.println(count);
    }

    public static void reverse(int row, int col) {
        int[] dr = {0, 0, 0, 1, -1};
        int[] dc = {0, 1, -1, 0, 0};

        for (int dir = 0; dir < 5; dir++) {
            int nr = row + dr[dir];
            int nc = col + dc[dir];

            if (outOfRange(nr, nc)) continue;

            grid[nr][nc] = 1 - grid[nr][nc];
        }
    }

    public static boolean outOfRange(int row, int col) {
        return (row < 0 || row >= n || col < 0 || col >= n);
    }
}