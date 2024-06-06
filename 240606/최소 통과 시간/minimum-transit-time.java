import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 물건의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 통로의 개수
        
        int[] paths = new int[m]; // [i]: i번째 통로를 통과하는데 걸리는 시간
        
        // 통로 입력
        for (int idx = 0; idx < m; idx++) {
            paths[idx] = Integer.parseInt(reader.readLine());
        }
        
        Arrays.sort(paths);
        
        int low = 1; // 최소 시간 범위의 시작
        int high = paths[m - 1] * n; // 최소 시간 범위의 끝
        int min = high; // 최소 시간
        
        // Parametric Search
        while (low <= high) {
            int mid = (low + high) / 2; // 최소 시간 범위의 중앙값
            long count = 0; // 중앙값에 통과되는 물건의 개수

            for (int idx = 0; idx < m; idx++) {
                count += mid / paths[idx];
                
                if (count >= n) break;
            }
            
            if (count >= n) {
                high = mid - 1;
                min = Math.min(min, mid);
            }
            else {
                low = mid + 1;
            }
        }
        
        System.out.println(min);
    }
}