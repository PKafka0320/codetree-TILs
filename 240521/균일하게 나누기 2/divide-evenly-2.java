import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        return this.x - p.x; // x 기준 오름차순 정렬
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 좌표의 개수
        
        Point[] points = new Point[n]; // 좌표 정보
        int maxY = 0; // y좌표의 최댓값
        
        // 좌표 정보 입력
        for (int idx = 0; idx < n; idx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            
            points[idx] = new Point(x, y);
            maxY = Math.max(maxY, y);
        }
        
        Arrays.sort(points);
        
        // 1,2 / 3,4 사분면을 나누는 y값을 설정하고 x값마다 각 사분면의 개수를 계산
        int answer = Integer.MAX_VALUE;
        for (int setY = 0; setY <= maxY; setY++) {
            int[] count = new int[5]; // 각 사분면에 들어 있는 좌표의 개수
            
            // x = 0 기준으로 개수 초기화 (1, 4 사분면)
            for (int idx = 0; idx < n; idx++) {
                if (points[idx].y > setY) {
                    count[1]++;
                }
                else {
                    count[4]++;
                }
            }
            
            // x값으로 정렬된 좌표들을 하나씩 2, 3 사분면으로 보정하면서 개수 계산
            for (int idx = 0; idx < n; idx++) {
                // x값이 변하는 경우 구분선이 이동하는 상황이므로 각 사분면의 개수중 최댓값 계산
                if (idx == 0 || points[idx].x != points[idx - 1].x) {
                    int maxCount = 0;
                    for (int quadrant = 1; quadrant <= 4; quadrant++) {
                        maxCount = Math.max(maxCount, count[quadrant]);
                    }
                    
                    answer = Math.min(answer, maxCount);
                }
                
                // 1, 4 사분면에 있던 좌표를 2, 3 사분면으로 보정
                if (points[idx].y > setY) {
                    count[1]--;
                    count[2]++;
                }
                else {
                    count[4]--;
                    count[3]++;
                }
            }
        }
        
        System.out.print(answer);
    }
}