import java.util.*;
import java.io.*;

class Job {
    int start, end, pay;

    public Job(int s, int e, int p) {
        this.start = s;
        this.end = e;
        this.pay = p;
    }
}

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static ArrayList<Job> jobs = new ArrayList<>();
    static int[] maxPay;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maxPay = new int[N];

        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            jobs.add(new Job(s, e, p));
        }

        for (int cIdx = 0; cIdx < N; cIdx++) {
            maxPay[cIdx] += jobs.get(cIdx).pay;
            for (int nIdx = cIdx + 1; nIdx < N; nIdx++) {
                if (jobs.get(cIdx).end < jobs.get(nIdx).start) {
                    if (maxPay[nIdx] < maxPay[cIdx]) {
                        maxPay[nIdx] = maxPay[cIdx];
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (answer < maxPay[i]) answer = maxPay[i];
        }

        System.out.println(answer);
    }
}