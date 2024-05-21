import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine()); // 숫자의 개수
        
        int[] numbers = new int[N + 1]; // 숫자
        long[] sum = new long[N + 1]; // [i]: 0번째 부터 i번째 숫자까지 누적합
        
        // 숫자 입력 및 누적합 계산
        for (int idx = 1; idx <= N; idx++) {
            numbers[idx] = Integer.parseInt(reader.readLine());
            sum[idx] = sum[idx - 1] + numbers[idx];
        }
        
        // 최대 길이를 설정하고 누적합을 사용해 7의 배수인지 확인
        int answer = 0;
        for (int max = 1; max <= N; max++) {
            for (int idx = max; idx <= N; idx++) {
                long tmp = (long) sum[idx] - sum[idx - max];
                if (tmp % 7 == 0) {
                    answer = Math.max(answer, max);
                }
                
                if (max == answer) break;
            }
        }
        
        System.out.println(answer);
    }
}