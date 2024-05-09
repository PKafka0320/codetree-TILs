import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(token.nextToken());
        int k = Integer.parseInt(token.nextToken());

        int[] numbers = new int[n + 1];
        int[] prefixSum = new int[n + 1];
        
        token = new StringTokenizer(reader.readLine());
        for (int idx = 1; idx <= n; idx++) {
            numbers[idx] = Integer.parseInt(token.nextToken());
            prefixSum[idx] = prefixSum[idx - 1] + numbers[idx];
        }

        int answer = 0;
        for (int start = 0; start <= n - 1; start++) {
            for (int end = start + 1; end <= n; end++) {
                int rangeSum = prefixSum[end] - prefixSum[start];
                if (rangeSum == k) answer++;
            }
        }

        System.out.println(answer);
    }
}