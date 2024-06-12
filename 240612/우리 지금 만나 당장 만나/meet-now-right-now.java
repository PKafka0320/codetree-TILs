import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] info;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine()); // 사람의 수
        
        info = new int[n][2]; // [i][j]: i번째 사람의 위치(j=0)와 이동속도(j=1)
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            info[idx][0] = Integer.parseInt(tokenizer.nextToken());
        }
        
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            info[idx][1] = Integer.parseInt(tokenizer.nextToken());
        }
        
        double low = 0; // 모두가 도착하는 데 걸리는 시간 범위의 시작
        double high = 1_000_000_000; // 모두가 도착하는 데 걸리는 시간 범위의 끝
        double min = high; // 모두가 도착하는 데 걸리는 시간으 최솟값
        
        // 실수형이기 때문에 횟수를 정해 이진 탐색
        for (int count = 0; count < 100; count++) {
            double mid = (low + high) / 2; // 도착 시간 범위의 중앙값
            
            if (isPossible(mid)) {
                min = Math.min(min, mid);
                high = mid;
            }
            else {
                low = mid;
            }
        }
        
        System.out.printf("%.4f", min);
    }
    
    public static boolean isPossible(double time) {
        double maxX1 = info[0][0] - info[0][1] * time;
        double minX2 = info[0][0] + info[0][1] * time;
        
        for(int idx = 1; idx < n; idx++) {
            double x1 = info[idx][0] - info[idx][1] * time;
            double x2 = info[idx][0] + info[idx][1] * time;
    
            // x1들 중 최댓값 갱신
            maxX1 = Math.max(maxX1, x1);
    
            // x2들 중 최솟값 갱신
            minX2 = Math.min(minX2, x2);
        }
    
        // 가능한 구간이 존재하면 모든 사람이 한 곳에 모일 수 있다.
        return maxX1 <= minX2;
    }
}