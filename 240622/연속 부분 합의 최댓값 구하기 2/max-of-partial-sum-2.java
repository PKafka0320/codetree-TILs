import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 정수의 개수
        
        int[] numbers = new int[n]; // [i]: i번째 숫자
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        int ans = -1000;
        int sum = 0;
        for (int idx = 0; idx < n; idx++) {
            sum += numbers[idx];
            
            ans = Math.max(ans, sum);
            if (sum < 0) {
                ans = Math.max(ans, sum);
                sum = 0;
            }
        }
        
        System.out.println(ans);
    }
}