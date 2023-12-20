import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] numbers;
    static int[] maxSum;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        maxSum = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        int answer = -1001;
        maxSum[0] = numbers[0];
        for (int idx = 1; idx < n; idx++) {
            maxSum[idx] = Math.max(maxSum[idx - 1] + numbers[idx], numbers[idx]);
            answer = Math.max(answer, maxSum[idx]);
        }

        System.out.println(answer);
    }
}