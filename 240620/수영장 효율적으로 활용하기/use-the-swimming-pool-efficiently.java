import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] times;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken()); // 사람의 수
        m = Integer.parseInt(tokenizer.nextToken()); // 수영장 레인의 수
        
        times = new int[n]; // [i]: i번째 사람의 수영장 이용시간
        
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            times[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        int min = 0; // 가능한 수영장 이용시간의 합의 범위 시작
        int max = 144_000_000; // 가능한 수영장 이용시간의 합의 범위 끝
        int ans = max; // 수영장 이용시간의 합들 중 최댓값의 최솟값
        
        while (min <= max) {
            int mid = (min + max) / 2; // 중앙값
            
            if (isPossible(mid)) {
                ans = Math.min(ans, mid);
                max = mid - 1;
            }
            else {
                min = mid + 1;
            }
        }
        
        System.out.println(ans);
    }
    
    public static boolean isPossible(int t) {
        int count = 1; // 필요한 레인의 수
        int time = 0; // 현재 레인의 이용시간 합
        
        for (int idx = 0; idx < n; idx++) {
            
            if (time + times[idx] > t) {
                count++;
                time = times[idx];
            }
            else {
                time += times[idx];
            }
        }

        return count <= m;
    }
}