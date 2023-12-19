import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] numbers;
    static boolean[] canMake;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        numbers = new int[n];

        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
            sum += numbers[idx];
        }
        if (sum % 2 != 0) {
            System.out.println("No");
            return;
        }

        canMake = new boolean[sum + 1];
        canMake[0] = true;
        for (int idx = 0; idx < n; idx++) {
            for (int number = sum; number >= 1; number--) {
                if (number < numbers[idx]) continue;
                if (canMake[number - numbers[idx]]) canMake[number] = true;
            }
        }

        if (canMake[sum / 2]) System.out.println("Yes");
        else System.out.println("No");
    }
}