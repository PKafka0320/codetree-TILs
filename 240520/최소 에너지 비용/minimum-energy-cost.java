import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 장소의 개수
        
        int[] energies = new int[n - 1]; // [i]: i번째 위치에서 i+1번째로 이동할때 드는 에너지
        int[] costs = new int[n]; // [i]: i번째 위치에서 에너지 1을 채우는데 드는 비용
        int[] L = new int[n - 1]; // [i]: 0부터 i까지 충전 비용이 가장 작은 장소의 위치
        
        // 입력
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n - 1; idx++) {
            energies[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            costs[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        // L 배열 생성
        L[0] = costs[0];
        for (int idx = 1; idx < n - 1; idx++) {
            L[idx] = Math.min(L[idx - 1], costs[idx]);
        }
        
        // 각 위치마다 가장 작은 충전 비용과 다음 장소로 가는데 드든 에너지의 곱을 합산
        long answer = 0;
        for (int idx = 0; idx < n - 1; idx++) {
            answer += L[idx] * energies[idx];
        }
        System.out.println(answer);
    }
}