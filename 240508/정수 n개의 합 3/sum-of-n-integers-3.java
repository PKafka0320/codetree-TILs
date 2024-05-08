import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] grid = new int[n + 1][n + 1]; // 숫자 그리드
        int[][] sum = new int[n + 1][n + 1]; //prefix sum 그리드
        for (int i = 0; i <= n; i++) {
            sum[i][0] = 0;
            sum[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) grid[i][j] = Integer.parseInt(st.nextToken());
        }

        // 누적합 배열 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] + grid[i][j] - sum[i - 1][j - 1];
            }
        }

        // 모든 구간에 대해 합을 찾아 최댓값 갱신
        int max = 0;
        for (int i = 0; i <= n - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                int tmp = sum[i + k][j + k] - sum[i + k][j] - sum[i][j + k] + sum[i][j];
                max = Math.max(max, tmp);
            }
        }

        System.out.println(max);
    }
}