import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] count;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        count = new int[n + 1];
        int[] numbers = {1, 2, 5};

        // initialize
        count[0] = 1;

        for (int number = 1; number <= n; number++) {
            for (int idx = 0; idx <= 2; idx++) {
                if (number >= numbers[idx]) {
                    count[number] = (count[number] + count[number - numbers[idx]]) % 10_007;
                }
            }
        }

        System.out.println(count[n]);
    }
}