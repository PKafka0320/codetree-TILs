import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int position, value;
    
    public Pair(int position, int value) {
        this.position = position;
        this.value = value;
    }
    
    @Override
    public int compareTo(Pair p) {
        return this.position - p.position;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine()); // 구간의 개수
        
        ArrayList<Pair> pairs = new ArrayList<>(); // 구간의 정보
        
        for (int n = 0; n < N; n++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            
            pairs.add(new Pair(start, 1));
            pairs.add(new Pair(end, -1));
        }
        
        Collections.sort(pairs);
        
        int answer = 0; // 최대 구간 길이
        int count = 0; // 현재 위치에서 겹치는 구간의 개수
        int startPosition = Integer.MIN_VALUE; // 구간의 시작 지점
        
        // 각 구간의 시작과 끝점마다 구간의 개수를 계산하고 길이의 합 계산
        for (Pair pair : pairs) {
            if (count == 0 && pair.value == 1) {
                startPosition = pair.position;
            }
            
            count += pair.value;
            
            if (count == 0) {
                answer = Math.max(answer, pair.position - startPosition);
            }
        }
        System.out.println(answer);
    }
}