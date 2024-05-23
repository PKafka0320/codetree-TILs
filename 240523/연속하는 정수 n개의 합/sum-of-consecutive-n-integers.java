import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 수열의 크기
        int m = Integer.parseInt(tokenizer.nextToken()); // 구하려는 원소들의 합
        
        int[] numbers = new int[n]; // 수열
        
        // 수열 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        int endIdx = 0; // 구간의 끝 위치
        long sum = 0; // 구간의 합
        int count = 0; // 조건을 만족하는 구간의 개수
        for (int startIdx = 0; startIdx < n; startIdx++) {
            // 구간의 합이 m 이상이 될 때까지 구간의 끝을 이동
            while (endIdx < n && sum < m) {
                sum += numbers[endIdx];
                endIdx++;
            }
            
            // 구간의 합이 m과 동일한 경우에만 계산
            if (sum == m) {
                count++;
            }
            
            // 구간의 시작 위치를 이동하기 전 해당 위치의 값 빼기
            sum -= numbers[startIdx];
        }
        
        System.out.println(count);
    }
}