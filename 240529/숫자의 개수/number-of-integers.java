import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 숫자의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 추가적으로 주어지는 숫자의 개수
        
        int[] numbers = new int[n]; // [i]: i번째 숫자
        
        // 숫자 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        StringBuilder answer = new StringBuilder();
        
        while (m-- > 0) {
            int target = Integer.parseInt(reader.readLine()); // 찾을 숫자
            
            int lowerIdx = n; // lower bound 인덱스
            int left = 0, right = n - 1; // 구간의 시작과 끝 인덱스
            
            // lower bound
            while (left <= right) {
                int mid = (left + right) / 2; // 중앙에 위치한 숫자의 인덱스
                
                // 찾으려는 숫자가 중앙값보다 작거나 같다면 왼쪽 구간에 만족하는 숫자가 있을 가능성 있음
                if (numbers[mid] >= target) {
                    right = mid - 1;
                    lowerIdx = Math.min(lowerIdx, mid);
                }
                // 찾으려는 숫자가 중앙값보다 크다면 오른쪽 구간에서 탐색
                else {
                    left = mid + 1;
                }
            }
            
            int upperIdx = n; // upper bound 인덱스
            left = 0;
            right = n - 1;
            
            // upper bound
            while (left <= right) {
                int mid = (left + right) / 2; // 중앙에 위치한 숫자의 인덱스
                
                // 찾으려는 숫자가 중앙값보다 작다면 왼쪽 구간에 만족하는 숫자가 있을 가능성 있음
                if (numbers[mid] > target) {
                    right = mid - 1;
                    upperIdx = Math.min(upperIdx, mid);
                }
                // 찾으려는 숫자가 중앙값보다 크다면 오른쪽 구간에서 탐색
                else {
                    left = mid + 1;
                }
            }
            
            answer.append(upperIdx - lowerIdx).append("\n");
        }
        
        System.out.println(answer);
    }
}