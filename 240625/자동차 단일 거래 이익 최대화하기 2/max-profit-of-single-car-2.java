import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 정보의 개수
        
        int[] prices = new int[n];
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            prices[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        int min = prices[0];
        int answer = 0;
        
        for (int idx = 1; idx < n; idx++) {
            if (prices[idx] >= min) {
                answer = Math.max(answer, prices[idx] - min);
            }
            else {
                min = prices[idx];
            }
        }
        
        System.out.println(answer);
    }
}