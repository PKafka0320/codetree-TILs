import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 선분의 개수
        int K = Integer.parseInt(tokenizer.nextToken()); // 겹쳐야 하는 선분의 최소 개수
        
        TreeMap<Integer, Integer> points = new TreeMap(); // 선분의 시작점과 끝점에서의 선분 시작(+1)과 끝(-1)의 총합
        
        int currentPosition = 0; // 현재 위치
        for (int n = 0; n < N; n++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int distance = Integer.parseInt(tokenizer.nextToken());
            String direction = tokenizer.nextToken();
            
            if (direction.equals("L")) {
                points.put(currentPosition, points.getOrDefault(currentPosition, 0) -1);
                currentPosition -= distance;
                points.put(currentPosition, points.getOrDefault(currentPosition, 0) +1);
            }
            else if (direction.equals("R")) {
                points.put(currentPosition, points.getOrDefault(currentPosition, 0) +1);
                currentPosition += distance;
                points.put(currentPosition, points.getOrDefault(currentPosition, 0) -1);
            }
        }
        
        int answer = 0; // 선분이 K개 이상 겹치는 구간의 개수
        int currentCount = 0; // 현재 위치에서 겹치는 선분의 개수
        int lastPosition = Integer.MIN_VALUE; // 마지막 확인 위치
        for (Integer position : points.keySet()) {
            if (lastPosition == Integer.MIN_VALUE) {
                lastPosition = position;
            }

            if (currentCount >= K) {
                answer += position - lastPosition;
            }


            currentCount += points.get(position);
            lastPosition = position;
        }
        
        System.out.print(answer);
    }
}