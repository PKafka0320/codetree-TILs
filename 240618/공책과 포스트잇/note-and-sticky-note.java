import java.io.*;
import java.util.*;

public class Main {
    static int n, k, l;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken()); // 공책의 개수
        k = Integer.parseInt(tokenizer.nextToken()); // 추가적으로 만들 포스트잇의 최대 개수
        l = Integer.parseInt(tokenizer.nextToken()); // 하나의 포스트잇에 적을 수 있는 공책의 번호 개수
        
        arr = new int[n]; // [i]: 처음 i번 공책의 번호가 다른 포스트잇에 적힌 횟수
        
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        Arrays.sort(arr);
        
        int left = 1; // 답이 될 수 있는 가장 작은 값
        int right = n; // 답이 될 수 있는 가장 큰 값
        int ans = 0; // 답
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (isPossible(mid)) {
                ans = Math.max(ans, mid);
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        
        System.out.println(ans);
    }

    // h 이상의 수를 h개 이상 만들 수 있을지 판단
    public static boolean isPossible(int h) {
        // 이미 크기가 큰 h개의 수들에 대해
        // 전부 h 이상이 되기 위해
        // 새로 적혀야 하는 번호의 수를 계산
        long cnt = 0;
        
        for (int idx = n - h; idx < n; idx++) {
            if (arr[idx] < h) {
                cnt += h - arr[idx];
            }
        }
        
        // 새로 적혀야 하는 번호의 수가 최대로 적을 수 있는 수인 k * l 이하이며
        // arr[n - h] + k가 h 이상이어야만 k개의 포스트잇으로 해결이 가능
        return cnt <= (long) k * l && arr[n - h] + k >= h;
    }
}