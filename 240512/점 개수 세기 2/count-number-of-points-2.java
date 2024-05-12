import java.io.*;
import java.util.*;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, q;
    static Point[] points; // 좌표
    static TreeSet<Integer> numbers; // 숫자
    static HashMap<Integer, Integer> sequence; // 숫자 순서
    static int[][] cmpGrid; // 압축 좌표
    static int[][] prefixSum; // 개수 누적 합

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer token = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(token.nextToken()); // 점 개수
        q = Integer.parseInt(token.nextToken()); // 질의 개수

        points = new Point[n];
        numbers = new TreeSet<>();
        sequence = new HashMap<>();

        // 숫자 입력
        for (int idx = 0; idx < n; idx++) {
            token = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());

            numbers.add(x);
            numbers.add(y);
            points[idx] = new Point(x, y);
        }

        // 숫자순으로 번호 부여
        int cnt = 1;
        for (int num : numbers) {
            sequence.put(num, cnt++);
        }

        // 부여한 번호로 좌표 압축
        cmpGrid = new int[cnt + 1][cnt + 1];
        for (int idx = 0; idx < n; idx++) {
            Point point = points[idx];
            int x = point.x;
            int y = point.y;

            int cmpX = sequence.get(x);
            int cmpY = sequence.get(y);

            cmpGrid[cmpX][cmpY]++;
        }

        // 개수 누적 합 계산
        // y 축의 방향이 row의 양의 방향과 동일한 점 유의
        prefixSum = new int[cnt + 1][cnt + 1];
        for (int row = 1; row <= cnt; row++) {
            for (int col = 1; col <= cnt; col++) {
                prefixSum[row][col] = cmpGrid[row][col] + prefixSum[row - 1][col] 
                    + prefixSum[row][col - 1] - prefixSum[row - 1][col - 1];
            }
        }

        // 누적 합을 사용해 개수 계산
        StringBuilder answer = new StringBuilder();
        for (int query = 0; query < q; query++) {
            token = new StringTokenizer(reader.readLine());
            int x1 = Integer.parseInt(token.nextToken());
            int y1 = Integer.parseInt(token.nextToken());
            int x2 = Integer.parseInt(token.nextToken());
            int y2 = Integer.parseInt(token.nextToken());

            int cnvtX1 = sequence.get(numbers.ceiling(x1));
            int cnvtY1 = sequence.get(numbers.ceiling(y1));
            int cnvtX2 = sequence.get(numbers.floor(x2));
            int cnvtY2 = sequence.get(numbers.floor(y2));

            int ans = prefixSum[cnvtX2][cnvtY2] - prefixSum[cnvtX1 - 1][cnvtY2] 
                - prefixSum[cnvtX2][cnvtY1 - 1] + prefixSum[cnvtX1 - 1][cnvtY1 - 1];
            answer.append(ans).append("\n");
        }
        System.out.println(answer);
    }
}