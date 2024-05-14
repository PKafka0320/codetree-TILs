import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static String string;
    static int[] L, R;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine()); // 문자열 길이
        string = reader.readLine(); // 문자열
        L = new int[n]; // L[i] : i번째 위치기준 왼쪽에 있는 C의 개수
        R = new int[n]; // R[i] : i번째 위치기준 오른쪽에 있는 W의 개수

        // L 배열 생성
        for (int idx = 1; idx < n; idx++) {
            L[idx] = L[idx - 1];

            if (string.charAt(idx - 1) == 'C') {
                L[idx]++;
            }
        }

        // R 배열 생성
        for (int idx = n - 2; idx >= 0; idx--) {
            R[idx] = R[idx + 1];

            if (string.charAt(idx + 1) == 'W') {
                R[idx]++;
            }
        }

        // 'O'의 위치마다 왼쪽에 있는 'C'의 개수와 오른쪽에 있는 'W'의 개수의 곱을 누적
        int ans = 0;
        for (int idx = 1; idx < n - 1; idx++) {
            if (string.charAt(idx) != 'O') continue;

            ans += L[idx] * R[idx];
        }

        System.out.println(ans);
    }
}