import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int M;
    static int[] coins;
    static int[] minCount;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        coins = new int[N];
        minCount = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            coins[idx] = Integer.parseInt(st.nextToken());
            // minCount[coins[idx]] = 1;
        }

        for (int number = 1; number <= M; number++) {
            for (int coinIdx = 0; coinIdx < N; coinIdx++) {
                if (number > coins[coinIdx]) {
                    if (minCount[number - coins[coinIdx]] == 0) continue;
                    if (minCount[number] == 0) {
                        minCount[number] = minCount[number - coins[coinIdx]] + 1;
                    } else {
                        minCount[number] = Math.min(minCount[number], minCount[number - coins[coinIdx]] + 1);
                    }
                } else if (number == coins[coinIdx]) {
                    minCount[number] = 1;
                }
            }
        }

        // for (int number = 1; number < M + 1; number++) {
        //     System.out.println(number + ": " + minCount[number]);
        // }

        if (minCount[M] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(minCount[M]);
        }
    }
}