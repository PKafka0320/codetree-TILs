import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] numbers;
    static int[] maxLen;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        maxLen = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(maxLen, 1);

        int answer = 0;
        for (int idx = 1; idx < n; idx++) {
            for (int cmp = 0; cmp < idx; cmp++) {
                if (numbers[idx] < numbers[cmp]) {
                    maxLen[idx] = Math.max(maxLen[idx], maxLen[cmp] + 1);
                }

                answer = Math.max(answer, maxLen[idx]);
            }
        }

        System.out.println(answer);
    }
}