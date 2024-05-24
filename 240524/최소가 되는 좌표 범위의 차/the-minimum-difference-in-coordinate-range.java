import java.io.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

class Pair implements Comparable<Pair> {
    int x, y;
    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair p) {
        return this.x - p.x; // x 기준 오름차순 정렬
    }
    
    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}

class TargetPoint implements Comparable<TargetPoint> {
    int x, y;

    public TargetPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(TargetPoint p) {
        if(this.y != p.y)
            return this.y - p.y; // y 기준 오름차순 정렬
        return this.x - p.x; // x 기준 오름차순 정렬
    }
}

public class Main {
    static TreeSet<TargetPoint> pointCount = new TreeSet<>(); // 두 점과 그 사이의 점들

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 점의 개수
        int D = Integer.parseInt(tokenizer.nextToken()); // 두 점의 y 좌표의 최소 차이

        Pair[] pairs = new Pair[N]; // [i]: i번째 점
        
        for (int idx = 0; idx < N; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            
            pairs[idx] = new Pair(x, y);
        }
        
        Arrays.sort(pairs);
        
        System.out.println(Arrays.toString(pairs));
        
        int secondIdx = -1; // 두 번째 점의 인덱스
        int min = Integer.MAX_VALUE; // 두 점의 x 좌표의 최소 차이
        
        // 구간 생성
        for (int firstIdx = 0; firstIdx < N; firstIdx++) {
            // y좌표 차가 d가 되기 전까지 진행
            while (secondIdx + 1 < N && getMax() - getMin() < D) {
                pointCount.add(new TargetPoint(pairs[secondIdx + 1].x, pairs[secondIdx + 1].y));
                secondIdx++;
            }
            
            // 최대한 이동했는데도 조건에 만족하지 못하면 종료
            if (getMax() - getMin() < D) {
                break;
            }
            
            // 구간 크기 중 최솟값 갱신
            min = Math.min(min, pairs[secondIdx].x - pairs[firstIdx].x);
            
            // 첫 번째 점의 정보 제거
            pointCount.remove(new TargetPoint(pairs[firstIdx].x, pairs[firstIdx].y));
        }
        
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        
        System.out.println(min);
    }
    
    public static int getMin() {
        if(pointCount.isEmpty()) return 0;
        return pointCount.first().y; 
    }
    
    public static int getMax() {
        if(pointCount.isEmpty()) return 0;
        return pointCount.last().y;
    }
}