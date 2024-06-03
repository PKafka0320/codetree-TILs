import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 격자의 크기
        int k = Integer.parseInt(reader.readLine()); // 숫자의 번호
        
        int left = 1; // 구간의 시작
        int right = n * n; // 구간의 끝
        
        while (left <= right) {
            int mid = (left + right) / 2; // 중앙값
            
            // mid보다 작거나 같은 수의 개수 계산
            int count = 0;
            for (int idx = 1; idx <= n; idx++) {
                count += Math.min(mid / idx, n);
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