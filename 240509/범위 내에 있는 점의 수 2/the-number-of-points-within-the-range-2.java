import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(reader.readLine());
        int totalPos = Integer.parseInt(token.nextToken());
        int totalQuery = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(reader.readLine());
        int[] positions = new int[1_000_001];
        for (int posCount = 0; posCount < totalPos; posCount++) {
            int number = Integer.parseInt(token.nextToken());
            positions[number] = 1;
        }

        int[] prefixSum = new int[1_000_001];
        for (int number = 1; number <= 1_000_000; number++) {
            prefixSum[number] = prefixSum[number - 1] + positions[number];
        }

        StringBuilder answer = new StringBuilder();
        for (int queryCount = 0; queryCount < totalQuery; queryCount++) {
            token = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());

            int count = prefixSum[end] - prefixSum[start];
            if (positions[start] == 1) count++;
            answer.append(count).append("\n");
        }

        System.out.println(answer);
    }
}