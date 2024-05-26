import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n  = Integer.parseInt(tokenizer.nextToken()); // 수열의 크기
        int k  = Integer.parseInt(tokenizer.nextToken()); // 같은 원소의 등장 횟수
        
        HashMap<Integer, Integer> counts = new HashMap<>(); // <K, V>: 수열 내 K 원소의 개수 V
        int[] numbers = new int[n]; // [i]: 수열의 i번째 수
        
        // 수열 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        int endIdx = 0; // 연속 부분 수열의 끝 위치
        int max = 0; // 연속 부분 수열의 최대 길이

        // 시작 지점을 이동할 때마다 조건을 만족하지 않을 때까지 끝 위치를 이동
        for (int startIdx = 0; startIdx < n; startIdx++) {
            // 같은 수가 k개 이상이 될때까지 부분 수열의 끝 위치 이동
            while (endIdx < n && counts.getOrDefault(numbers[endIdx], 0) < k) {
                counts.put(numbers[endIdx], counts.getOrDefault(numbers[endIdx], 0) + 1);
                endIdx++;
            }
            
            max = Math.max(max, endIdx - startIdx);
            
            // 부분 수열의 시작 위치를 이동하기 전, 수열 내 해당 원소의 숫자 차감
            counts.put(numbers[startIdx], counts.getOrDefault(numbers[startIdx], 0) - 1);
        }
        
        System.out.println(max);
    }
}