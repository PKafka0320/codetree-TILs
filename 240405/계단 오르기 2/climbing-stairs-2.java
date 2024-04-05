import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int[] coin;
    static int[][] maxCoin;

    public static void main(String[] args) {
        n = sc.nextInt();
        coin = new int[n + 1];
        maxCoin = new int[n + 1][4];
        for (int i = 1; i <= n; i++) {
            coin[i] = sc.nextInt();
        }

        // init
        maxCoin[1][1] = coin[1];
        maxCoin[2][0] = coin[2];
        maxCoin[2][2] = coin[1] + coin[2];

        for (int i = 3; i <= n; i++) {
            for (int j = 0; j <= 3; j++) {
                if (maxCoin[i - 2][j] != 0) {
                    maxCoin[i][j] = Math.max(maxCoin[i][j], maxCoin[i - 2][j] + coin[i]);
                }
                if (j > 0 && maxCoin[i - 1][j - 1] != 0) {
                    maxCoin[i][j] = Math.max(maxCoin[i][j], maxCoin[i - 1][j - 1] + coin[i]);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= 3; i++) {
            ans = Math.max(ans, maxCoin[n][i]);
        }

        System.out.println(ans);
    }
}