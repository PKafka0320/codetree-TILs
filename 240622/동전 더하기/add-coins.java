import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 동전의 종류
        int k = Integer.parseInt(tokenizer.nextToken()); // 완성시킬 금액
        
        int[] coins = new int[n]; // [i]: i번째 동전의 금액
        
        for (int idx = 0; idx < n; idx++) {
            coins[idx] = Integer.parseInt(reader.readLine());
        }
        
        int answer = 0; // 금액 k를 만드는데 필요한 동전의 최소 개수
        int currentIdx = n - 1; // 현재 확인하고 있는 동전의 번호
        while (k > 0) {
            answer += k / coins[currentIdx];
            k %= coins[currentIdx];
            currentIdx--;
        }
        
        System.out.println(answer);
    }
}