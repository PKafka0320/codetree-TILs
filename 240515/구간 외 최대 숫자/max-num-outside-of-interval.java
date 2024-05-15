import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 숫자의 개수
        int q = Integer.parseInt(tokenizer.nextToken()); // 질의의 개수

        int[] numbers = new int[n]; // 숫자 배열
        int[] L = new int[n]; // L[i] : 0번부터 i번 원소 중 최댓값
        int[] R = new int[n]; // R[i] : i번부터 n-1번 원소 중 최댓값
        
        // 숫자 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }

        // L 배열 생성
        L[0] = numbers[0];
        for(int idx = 1; idx < n; idx++) {
            L[idx] = Math.max(L[idx - 1], numbers[idx]);
        }

        // L 배열 생성
        R[n - 1] = numbers[n - 1];
        for(int idx = n - 2; idx >= 0; idx--) {
            R[idx] = Math.max(R[idx + 1], numbers[idx]);
        }
        
        // 구간 [idxFrom, idxTo] 외 범위에서의 최댓값 계산
        // L[idxFrom - 1], R[idxTo + 1] 중 더 큰 값
        StringBuilder answer = new StringBuilder();
        while (q-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            int idxFrom = Integer.parseInt(tokenizer.nextToken()) - 1;
            int idxTo = Integer.parseInt(tokenizer.nextToken()) - 1;
            
            answer.append(Math.max(L[idxFrom - 1], R[idxTo + 1])).append("\n");
        }
        System.out.println(answer);
    }
}