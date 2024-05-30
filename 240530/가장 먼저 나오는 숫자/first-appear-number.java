import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 숫자의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 질의의 개수
        
        int[] numbers = new int[n]; // [i]: i번째 숫자
        tokenizer = new StringTokenizer(reader.readLine());
        
        // 숫자 입력
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }

        StringBuilder answer = new StringBuilder();
        tokenizer = new StringTokenizer(reader.readLine());
        
        while (m-- > 0) {
            int target = Integer.parseInt(tokenizer.nextToken()); // 목표 숫자
            int left = 0; // 구간의 시작
            int right = n - 1; // 구간의 끝
            int index = -1; // 목표 숫자의 위치
            
            while (left <= right) {
                int mid = (left + right) / 2;
                
                if (numbers[mid] == target) {
                    index = mid + 1;
                }
                
                if (numbers[mid] < target) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            
            answer.append(index).append("\n");
        }
        
        System.out.println(answer);
    }
}