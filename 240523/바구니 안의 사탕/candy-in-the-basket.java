import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 바구니의 개수
        int K = Integer.parseInt(tokenizer.nextToken()) * 2; // 구간의 길이
        
        HashMap<Integer, Long> candies = new HashMap<>(); // <K, V>: K위치의 사탕의 개수
        int maxPosition = 0; // 최대 위치
        int minPosition = 1_000_000; // 최소 위치
        
        // 좌표에 따른 사탕의 개수 입력
        for (int idx = 0; idx < N; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int count = Integer.parseInt(tokenizer.nextToken()); // 사탕의 개수
            int position = Integer.parseInt(tokenizer.nextToken()); // 바구니의 좌표
            
            candies.put(position, candies.getOrDefault(position, 0L) + count);
            maxPosition = Math.max(maxPosition, position);
            minPosition = Math.min(minPosition, position);
        }
        
        int endPosition = 0; // 구간의 끝 위치
        long sum = 0; // 구간 내 사탕의 합
        long max = 0; // 사탕의 합의 최댓값
        
        // 구간의 시작 지점부터 구간의 길이 내의 사탕의 합 계산
        for (int startPosition = minPosition; startPosition <= maxPosition; startPosition++) {
            while (endPosition <= maxPosition && endPosition - startPosition <= K) {
                sum += candies.getOrDefault(endPosition, 0L);
                endPosition++;
            }
            
            max = Math.max(max, sum);
            if (endPosition > maxPosition) break;
            
            sum -= candies.getOrDefault(startPosition, 0L);
        }
        
        System.out.println(max);
    }
}