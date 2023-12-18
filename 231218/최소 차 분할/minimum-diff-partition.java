import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] numbers;
    static boolean[] dp;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
            sum += numbers[idx];
        }
        dp = new boolean[sum + 1];

        dp[0] = true;
        for (int idx = 0; idx < n; idx++) {
            for (int number = sum; number >= 0; number--) {
                if (number >= numbers[idx] && dp[number - numbers[idx]]) {
                    dp[number] = true;
                }
            }
        }

        int answer = sum;
        for (int number = 1; number <= sum; number++) {
            if (dp[number]) {
                answer = Math.min(answer, Math.abs(number - (sum - number)));
            }
        }

        System.out.println(answer);
    }
}