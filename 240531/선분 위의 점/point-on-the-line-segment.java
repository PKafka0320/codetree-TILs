import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 점의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 선분의 개수
        
        int[] points = new int[n]; // [i]: i번째 점의 위치
        tokenizer = new StringTokenizer(reader.readLine());
        
        // 점의 위치 입력
        for (int idx = 0; idx < n; idx++) {
            points[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        Arrays.sort(points);
        
        StringBuilder answer = new StringBuilder();
        
        while (m-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()); // 선분의 시작 위치
            int end = Integer.parseInt(tokenizer.nextToken()); // 선분의 끝 위치
            
            int left = 0; // 구간의 시작
            int right = n - 1; // 구간의 끝
            int startIndex = n - 1; // 구간에 포함되는 첫 번째 점의 인덱스
            
            // startIndex 탐색
            while (left <= right) {
                int mid = (left + right) / 2;
                
                if (points[mid] >= start) {
                    right = mid - 1;
                    startIndex = Math.min(startIndex, mid);
                }
                else {
                    left = mid + 1;
                }
            }
            
            left = 0; // 구간의 시작
            right = n - 1; // 구간의 끝
            int endIndex = 0; // 구간에 포함되는 마지막 점의 인덱스
            
            // endIndex 탐색
            while (left <= right) {
                int mid = (left + right) / 2;
                
                if (points[mid] <= end) {
                    left = mid + 1;
                    endIndex = Math.max(endIndex, mid);
                }
                else {
                    right = mid - 1;
                }
            }
            
            if (startIndex == endIndex && (start > points[startIndex] || end < points[endIndex])) {
                answer.append("0\n");
            }
            else {
                answer.append(endIndex - startIndex + 1).append("\n");
            }
        }
        
        System.out.println(answer);
    }
}