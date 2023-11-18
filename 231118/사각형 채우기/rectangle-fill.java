import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // int result = tabul(n);
        int[] memo = new int[n + 1];
        int result = memoi(memo, n);

        System.out.println(result);
    }

    // tabulation
    public static int tabul(int n) {
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;
        
        for (int idx = 3; idx <= n; idx++) {
            memo[idx] = (memo[idx - 1] + memo[idx - 2]) % 10_007;
        }

        return memo[n];
    }

    // memoization
    public static int memoi(int[] memo, int n) {
        if (memo[n] != 0) {
            return memo[n];
        }

        if (n <= 2) {
            memo[n] = n;
        } else{
            memo[n] = (memoi(memo, n - 1) + memoi(memo, n - 2)) % 10_007;
        }

        return memo[n];
    }
}