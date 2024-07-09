import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 숫자의 개수
        int[] numbers = new int[n]; // [i]: i번째 숫자
        int count = 0; // 반전 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        // idx 위치의 숫자가 0일 경우 idx + 1의 숫자와 그 양 옆을 뒤집기
        for (int idx = 0; idx < n - 1; idx++) {
            if (numbers[idx] == 0) {
                count++;
                numbers[idx] = 1 - numbers[idx];
                numbers[idx + 1] = 1 - numbers[idx + 1];
                if (idx == n - 2) continue; // 마지막 숫자는 왼쪽만 뒤집기
                numbers[idx + 2] = 1 - numbers[idx + 2];
            }
        }

        // 마지막 숫자가 1이 아닐 경우 불가능
        if (numbers[n - 1] != 1) {
            System.out.println(-1);
            return;
        }
        System.out.println(count);
    }
}