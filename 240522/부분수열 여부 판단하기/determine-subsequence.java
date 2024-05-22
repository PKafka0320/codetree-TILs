import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 수열 A의 원소의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 수열 B의 원소의 개수
        
        int[] A = new int[n]; // [i]: 수열 A의 i번째 수
        int[] B = new int[m]; // [i]: 수열 B의 i번째 수
        
        // 수열 A 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            A[idx] = Integer.parseInt(tokenizer.nextToken()); 
        }
        
        // 수열 B 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < m; idx++) {
            B[idx] = Integer.parseInt(tokenizer.nextToken()); 
        }
        
        int currentIdx = 0; // 현재 확인하고 있는 수열 A의 원소 위치
        boolean isSub = true; // 부분 수열 여부
        
        // 현재 확인하고 있는 수열 B의 원소 위치를 이동시키면서 수열 A에 포함되는지 확인
        for (int idx = 0; idx < m; idx++) {
            // A의 수와 B의 수가 일치해지는 위치 계산
            while (currentIdx < n && A[currentIdx] != B[idx]) {
                currentIdx++;
            }
            
            // 일치하는 원소를 찾지 못한다면 부분 수열이 아님
            if (currentIdx == n) {
                isSub = false;
                break;
            }
            // 일치한다면 확인하고 있는 A의 원소 위치를 다음 원소로 이동
            else {
                currentIdx++;
            }
        }
        
        if (isSub) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}