import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int n = Integer.parseInt(reader.readLine()); // 서로 다른 자연수의 개수
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 모든 자연수 목록

        for (int idx = 0; idx < n; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int count = Integer.parseInt(tokenizer.nextToken());
            int number = Integer.parseInt(tokenizer.nextToken());

            for (int currentCount = 0; currentCount < count; currentCount++) {
                pq.add(number);
            }
        }

        Object[] sums = pq.toArray();
        
        int min = (int) sums[0] + (int) sums[sums.length - 1];
        int startIdx = 1;
        int endIdx = sums.length - 2;

        while (startIdx < endIdx) {
            min = Math.min(min,(int) sums[startIdx++] + (int) sums[endIdx--]);
        }

        System.out.println(min);
    }
}