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
    static int minTime[];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        quests = new Quest[n];
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            quests[idx] = new Quest(e, t);
        }

        minTime = new int[m + 1];
        Arrays.fill(minTime, -1);
        minTime[0] = 0;
        for (int idx = 0; idx < n; idx++) {
            for (int ex = m; ex >= 1; ex--) {
                if (ex < quests[idx].exp) {
                    if (minTime[ex] == -1) {
                        minTime[ex] = quests[idx].time;
                    }
                    else {
                        minTime[ex] = Math.min(minTime[ex], quests[idx].time);
                    }
                }
                else {
                    if (minTime[ex] == -1) {
                        if (minTime[ex - quests[idx].exp] == -1) continue;
                        minTime[ex] = minTime[ex - quests[idx].exp] + quests[idx].time;
                    }
                    else {
                        minTime[ex] = Math.min(minTime[ex], minTime[ex - quests[idx].exp] + quests[idx].time);
                    }
                }
            }
        }

        // for (int ex = 0; ex <= m; ex++) {
        //     System.out.println(ex + " : " + minTime[ex]);
        // }

        System.out.println(minTime[m]);
    }
}