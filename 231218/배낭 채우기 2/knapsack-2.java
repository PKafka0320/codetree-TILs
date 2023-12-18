import java.util.*;
import java.io.*;

class Jewel {
    int weight, value;
    
    public Jewel (int w, int v) {
        this.weight = w;
        this.value = v;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static Jewel[] jewels;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        jewels = new Jewel[N];

        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[idx] = new Jewel(w, v);
        }

        int answer = 0;
        int[] maxValues = new int[M + 1];
        for (int weight = 1; weight <= M; weight++) {
            for (int idx = 0; idx < N; idx++) {
                if (weight < jewels[idx].weight) continue;
                if (maxValues[weight - jewels[idx].weight] == 0 && weight != jewels[idx].weight) continue;
                maxValues[weight] = Math.max(maxValues[weight - jewels[idx].weight] + jewels[idx].value, maxValues[weight]);
            }

            answer = Math.max(answer, maxValues[weight]);
        }

        System.out.println(answer);
    }
}