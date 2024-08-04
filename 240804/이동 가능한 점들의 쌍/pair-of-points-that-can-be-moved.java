import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n][n];
        for (int r = 0; r < n; r++) {
            Arrays.fill(dist[r], (int) 1e9);
            dist[r][r] = 0;
        }

        // 그래프 입력
        for (int r = 0; r < m; r++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());
            
            dist[from][to] = distance;
        }
        
        for (int k = 0; k < n; k++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    if (dist[from][to] > dist[from][k] + dist[k][to]) {
                        dist[from][to] = dist[from][k] + dist[k][to];
                    }
                }
            }
        }
        
        int count = 0;
        long total = 0;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            
            int distance = (int)1e9;
            // 모든 빨간 점에 대해 이동 가능 여부 확인
            for(int stopover = 1; stopover <= p; stopover++) {
                distance = Math.min(distance, dist[from][stopover] + dist[stopover][to]);
            }

            // 만약 이동 불가능하다면 스킵
            if(distance >= (int)1e9)
                continue;
                
            total += distance;
            count++;
        }
        
        System.out.printf("%d\n%d", count, total);
    }

}