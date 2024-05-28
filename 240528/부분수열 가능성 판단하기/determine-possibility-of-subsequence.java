import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 수열 A의 원소의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 수열 B의 원소의 개수
        
        int[] A = new int[n]; // 수열 A
        int[] B = new int[m]; // 수열 B

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
        
        int count = 0; // 부분 수열의 가짓수
        
        // 제거할 원소를 선택
        for (int removeIdx = 0; removeIdx < m; removeIdx++) {
            int idxA = 0; // 수열 A에서 현재 확인하고 있는 원소 위치
            int idxB = 0; // 수열 B에서 현재 확인하고 있는 원소 위치
            
            for (idxB = 0; idxB < m; idxB++) {
                if (idxB == removeIdx) continue;
                
                // B에서 현재 확인하고 있는 원소와 동일한 원소가 나올때까지
                // A에서 확인하고 있는 포인터 이동
                while (idxA < n && A[idxA] != B[idxB]) {
                    idxA++;
                }
                
                // 끝까지 확인한 경우 종료
                if (idxA == n) break;
            }
            
            if (idxA == n && idxB != m) continue;
            count++;
        }
        
        System.out.println(count);
    }
}