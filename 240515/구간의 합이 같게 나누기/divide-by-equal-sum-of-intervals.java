import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 정수의 개수
        
        int[] numbers = new int[n]; // 정수 배열
        long[] L = new long[n]; // L[i] : 0번부터 i번까지 합이 targetSum인 구간을 정확히 2개 만들 수 있는 서로 다른 가지수
        long[] R = new long[n]; // R[i] : i번부터 n - 1번까지 합이 targetSum인 구간을 정확히 2개 만들 수 있는 서로 다른 가지수
        
        // 입력 및 누적합 계산
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        long totalSum = 0;
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] =  Integer.parseInt(tokenizer.nextToken());
            totalSum += numbers[idx];
        }

        // 총 합이 4로 나누어 떨어지지 않는 경우 오답 처리
        if(totalSum % 4 != 0) {
            System.out.print(0);
            System.exit(0);
        }

        // 각 구간 내 합이 되어야 할 값 계산
        long targetSum = totalSum / 4;

        // L 배열 생성
        L[0] = 0;
        long sum = numbers[0]; // 지금까지의 합
        long cnt = (sum == targetSum) ? 1 : 0; // 합이 정확히 targetSum이 되었던 횟수
        for(int i = 1; i < n; i++) {
            sum += numbers[i];
            
            // 합이 2 * targetSum이 되면
            // 정확히 2개의 구간을 만들 수 있는 가능성이 있으므로
            // cnt값 기록
            if(sum == 2 * targetSum) {
                L[i] = cnt;
            }
            
            // 합이 targetSum인 경우
            // cnt 값을 갱신
            if(sum == targetSum) {
                cnt++;
            }
        }

        // R 배열 생성
        R[n - 1] = 0;
        sum = numbers[n - 1]; // 지금까지의 합
        cnt = (sum == targetSum) ? 1 : 0; // 합이 정확히 targetSum이 되었던 횟수
        for(int i = n - 2; i >= 0; i--) {
            sum += numbers[i];

            // 합이 2 * targetSum이 되면
            // 정확히 2개의 구간을 만들 수 있는 가능성이 있으므로
            // cnt값 기록
            if(sum == 2 * targetSum)
                R[i] = cnt;
            
            // 합이 targetSum인 경우
            // cnt 값 갱신
            if(sum == targetSum)
                cnt++;
        }
        
        // 각 위치 idx에 대해
        // [0, idx] 까지는 합이 targetSum인 구간을 정확히 2개를 만들고
        // [idx + 1, n] 까지도 합이 targetSum인 구간을 정확히 2개를 만든다고 했을 때 
        // 가능한 가지수 계산
        long answer = 0;
        for(int idx = 1; idx < n - 1; idx++)
            answer += (long) L[idx] * R[idx + 1];
        System.out.print(answer);
    }
}