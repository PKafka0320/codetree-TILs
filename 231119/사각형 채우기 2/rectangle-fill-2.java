import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int answer = find(n);

        System.out.println(answer);
    }

    public static int find(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 3;
        }

        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        memo[2] = 3;
        for (int i = 3; i <= n; i++) {
            memo[i] = (memo[i - 1] + memo[i - 2] * 2) % 10_007;
        }

        return memo[n];
    }
}