import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(reader.readLine()); // 격자의 크기
        long k = Long.parseLong(reader.readLine()); // 숫자의 번호
        
        long left = 1; // 구간의 시작
        long right = n * n; // 구간의 끝
        
        while (left <= right) {
            long mid = (left + right) / 2; // 중앙값
            
            // 각 행에서 mid보다 작거나 같은 수의 개수의 합 계산
            long count = 0;
            for (int idx = 1; idx <= n; idx++) {
                count += Math.min(mid / idx, n); // 개수는 n개를 넘을 수 없음
            }
            
            if (count < k) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        
        System.out.println(left);
    }
}