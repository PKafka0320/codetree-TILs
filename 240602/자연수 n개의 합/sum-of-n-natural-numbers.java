import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long target = Long.parseLong(reader.readLine()); // 자연수 합의 최댓값
        
        long left = 1; // 가능한 n의 구간 시작
        long right = 1_414_213_562; // 가능한 n의 구간 끝
        long answer = 0; // n의 최댓값
        
        while (left <= right) {
            long mid = (left + right) / 2; // 중앙값
            
            if (mid * (mid + 1) / 2 <= target) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        
        System.out.println(answer);
    }
}