import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int[] s;
    static int[] b;
    static int[][] max;

    public static void main(String[] args) {
        n = sc.nextInt();
        s = new int[n + 1];
        b = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            s[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }

        max = new int[12][10];
        for (int i = 0; i < 12; i++) {
            Arrays.fill(max[i], -1);
        }
        max[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int r = 11; r >= 0; r--) { //s
                for (int c = 9; c >= 0; c--) { //b
                    if (r > 0 && max[r - 1][c] != -1) {
                        // if (max[r - 1][c] == -1) continue;
                        max[r][c] = Math.max(max[r][c], max[r - 1][c] + s[i]);
                    }
                    if (c > 0 && max[r][c - 1] != -1) {
                        // if (max[r][c - 1] == -1) continue;
                        max[r][c] = Math.max(max[r][c], max[r][c - 1] + b[i]);
                    }
                }
            }
        }

        // for (int r = 0; r < 12; r++) {
        //     for (int c = 0; c < 10; c++) {
        //         System.out.print(max[r][c] + " ");
        //     }
        //     System.out.println();
        // }

        System.out.println(max[11][9]);
    }
}