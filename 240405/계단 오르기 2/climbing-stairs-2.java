import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] coin;
    static int[][] maxCoin;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        coin = new int[n + 1];
        maxCoin = new int[n + 1][4];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
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