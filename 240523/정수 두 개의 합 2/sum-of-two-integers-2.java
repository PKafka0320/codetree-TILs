import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 정수의 개수
        int k = Integer.parseInt(tokenizer.nextToken()); // 두 수의 합의 최댓값
        
        int[] numbers = new int[n]; // [i]: i번째 정수
        
        // 정수 입력
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(reader.readLine());
        }
        
        Arrays.sort(numbers);
        
        int secondIdx = n - 1; // 두 번째 수의 위치
        int count = 0; // 조건을 만족하는 경우의 개수
        
        // 두 수의 합이 k 이하가 되는 경우 중 두 번째 수의 위치가 최댓값이 되는 경우를 계산 
        for (int firstIdx = 0; firstIdx < n; firstIdx++) {
            // 두 수의 합이 k 이하가 될때까지 두 번째 수의 위치 이동
            while (secondIdx >= 0 && numbers[firstIdx] + numbers[secondIdx] > k) {
                secondIdx--;
            }
            
            // 두 번째 수의 위치가 첫 번째 수의 위치와 같거나 앞으로 오게 되면 종료
            if (secondIdx <= firstIdx) {
                break;
            }
            
            // 두 수의 합이 k 이하라면 그 사이의 값들로 합을 구해도 조건에 부합
            count += secondIdx - firstIdx;
        }
        
        System.out.println(count);
    }
}