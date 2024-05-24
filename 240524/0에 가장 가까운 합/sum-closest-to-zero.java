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
        
        Arrays.sort(numbers);
        
        int secondIdx = n - 1; // 두 번째 정수의 위치
        int min = Integer.MAX_VALUE; // 두 수의 합의 최솟값 (절댓값)
        
        // 두 수의 합이 0에 가까워지는 동안 두 번째 정수의 위치 이동
        for (int firstIdx = 0; firstIdx < n; firstIdx++) {
            while (secondIdx > firstIdx && Math.abs(numbers[firstIdx] + numbers[secondIdx]) < min) {
                min = Math.abs(numbers[firstIdx] + numbers[secondIdx]);
                secondIdx--;
            }
        }
        
        System.out.print(min);
    }
}