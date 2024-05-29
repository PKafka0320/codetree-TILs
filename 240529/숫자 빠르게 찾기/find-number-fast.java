import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 숫자의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 추가적으로 주어지는 숫자의 개수
        
        int[] numbers = new int[n + 1]; // [i]: i번째 숫자
        
        // 숫자 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 1; idx <= n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        StringBuilder answer = new StringBuilder();
        
        while (m-- > 0) {
            int num = Integer.parseInt(reader.readLine()); // 찾을 숫자
            int idx = -1; // 찾을 숫자의 인덱스
            int left = 1, right = n; // 구간의 시작과 끝 인덱스
            
            while (left <= right) {
                int mid = (left + right) / 2; // 중앙에 위치한 숫자의 인덱스
                
                // 탐색에 성공하면 인덱스 갱신
                if (numbers[mid] == num) {
                    idx = mid;
                    break;
                }
                
                // 찾으려는 숫자가 중앙값보다 작다면 왼쪽 구간에서 탐색 
                if (numbers[mid] > num) {
                    right = mid - 1;
                }
                // 찾으려는 숫자가 중앙값보다 크다면 오른쪽 구간에서 탐색
                else {
                    left = mid + 1;
                }
            }
            
            answer.append(idx).append("\n");
        }
        
        System.out.println(answer);
    }
}