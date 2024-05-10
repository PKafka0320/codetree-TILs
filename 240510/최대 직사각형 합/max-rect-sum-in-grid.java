import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        int[][] grid = new int[n + 1][n + 1];
        int[][] prefixSum = new int[n + 1][n + 1];

        // 입력
        for (int row = 1; row <= n; row++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            for (int col = 1; col <= n; col++) {
                grid[row][col] = Integer.parseInt(token.nextToken());
            }
        }

        // prefixSum 계산
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                prefixSum[row][col] = grid[row][col] + prefixSum[row - 1][col] 
                    + prefixSum[row][col - 1] - prefixSum[row - 1][col - 1];
            }
        }

        int max = Integer.MIN_VALUE;
        // 사각형 범위에 따른 합의 최댓값
        for (int r1 = 0; r1 < n; r1++) {
            for (int c1 = 0; c1 < n; c1++) {
                for (int r2 = r1 + 1; r2 <= n; r2++) {
                    for (int c2 = c1 + 1; c2 <= n; c2++) {
                        int tmp = prefixSum[r2][c2] - prefixSum[r2][c1] 
                            - prefixSum[r1][c2] + prefixSum[r1][c1];
                        max = Math.max(max, tmp);
                    }
                }
            }
        }

        System.out.println(max);
    }
}