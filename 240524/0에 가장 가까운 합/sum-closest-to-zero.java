import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 정수의 개수
        
        int[] numbers = new int[n]; // [i]: i번째 정수
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        // 숫자들을 정렬하면 숫자 i를 1부터 n까지 증가시키며 numbers[i] + numbers[j] <= 0 을 만족하는 j 중 최댓값을 구하도록 two pointer를 진행할 수 있다. 
        Arrays.sort(numbers);
        
        int secondIdx = n - 1; // 두 번째 정수의 위치
        int min = Integer.MAX_VALUE; // 두 수의 합의 최솟값 (절댓값)
        
        // 두 수의 합이 0에 가까워지는 동안 두 번째 정수의 위치 이동
        for (int firstIdx = 0; firstIdx < n; firstIdx++) { // 첫 번째 정수의 위치
            // 첫 번째 수의 위치기 변경되었을때 검사
            if (firstIdx < secondIdx) {
                min = Math.min(min, Math.abs(numbers[firstIdx] + numbers[secondIdx]));
            }
            
            // 두 수의 합이 0 이하가 될 대까지 두 번째 정수의 위치 이동
            while (secondIdx - 1 > firstIdx && numbers[firstIdx] + numbers[secondIdx] > 0) {
                secondIdx--;
                min = Math.min(min, Math.abs(numbers[firstIdx] + numbers[secondIdx]));
            }
        }
        
        System.out.print(min);
    }
}