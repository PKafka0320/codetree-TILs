import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 정수의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 같은 크기의 정수 개수
        
        int[] numbers = new int[n]; // [i]: i번째 정수
        
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(reader.readLine());
        }
        
        int left = 1; // m개 이상 만들 수 있는 k 값의 구간 시작
        int right = 100_000; // m개 이상 만들 수 있는 k 값의 구간 끝
        int max = 0; // 만들 수 있는 k 값의 최댓값
        
        while (left <= right) {
            int mid = (left + right) / 2; // 중앙값
            int count = 0; // mid를 만들 수 있는 개수
            
            // mid를 만들 수 있는 개수의 합 계산
            for (int idx = 0; idx < n; idx++) {
                count += numbers[idx] / mid;
            }
            
            // 개수가 m개 이상이라면 더 큰 수로 만들 수 있기 때문에 구간을 오른쪽으로 한정
            if (count >= m) {
                max = Math.max(max, mid);
                left = mid + 1;
            }
            // 개수가 m기 미만이라면 더 작은 수로 만들어야 하기 때문에 구간을 왼쪽으로 한정
            else {
                right = mid - 1;
            }
        }
        
        System.out.println(max);
    }
}