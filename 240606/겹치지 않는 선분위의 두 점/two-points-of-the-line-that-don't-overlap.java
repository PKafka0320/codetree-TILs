import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> { 
    long from, to;
    
    public Pair(long from, long to) { 
        this.from = from; 
        this.to = to; 
    }
    
    @Override
    public int compareTo(Pair other) {
        if(from > other.from) return 1;
        if(from < other.from) return -1;

        if(to > other.to) return 1;
        return -1;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 점의 개수
        int M = Integer.parseInt(tokenizer.nextToken()); // 선분의 개수
        
        Pair[] segments = new Pair[M]; // [i]: i번째 선분
        
        // 선분 입력
        for (int idx = 0; idx < M; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            
            segments[idx] = new Pair(from, to);
        }
        
        // 선분의 시작점을 기준으로 오름차순 정렬
        Arrays.sort(segments);
        
        long left = 1; // 답이 될 수 있는 값의 범위 시작
        long right = (long) 1e18; // 답이 될 수 있는 값의 범위 끝
        long max = 0; // 가장 가까운 거리의 최댓값
        
        while (left <= right) {
            long mid = (left + right) / 2; // 될 수 있는 범위의 중앙값
            
            long count = 0; // 인접거리가 중앙값일때 배치 가능한 점의 개수
            long lastPosition = - (long) 1e18; // 마지막으로 배치한 점의 위치
            
            // 놓을 수 있는 점의 수 게산
            for (int idx = 0; idx < M; idx++) {
                long from = segments[idx].from;
                long to = segments[idx].to;
                
                while (lastPosition + mid <= to) {
                    count++;
                    lastPosition = Math.max(lastPosition + mid, from);
                    
                    if (count >= N) {
                        break;
                    }
                }
            }
            
            if (count >= N) {
                left = mid + 1;
                max = Math.max(max, mid);
            }
            else {
                right = mid - 1;
            }
        }
        
        System.out.println(max);
    }
}