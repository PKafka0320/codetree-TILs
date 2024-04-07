import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int k;
    static int[] num;
    static int[][] max;

    public static void main(String[] args) {
        //input
        n = sc.nextInt();
        k = sc.nextInt();
        num = new int[n + 1];
        max = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            num[i] = sc.nextInt();
        }

        //init
        for (int i = 0; i <= n; i++) {
            Arrays.fill(max[i], Integer.MIN_VALUE);
        }
        max[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                // if (max[i][j] == -1) continue;

                if (num[i + 1] >= 0) {
                    max[i + 1][j] = Math.max(Math.max(max[i + 1][j], max[i][j] + num[i + 1]), num[i + 1]);
                }
                else {
                    if (j == k) continue;
                    max[i + 1][j + 1] = Math.max(max[i + 1][j + 1], max[i][j] + num[i + 1]);
                }
            }
        }

        // for (int i = 0; i <= n; i++) {
        //     for (int j = 0; j <= k; j++) {
        //         System.out.print(max[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                ans = Math.max(ans, max[i][j]);
            }
        }

        System.out.println(ans);
    }
}