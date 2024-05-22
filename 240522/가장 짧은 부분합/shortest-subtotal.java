import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 원소의 개수
        int s = Integer.parseInt(tokenizer.nextToken()); // 구간의 최소 합
        
        int[] numbers = new int[n]; // 원소
        
        // 원소 배열 생성
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        int sum = 0; // 구간의 합
        int endIdx = 0; // 구간의 끝 위치
        int answer = n; // 구간을 잡았을 때, 합이 s 이상이 되는 가장 짧은 구간의 길이
        for (int startIdx = 0; startIdx < n; startIdx++) {
            // 합이 s가 넘을때까지 구간의 끝을 이동
            while (endIdx + 1 < n && sum + numbers[endIdx + 1] < s) {
                sum += numbers[endIdx];
                endIdx++;
            }
            
            // 조건에 만족하는 경우 정답 갱신
            if (sum >= s) {
                answer = Math.min(answer, endIdx - startIdx);
            }
            // 구간의 시작을 이동하기 전 시작 위치의 값을 빼기
            sum -= numbers[startIdx];
        }
        
        // 만족하는 구간이 없는 경우
        if (answer == n && sum < s) {
            answer = -1;
        }
        
        System.out.println(answer);
    }
}