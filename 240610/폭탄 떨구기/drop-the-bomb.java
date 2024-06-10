import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] positions;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken()); // 점의 개수
        K = Integer.parseInt(tokenizer.nextToken()); // 폭탄을 떨어뜨리는 횟수
        
        positions = new int[N]; // [i]: i번재 점의 위치
        
        // 점의 위치 입력
        for (int idx = 0; idx < N; idx++) {
            positions[idx] = Integer.parseInt(reader.readLine());
        }
        
        // 폭탄의 범위를 오름차순으로 확인하기 위해 정렬
        Arrays.sort(positions);
        
        int low = 0; // 모든 점을 제거할 수 있는 폭발 범위의 시작
        int high = 1_000_000_000; // 모든 점을 제거할 수 있는 폭발 범위의 끝
        int min = high; // 모든 점을 제거할 수 있는 폭발 범위의 최솟값
        
        // parametric search
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
    
    public static boolean isPossible(int r) {
        int count = 1; // 사용된 폭탄의 개수
        int idx = 0; // 현재까지 확인한 점의 위치
        long startPosition = positions[idx]; // 폭탄의 범위 시작 위치
        
        while (count <= K && idx < N - 1) {
            // 폭탄의 범위에 속하면 다음 위치 확인
            if (startPosition + (2 * r) >= positions[idx + 1]) {
                idx++;
            }
            
            // 속하지 않을 경우 폭탄의 개수 추가, 폭탄의 범위 시작 위치 갱신
            else {
                count++;
                idx++;
                startPosition = positions[idx];
            }
        }
        
        if (count <= K && idx == N - 1) {
            return true;
        }
        else {
            return false;
        }
    }
}