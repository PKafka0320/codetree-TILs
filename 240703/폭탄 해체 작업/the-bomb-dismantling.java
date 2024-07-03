import java.io.*;
import java.util.*;

class Bomb implements Comparable<Bomb> { 
    int timeLimit, score;
    
    public Bomb(int timeLimit, int score) { 
        this.timeLimit = timeLimit; 
        this.score = score; 
    }
    
    @Override
    public int compareTo(Bomb other) {
        if(timeLimit != other.timeLimit) return timeLimit - other.timeLimit;
        return score - other.score;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int n = Integer.parseInt(reader.readLine()); // 폭탄의 수
        int maxTime = 0; // 폭탄의 시간 중 최댓값
        Bomb[] bombs = new Bomb[n]; // [i]: i번째 폭탄
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 시간에 따른 폭탄의 우선순위
        
        for (int idx = 0; idx < n; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int score = Integer.parseInt(tokenizer.nextToken());
            int timeLimit = Integer.parseInt(tokenizer.nextToken());
            
            maxTime = Math.max(maxTime, timeLimit);            
            bombs[idx] = new Bomb(timeLimit, score);
        }
        
        Arrays.sort(bombs);
        
        int bombIdx = n - 1;
        int answer = 0;
        
        for (int time = maxTime; time >= 1; time--) {
            while (bombIdx >= 0 && bombs[bombIdx].timeLimit >= time) {
                pq.add(-bombs[bombIdx].score);
                bombIdx--;
            }
            
            if (!pq.isEmpty()) {
                answer += -pq.poll();
            }
        }
        
        System.out.println(answer);
    }
}