import java.io.*;
import java.util.*;

class Candy implements Comparable<Candy> {
    int position, count;

    public Candy(int position, int count) {
        this.position = position;
        this.count = count;
    }

    @Override
    public int compareTo(Candy c) {
        return this.position - c.position; // position 기준 오름차순 정렬
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 바구니의 개수
        int K = Integer.parseInt(tokenizer.nextToken()) * 2; // 구간의 길이
        
        Candy[] candies = new Candy[N]; // [i]: i번째 바구니의 정보
        // HashMap<Integer, Long> candies = new HashMap<>(); // <K, V>: K위치의 사탕의 개수
        int maxPosition = 0; // 최대 위치
        int minPosition = 1_000_000; // 최소 위치
        
        // 좌표에 따른 사탕의 개수 입력
        for (int idx = 0; idx < N; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int count = Integer.parseInt(tokenizer.nextToken()); // 사탕의 개수
            int position = Integer.parseInt(tokenizer.nextToken()); // 바구니의 좌표
            
            candies[idx] = new Candy(position, count);
            // candies.put(position, candies.getOrDefault(position, 0L) + count);
            // maxPosition = Math.max(maxPosition, position);
            // minPosition = Math.min(minPosition, position);
        }

        Arrays.sort(candies);
        
        int endIdx = 0; // 구간의 끝 위치
        long sum = 0; // 구간 내 사탕의 합
        long max = 0; // 사탕의 합의 최댓값
        
        // 구간 내의 사탕의 합 계산
        for (int startIdx = 0; startIdx < N; startIdx++) {
            while (endIdx < N && candies[endIdx].position - candies[startIdx].position <= K) {
                sum += candies[endIdx].count;
                endIdx++;
            }
            
            max = Math.max(max, sum);
            
            sum -= candies[startIdx].count;
        }
        
        System.out.println(max);
    }
}