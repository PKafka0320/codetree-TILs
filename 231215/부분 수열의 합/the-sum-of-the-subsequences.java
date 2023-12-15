import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static int[] numbers;
    static boolean[] canMake;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        canMake = new boolean[m + 1];

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        canMake[0] = true;
        for (int idx = 0; idx < n; idx++) {
            for (int number = m; number >= 0; number--) {
                if (number < numbers[idx]) continue;
                if (canMake[number - numbers[idx]]) {
                    canMake[number] = true;
                }
            }
        }

        if (canMake[m]) System.out.println("Yes");
        else System.out.println("No");
    }
}