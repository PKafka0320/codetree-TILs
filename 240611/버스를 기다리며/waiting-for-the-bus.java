import java.io.*;
import java.util.*;

public class Main {
    static int N, M, C;
    static int[] arrival;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken()); // 사람의 수
        M = Integer.parseInt(tokenizer.nextToken()); // 버스의 수
        C = Integer.parseInt(tokenizer.nextToken()); // 버스가 태울 수 있는 최대 인원 수
        
        arrival = new int[N]; // [i]: i번째 사람이 정류장에 도착하는 시간
        
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < N; idx++) {
            arrival[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        Arrays.sort(arrival);
        
        int low = 0; // 가장 오래 기다려야하는 사람이 기다리는 시간의 범위 시작
        int high = 1_000_000_000; // 가장 오래 기다려야하는 사람이 기다리는 시간의 범위 끝
        int min = high; // 가장 오래 기다려야하는 사람이 기다리는 시간의 최솟값
        
        while (low <= high) {
            int mid = (low + high) / 2; // 중앙값
            
            if (isPossible(mid)) {
                min = Math.min(min, mid);
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        
        System.out.println(min);
    }
    
    public static boolean isPossible(int waitTime) {
        int count = 1; // 사용된 버스의 수
        int nextIdx = 1; // 다음으로 태울 사람의 번호
        long startTime = arrival[0]; // 사람을 태우기 시작한 시간
        int boardCount = 1; // 태운 사람의 수
        
        while (nextIdx < N) {
            // 사람을 태울 수 있는 경우
            if (arrival[nextIdx] <= startTime + waitTime && boardCount < C) {
                boardCount++;
                nextIdx++;
            }
            // 사람을 태우지 못하는 경우
            else {
                count++;
                startTime = arrival[nextIdx];
                nextIdx++;
                boardCount = 1;
            }
        }
        
        if (count <= M) {
            return true;
        }
        else {
            return false;
        }
    }
}