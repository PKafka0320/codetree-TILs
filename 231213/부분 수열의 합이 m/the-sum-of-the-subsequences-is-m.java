import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int m;
    static int[] numbers;
    static int[] minLen;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        minLen = new int[m + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        initialize();

        for (int idx = 0; idx < n; idx++) {
            for (int number = m; number >= 0; number--) {
                if (number >= numbers[idx]) {
                    minLen[number] = Math.min(minLen[number], minLen[number - numbers[idx]] + 1);
                }
            }
        }

        int answer = minLen[m];
        if (answer == m + 1) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static void initialize() {
        for (int number = 1; number <= m; number++) {
            minLen[number] = m + 1;
        }
    }
}