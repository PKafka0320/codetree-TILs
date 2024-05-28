import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 수열의 길이
        int k = Integer.parseInt(tokenizer.nextToken()); // 수열에 존재해야 하는 1의 개수의 최솟값
        
        int[] numbers = new int[n]; // [i]: i번째 수
        
        // 수열 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        int min = n; // 조건을 만족하는 부분 수열의 최소 길이
        int count = 0; // 구간 내 1의 개수
        
        for (int endIdx = 0, startIdx = 0; startIdx < n; startIdx++) {
            // 1의 개수의 최솟값이 될때까지 구간 늘리기
            while (endIdx < n && count < k) {
                if (numbers[endIdx] == 1) count++;
                endIdx++;
            }
            
            // 최소 길이 갱신
            min = Math.min(min, endIdx - startIdx);
            
            // 구간의 끝까지 늘렸다면 종료
            if (endIdx == n) break;
            
            // 구간의 시작 지점의 숫자가 1일 경우 개수 차감
            if (numbers[startIdx] == 1) count--;
        }
        
        System.out.println(min);
    }
}