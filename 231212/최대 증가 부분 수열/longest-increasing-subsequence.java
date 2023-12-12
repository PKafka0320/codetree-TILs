import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[] numbers;
    static int[] maxLength;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        maxLength = new int[N];

        String s = br.readLine();
        st = new StringTokenizer(s);
        for (int idx = 0; idx < N; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        for (int currentIdx = 0; currentIdx < N; currentIdx++) {
            if (maxLength[currentIdx] == 0) maxLength[currentIdx]++;
            for (int nextIdx = currentIdx + 1; nextIdx < N; nextIdx++) {
                if (numbers[nextIdx] > numbers[currentIdx]) {
                    if (maxLength[nextIdx] < maxLength[currentIdx] + 1) {
                        maxLength[nextIdx] = maxLength[currentIdx] + 1;
                    }
                }
            }
            if (answer < maxLength[currentIdx]) answer = maxLength[currentIdx];
        }

        System.out.println(answer);
    }
}