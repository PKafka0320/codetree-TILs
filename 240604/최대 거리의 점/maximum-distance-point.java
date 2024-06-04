import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 점의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 물건의 개수
        
        int[] points = new int[n]; // [i]: i번째 점
        
        // 점 입력
        for (int idx = 0; idx < n; idx++) {
            points[idx] = Integer.parseInt(reader.readLine());
        }
        
        Arrays.sort(points);
        
        int left = 1; // 가능한 인접 거리의 최솟값
        int right = points[n - 1] - points[0]; // 가능한 인접 거리의 최댓값
        int min = Integer.MAX_VALUE; // 가장 인접한 거리의 최댓값
        
        // 인접 거리의 값을 파라매트릭 서치
        while (left <= right) {
            int mid = (left + right) / 2; // 가능한 인접거리의 중앙값
            int count = 1; // 첫 번째 물건 배치
            int lastPoint = points[0]; // 마지막으로 물건을 놓은 위치
            boolean can = false; // 가능한 경우 여부
            
            // 인접거리가 중앙값 이상이 될 수 있는지 확인
            for (int idx = 1; idx < n; idx++) {
                if (points[idx] - lastPoint >= mid) {
                    count++;
                    lastPoint = points[idx];
                    
                    if (count == m) {
                        can = true;
                        break;
                    }
                }
            }
            
            if (can) {
                min = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        
        System.out.println(min);
    }
}