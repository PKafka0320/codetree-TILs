import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 사람의 수
        int T = Integer.parseInt(tokenizer.nextToken()); // 모든 사람이 무대에서 내려갈 때 까지 걸리는 시간의 최댓값
        
        int[] times = new int[N]; // [i]: i번째 사람이 무대 위에 머무르는 시간
        
        for (int idx = 0; idx < N; idx++) {
            times[idx] = Integer.parseInt(reader.readLine());
        }
        
        int min = 1; // 무대 위에 올라올 수 있는 사람 수의 범위 시작
        int max = N; // 무대 위에 올라올 수 있는 사람 수의 범위 끝
        int answer = N; // T를 넘지 않는 시간 내에 모든 사람이 무대를 올라왔다 내려갈 수 있는 무대 수용 인원의 최솟값
        
        while (min <= max) {
            int mid = (min + max) / 2; // 중앙값
            PriorityQueue<Integer> lapse = new PriorityQueue<>(); // 끝나는 시간
            
            // 맨 처음 올라가있는 사람
            for (int idx = 0; idx < mid; idx++) {
                lapse.add(times[idx]);
            }
            
            // 시간 경과에 따라 올라가는 사람
            for(int idx = mid; idx < N; idx++) {
                int currTime = lapse.poll();
                
                lapse.add(currTime + times[idx]);
            }

            int endTime = 0;
            
            // 마지막 사람의 시간 계산
            while(!lapse.isEmpty()) {
                endTime = Math.max(endTime, lapse.poll());
            }
            
            if (endTime <= T) {
                answer = Math.min(answer, mid);
                max = mid - 1;
            }
            else {
                min = mid + 1;
            }
        }
        
        System.out.println(answer);
    }
}