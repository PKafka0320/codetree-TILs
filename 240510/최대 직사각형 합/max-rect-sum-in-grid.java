import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dp;
    static int[][] grid;
    static int[][] prefixSum;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());
        grid = new int[n + 1][n + 1];
        prefixSum = new int[n + 1][n + 1];
        dp = new int[n + 1];

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
        // 직사각형의 시작 행과 끝 행을 결정해서 각 쌍에 대해 가능한 직사각형 중 최대 합을 계산
        for (int col1 = 1; col1 <= n; col1++) {
            for (int col2 = col1; col2 <= n; col2++) {
                max = Math.max(max, getMaxArea(col1, col2));
            }
        }

        System.out.println(max);
    }

    public static int getMaxArea(int x1, int x2) {
        for(int y = 1; y <= n; y++) {
            // 각 열의 합 계산
            int value = getSum(x1, y, x2, y);
    
            // 이전까지의 합과 비교해서 최댓값 갱신
            dp[y] = Math.max(value, dp[y - 1] + value);
        }
    
        // dp 값 중 최댓값 확인
        int maxArea = Integer.MIN_VALUE;
        for(int y = 1; y <= n; y++) {
            maxArea = Math.max(maxArea, dp[y]);
        }

        return maxArea;
    }

    // (x1, y1), (x2, y2) 직사각형 구간 내의 원소의 합 계산
    public static int getSum(int x1, int y1, int x2, int y2) {
        return prefixSum[x2][y2] - prefixSum[x1 - 1][y2] 
            - prefixSum[x2][y1 - 1] + prefixSum[x1 - 1][y1 - 1];
    }
}