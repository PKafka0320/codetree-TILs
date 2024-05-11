import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] grid;
    static int[][] prefixSum;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken()); // 격자 크기
        K = Integer.parseInt(token.nextToken()); // 이동 가능한 거리

        grid = new int[N + 1][N + 1]; // 격자
        prefixSum = new int[N + 1][N + 1]; // 각 행의 누적합

        for (int row = 1; row <= N; row++) {
            token = new StringTokenizer(reader.readLine());
            for (int col = 1; col <= N; col++) {
                grid[row][col] = Integer.parseInt(token.nextToken());
            }
        }

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                prefixSum[row][col] = prefixSum[row][col - 1] + grid[row][col];
            }
        }

        // 모든 중심에 대한 최댓값 계산
        int ans = 0;
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                // 중심이 (row, col)일 때의 숫자 합 계산
                int sum = 0;
                for (int r = row - K; r <= row + K; r++) {
                    // r 행일 때 (col - c ~ col + c)열 까지의 부분합 계산
                    int c = K - Math.abs(row - r);

                    // r 행이 범위 안에 있을 경우 부분합 계산
                    if (1 <= r && r <= N) {
                        sum += prefixSum[r][Math.min(col + c, N)] - prefixSum[r][Math.max(col - c - 1, 0)];
                    }
                }

                ans = Math.max(ans, sum);
            }
        }

        System.out.println(ans);
    }
}