import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] values;
    static int[] maxValues;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        values = new int[n + 1];
        maxValues = new int[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int length = 1; length <= n; length++) {
            values[length] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int length = 1; length <= n; length++) {
            for (int len = 1; len <= length; len++) {
                maxValues[length] = Math.max(maxValues[length], maxValues[length - len] + values[len]);
            }
        }

        System.out.println(maxValues[n]);
    }
}