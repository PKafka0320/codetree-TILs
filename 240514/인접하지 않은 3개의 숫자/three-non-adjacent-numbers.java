import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] numbers, L, R;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(reader.readLine()); // 숫자 개수
        numbers = new int[n]; // 숫자 배열
        L = new int[n]; // L[i] : i번째 숫자의 왼쪽 숫자 중에서 가장 큰 수
        R = new int[n]; // R[i] : i번째 숫자의 오른쪽 숫자 중에서 가장 큰 수

        // 숫자 입력
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(token.nextToken());
        }

        // L 배열 생성
        int max = numbers[0];
        for (int idx = 1; idx < n; idx++) {
            L[idx] = max;
            max = Math.max(max, numbers[idx]);
        }

        // R 배열 생성
        max = numbers[n - 1];
        for (int idx = n - 2; idx >= 0; idx--) {
            R[idx] = max;
            max = Math.max(max, numbers[idx]);
        }

        // 선택한 3개의 숫자 중 두 번째 숫자의 위치를 선택하고 계산
        int ans = 0;
        for (int idx = 2; idx < n - 2; idx++) {
            ans = Math.max(ans, numbers[idx] + L[idx - 1] + R[idx + 1]);
        }
        System.out.println(ans);
    }
}