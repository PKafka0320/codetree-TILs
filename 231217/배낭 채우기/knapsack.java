import java.util.*;
import java.io.*;

class Jewel {
    int weight, value;
    public Jewel(int w, int v) {
        this.weight = w;
        this.value = v;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Jewel> jewels = new ArrayList<>();
    static int[] maxValue;
    static int N, M;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maxValue = new int[M + 1];

        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(w, v));
        }

        for (int idx = 0; idx < N; idx++) {
            Jewel jewel = jewels.get(idx);

            for (int weight = M; weight >= 1; weight--) {
                if (weight < jewel.weight) continue;
                if (maxValue[weight - jewel.weight] == 0 && weight != jewel.weight) continue;
                maxValue[weight] = Math.max(maxValue[weight], maxValue[weight - jewel.weight] + jewel.value);
                if (answer < maxValue[weight]) answer = maxValue[weight];
            }
        }

        // for (int weight = 0; weight <= M; weight++) {
        //     System.out.println(weight + " : " + maxValue[weight]);
        // }

        System.out.println(answer);
    }
}