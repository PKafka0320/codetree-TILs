import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int from, to;
    
    public Pair(int from, int to) {
        this.from = from;
        this.to = to;
    }
    
    @Override
    public int compareTo(Pair other) {
        if (from == other.from) return to - other.to;
        return from - other.from;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 선분의 개수
        
        Pair[] segments = new Pair[n];
        
        for (int idx = 0; idx < n; idx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            
            segments[idx] = new Pair(from, to);
        }
        
        Arrays.sort(segments);
        
        long low = 1; // 점 사이의 최소 거리가 될수 있는 범위 시작
        long high = (long) 1e9; // 점 사이의 최소 거리가 될수 있는 범위 시작
        long max = 1; // 점 사이의 최소 거리의 최댓값
        
        while (low <= high) {
            long mid = (low + high) / 2; // 중앙값
            boolean cannotBuild = false;
            
            // 점 사이의 거리가 최대가 되도록 하기 위해 첫 번째 선분의 가장 왼쪽을 시작 지점으로 설정
            long currentPosition = segments[0].from; // 시작 위치
            
            for (int idx = 1; idx < n; idx++) {
                long nextPosition = currentPosition + mid;
                
                if (segments[idx].to < nextPosition) {
                    cannotBuild = true;
                    break;
                }
                
                if (nextPosition < segments[idx].from) {
                    currentPosition = segments[idx].from;
                }
                else {
                    currentPosition = nextPosition;
                }
            }
            
            if (cannotBuild) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
                max = Math.max(max, mid);
            }
        }
        
        System.out.println(max);
    }
}