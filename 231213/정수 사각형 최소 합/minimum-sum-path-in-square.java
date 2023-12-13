import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[][] grid;
    static int[][] minSum;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        grid = new int[N][N];
        minSum = new int[N][N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        minSum[0][N - 1] = grid[0][N - 1];

        for (int col = N - 2; col >= 0; col--) {
            minSum[0][col] = grid[0][col] + minSum[0][col + 1];
        }

        for (int row = 1; row < N; row++) {
            minSum[row][N - 1] = grid[row][N - 1] + minSum[row - 1][N - 1];
        }

        for (int row = 1; row < N; row++) {
            for (int col = N - 2; col >= 0; col--) {
                minSum[row][col] = Math.min(minSum[row - 1][col], minSum[row][col + 1]) + grid[row][col];
            }
        }

        System.out.println(minSum[N - 1][0]);
    }
}