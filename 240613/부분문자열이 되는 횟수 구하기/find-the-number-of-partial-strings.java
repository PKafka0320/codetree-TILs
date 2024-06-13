import java.io.*;
import java.util.*;

public class Main {
    static String a, b;
    static int n, m;
    static int[] delete;
    static boolean[] skip;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        a = reader.readLine(); // 문자열 a
        b = reader.readLine(); // 문자열 b
        n = a.length();
        m = b.length();
        
        delete = new int[n]; // [i]: i번째 삭제할 문자열 a의 문자 인덱스
        skip = new boolean[n]; // [i]: 문자열 a의 i번째 문자의 삭제 여부 
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            delete[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        int low = 0; // 답이 될 수 있는 값의 범위 시작
        int high = n; // 답이 될 수 있는 값의 범위 끝
        int answer = -1; // 답이 될 수 있는 값의 최댓값
        
        // 특정 순간부터 부분 문자열이 아니게 되므로, 몇 번째 문자까지 지워졌는지를 정해서 확인 (parametric search)
        while (low <= high) {
            int mid = (low + high) / 2; // 중앙값
            
            if (isPossible(mid)) {
                answer = Math.max(answer, mid);
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
            
            // skip 배열 초기화
            for(int idx = 0; idx < mid; idx++) {
                skip[delete[idx] - 1] = false;
            }
        }
        
        // 삭제하는 문자가 0번째부터 시작하기 때문에 +1
        System.out.println(answer + 1);
    }
    
    public static boolean isPossible(int mid) {
        int bIdx = 0; // 현재 확인하는 문자열 b의 문자 인덱스
        
        // mid번째까지 문자 제거
        for (int idx = 0; idx < mid; idx++) {
            skip[delete[idx] - 1] = true;
        }
        
        // two pointer로 부분 문자열 확인
        for(int idx = 0; idx <= n - 1; idx++) {
            if(skip[idx]) continue;
            if(bIdx < m && a.charAt(idx) == b.charAt(bIdx)) bIdx++;
        }
        
        return bIdx == m;
    }
}