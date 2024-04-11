import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int dp[][];
    static final int mod = 1000000007;

    public static void main(String[] args) {
        n = sc.nextInt();

        //dp[i][j] :: i자릿수 까지 봤을 때, 마지막 숫자가 j인 가짓수
        dp = new int[n + 1][10];
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 9; j++) {
                //숫자가 감소하는 경우
                if (j > 0) {
                    dp[i + 1][j - 1] = (dp[i + 1][j - 1] + dp[i][j]) % mod;
                }
                //숫자가 증가하는 경우
                if (j < 9) {
                    dp[i + 1][j + 1] = (dp[i + 1][j + 1] + dp[i][j]) % mod;
                }
            }
        }

        //결과 계산
        int ans = 0;
        for (int i = 0; i <= 9; i++) {
            ans = (ans + dp[n][i]) % mod;
        }

        System.out.println(ans);
    }
}