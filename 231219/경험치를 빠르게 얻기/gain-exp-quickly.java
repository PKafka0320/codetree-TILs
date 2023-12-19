import java.util.*;
import java.io.*;

class Quest {
    int exp, time;
    public Quest(int e, int t) {
        this.exp = e;
        this.time = t;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static Quest[] quests;
    static int maxExp[];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int sum = 0;
        quests = new Quest[n];
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            quests[idx] = new Quest(e, t);
            sum += t;
        }

        maxExp = new int[sum + 1];
        for (int idx = 0; idx < n; idx++) {
            for (int t = sum; t >= 1; t--) {
                if (t < quests[idx].time) continue;
                maxExp[t] = Math.max(maxExp[t], maxExp[t - quests[idx].time] + quests[idx].exp);
            }
        }

        // for (int t = 0; t <= m; t++) {
        //     System.out.println(t + " : " + maxExp[t]);
        // }

        int answer = sum;
        for (int time = 0; time <= sum; time++) {
            if (maxExp[time] >= m) {
                answer = Math.min(answer, time);
            }
        }

        System.out.println(answer);
    }
}