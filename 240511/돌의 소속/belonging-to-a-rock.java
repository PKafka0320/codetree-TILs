import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static int[] rocks;
    static int[][] prefixSum;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken()); // 돌의 개수
        Q = Integer.parseInt(token.nextToken()); // 범위의 개수

        rocks = new int[N + 1]; // idx번 돌의 번호
        prefixSum = new int[N + 1][4]; // 1번 돌부터 idx번 돌까지 각 그룹의 누적 개수

        for (int idx = 1; idx <= N; idx++) {
            int num = Integer.parseInt(reader.readLine());
            rocks[idx] = num;
        }

        // 누적 합 계산
        for (int idx = 1; idx <= N; idx++) {
            for (int group = 1; group <= 3; group++) {
                prefixSum[idx][group] += prefixSum[idx - 1][group] + (rocks[idx] == group ? 1 : 0);
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int query = 0; query < Q; query++) {
            token = new StringTokenizer(reader.readLine());
            int idxFrom = Integer.parseInt(token.nextToken());
            int idxTo = Integer.parseInt(token.nextToken());

            for (int group = 1; group <= 3; group++) {
                int count = prefixSum[idxTo][group] - prefixSum[idxFrom - 1][group];
                answer.append(count).append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}