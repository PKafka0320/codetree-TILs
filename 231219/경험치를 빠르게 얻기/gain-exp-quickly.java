import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[] exp, time;
    static int maxExp[][];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int sum = 0;
        exp = new int[n + 1];
        time = new int[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            st = new StringTokenizer(br.readLine());
            exp[idx] = Integer.parseInt(st.nextToken());
            time[idx] = Integer.parseInt(st.nextToken());
            sum += time[idx];
        }

        // dp[i][j] : i번째 퀘스트까지 고려헀을 때
        //            지금까지 퀘스트를 진행하는 데 걸리는 시간의 총 합이 j일 때
        //            얻을 수 있었던 최대 경험치
        maxExp = new int[n + 1][sum + 1];
        for(int idx = 0; idx <= n; idx++)
            for(int time = 0; time <= sum; time++)
                maxExp[idx][time] = -1;
        maxExp[0][0] = 0;

        for (int idx = 1; idx <= n; idx++) {
            for (int t = 0; t <= sum; t++) {
                // Case 1. 현재 퀘스트를 진행하여
                //         수행시간의 총 합이 j가 되기 위해서는
                //         i - 1번째 퀘스트 까지 수행시간이 j - runtime[i]가 되어야 합니다.
                if (t >= time[idx]) {
                    maxExp[idx][t] = Math.max(maxExp[idx][t], maxExp[idx - 1][t - time[idx]] + exp[idx]);
                }
                
                // Case 2. 현재 퀘스트를 진행하지 않고
                //         수행시간의 총 합이 j가 되기 위해서는
                //         i - 1번째 퀘스트 까지 수행시간이 j가 되어야 합니다.
                maxExp[idx][t] = Math.max(maxExp[idx][t], maxExp[idx - 1][t]);
            }
        }

        // for (int t = 0; t <= sum; t++) {
        //     System.out.println(t + " : " + maxExp[t]);
        // }

        int answer = sum + 1;
        for (int time = 0; time <= sum; time++) {
            if (maxExp[n][time] >= m) {
                answer = Math.min(answer, time);
            }
        }

        if (answer == sum + 1) {
            answer = -1;
        }

        System.out.println(answer);
    }
}