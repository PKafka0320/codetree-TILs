import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int OFFSET;
    static int INT_MIN = Integer.MIN_VALUE;
    static int[] numbers;
    static int n;
    static int sum = 0;
    // dp[i][j] : i번째 수까지 고려헀을 떄
    //            그룹 A 합 - 그룹 B 합을 j라 했을 때
    //            만들 수 있는 최대 그룹 A의 합
    static int[][] dp;
    
    public static void main(String[] args)  throws Exception {
        n = Integer.parseInt(br.readLine());
        numbers = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int idx = 1; idx <= n; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
            sum += numbers[idx];
        }
        OFFSET = sum;

        // initialize
        dp = new int[n + 1][sum + 1 + OFFSET];
        for (int idx = 0; idx < n; idx++) {
            for (int number = -sum; number <= sum; number++) {
                dp[idx][number + OFFSET] = -INT_MIN;
            }
        }
        dp[0][0 + OFFSET] = 0;

        for (int idx = 1; idx <= n; idx++) {
            for (int number = -sum; number <= sum; number++) {
                // Case 1. 그룹 A에 i번째 원소를 추가하여 그룹A-그룹B가 j가 된 경우
                //         dp[i - 1][j - arr[i]] + arr[i] -> dp[i][j]
                update (idx , number, idx - 1, number - numbers[idx], numbers[idx]);

                // Case 2. 그룹 B에 i번째 원소를 추가하여 그룹A-그룹B가 j가 된 경우
                //         dp[i - 1][j + arr[i]] -> dp[i][j]
                update (idx , number, idx - 1, number + numbers[idx], 0);

                // Case 3. 그룹 C에 i번째 원소를 추가하여 그룹A-그룹B가 j가 된 경우
                //         dp[i - 1][j] -> dp[i][j]
                update (idx , number, idx - 1, number, 0);
            }
        }

        // for (int idx = 0; idx <= n; idx++) {
        //     for (int number = -sum; number <= sum; number++) {
        //         if (dp[idx][number + OFFSET] == INT_MIN) System.out.print("- ");
        //         else System.out.print(dp[idx][number + OFFSET] + " ");
        //     }
        //     System.out.println();
        // }

        System.out.println(dp[n][0 + OFFSET]);
    }

    public static void update (int idx, int number, int prevIdx, int prevNum, int val) {
        if (prevNum < -sum || prevNum > sum || dp[prevIdx][prevNum + OFFSET] == INT_MIN) return;
        dp[idx][number + OFFSET] = Math.max(dp[idx][number + OFFSET], dp[prevIdx][prevNum + OFFSET] + val);
    }
}