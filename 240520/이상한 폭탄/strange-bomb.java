import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 폭탄의 수
        int K = Integer.parseInt(tokenizer.nextToken()); // 폭탄의 유효거리
        
        HashMap<Integer, Integer> bombs = new HashMap<>(); // 폭탄의 위치
        
        int answer = -1;
        for (int idx = 0; idx < N; idx++) {
            int number = Integer.parseInt(reader.readLine());
            
            if (bombs.containsKey(number) && (idx - bombs.get(number)) <= K) {
                answer = Math.max(answer, number);
            }
            bombs.put(number, idx);
        }

        System.out.println(answer);
    }
}