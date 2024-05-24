import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int x, y;
    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair p) {
        return this.y - p.y;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 점의 개수
        int D = Integer.parseInt(tokenizer.nextToken()); // 두 점의 y 좌표의 최소 차이

        Pair[] pairs = new Pair[N];
        
        for (int idx = 0; idx < N; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            
            pairs[idx] = new Pair(x, y);
        }
        
        Arrays.sort(pairs);
        
        int secondIdx = N - 1; // 두 번째 점의 인덱스
        int min = Integer.MAX_VALUE; // 두 점의 x 좌표의 최소 차이
        
        for (int firstIdx = 0; firstIdx < N; firstIdx++) {
            if (firstIdx < secondIdx && Math.abs(pairs[firstIdx].y - pairs[secondIdx].y) >= D) {
                min = Math.min(min, Math.abs(pairs[firstIdx].x - pairs[secondIdx].x));
            }
            
            while (secondIdx >= 0 && Math.abs(pairs[firstIdx].y - pairs[secondIdx].y) >= D) {
                min = Math.min(min, Math.abs(pairs[firstIdx].x - pairs[secondIdx].x));
                secondIdx--;
            }
        }
        
        System.out.println(min);
    }
}