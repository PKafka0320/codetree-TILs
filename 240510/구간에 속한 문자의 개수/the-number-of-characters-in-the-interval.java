import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][][] grid;
    static int[][][] prefixSum;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        k = Integer.parseInt(token.nextToken());

        prefixSum = new int[n + 1][m + 1][3];
        // 각 위치마다 있는 알파벳의 개수 (a, b, c)
        grid = new int[n + 1][m + 1][3];

        for (int row = 1; row <= n; row++) {
            String line = reader.readLine();
            for (int col = 1; col <= m; col++) {
                grid[row][col][line.charAt(col - 1) - 'a'] += 1; // 해당하는 알파벳의 수 + 1
            }
        }

        // prefixSum 계산
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                for (int ch = 0; ch < 3; ch++) {
                    prefixSum[row][col][ch] = grid[row][col][ch] + prefixSum[row - 1][col][ch] 
                        + prefixSum[row][col - 1][ch] - prefixSum[row - 1][col - 1][ch];
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int query = 0; query < k; query++) {
            token = new StringTokenizer(reader.readLine());
            int r1 = Integer.parseInt(token.nextToken());
            int c1 = Integer.parseInt(token.nextToken());
            int r2 = Integer.parseInt(token.nextToken());
            int c2 = Integer.parseInt(token.nextToken());

            for (int ch = 0; ch < 3; ch++) {
                int sum = prefixSum[r2][c2][ch] - prefixSum[r2][c1 - 1] [ch]
                    - prefixSum[r1 - 1][c2][ch] + prefixSum[r1 - 1][c1 - 1][ch];
                answer.append(sum).append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}