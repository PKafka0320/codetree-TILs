import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine()); // N번째 숫자
        
        long left = 1; // 구간의 시작
        long right = Integer.MAX_VALUE; // 구간의 끝
        long min = right; // 숫자의 개수가 N 이상인 경우 중 최솟값
        
        while (left <= right) {
            long mid = (left + right) / 2; // 중앙값

            // 서로 다른 수의 개수 계산
            long mooCnt = mid / 3 + mid / 5 - mid / 15; // 3의 배수와 5의 배수의 개수
            long count = mid - mooCnt; // 조건에 만족하는 서로 다른 수의 개수
            
            if (count >= N) {
                right = mid - 1;
                min = Math.min(min, mid);
            }
            else {
                left = mid + 1;
            }
        }
        
        System.out.println(min);
    }
}