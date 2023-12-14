import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M;
    static int[] coins;
    static int[] maxCount;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        coins = new int[N];
        maxCount = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            coins[idx] = Integer.parseInt(st.nextToken());
        }

        for (int number = 1; number <= M; number++) {
            for (int idx = 0; idx < N; idx++) {
                if (number < coins[idx]) continue;
                maxCount[number] = Math.max(maxCount[number], maxCount[number - coins[idx]] + 1);
            }
        }

        System.out.println(maxCount[M]);
    }
}