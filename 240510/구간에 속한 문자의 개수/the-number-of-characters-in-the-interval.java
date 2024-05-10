import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int n;   //  세로 길이
    private static int m;   //  가로 길이
    private static int k;   //  질의 개수

    private static int[][] aCnt; // aCnt[i][j] : (1, 1)부터 (i, j)까지 a의 개수
    private static int[][] bCnt; // bCnt[i][j] : (1, 1)부터 (i, j)까지 b의 개수
    private static int[][] cCnt; // cCnt[i][j] : (1, 1)부터 (i, j)까지 c의 개수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        aCnt = new int[n + 1][m + 1];
        bCnt = new int[n + 1][m + 1];
        cCnt = new int[n + 1][m + 1];

        for(int i = 0; i < n; i++) {
            String input = br.readLine();

            for(int j = 0; j < m; j++) {
                char ch = input.charAt(j);

                switch(ch) {
                case 'a':
                    aCnt[i + 1][j + 1] = aCnt[i + 1][j] + aCnt[i][j + 1] - aCnt[i][j] + 1;
                    bCnt[i + 1][j + 1] = bCnt[i + 1][j] + bCnt[i][j + 1] - bCnt[i][j];
                    cCnt[i + 1][j + 1] = cCnt[i + 1][j] + cCnt[i][j + 1] - cCnt[i][j];
                    break;
                case 'b':
                    aCnt[i + 1][j + 1] = aCnt[i + 1][j] + aCnt[i][j + 1] - aCnt[i][j];
                    bCnt[i + 1][j + 1] = bCnt[i + 1][j] + bCnt[i][j + 1] - bCnt[i][j] + 1;
                    cCnt[i + 1][j + 1] = cCnt[i + 1][j] + cCnt[i][j + 1] - cCnt[i][j];
                    break;
                case 'c':
                    aCnt[i + 1][j + 1] = aCnt[i + 1][j] + aCnt[i][j + 1] - aCnt[i][j];
                    bCnt[i + 1][j + 1] = bCnt[i + 1][j] + bCnt[i][j + 1] - bCnt[i][j];
                    cCnt[i + 1][j + 1] = cCnt[i + 1][j] + cCnt[i][j + 1] - cCnt[i][j] + 1;
                    break;
                }
            }
        }

        for(int i = 0; i < k; i++) {
            tokens = new StringTokenizer(br.readLine());

            int sy = Integer.parseInt(tokens.nextToken());
            int sx = Integer.parseInt(tokens.nextToken());
            int ey = Integer.parseInt(tokens.nextToken());
            int ex = Integer.parseInt(tokens.nextToken());

            sb.append(aCnt[ey][ex] - aCnt[sy - 1][ex] - aCnt[ey][sx - 1] + aCnt[sy - 1][sx - 1]).append(" ");
            sb.append(bCnt[ey][ex] - bCnt[sy - 1][ex] - bCnt[ey][sx - 1] + bCnt[sy - 1][sx - 1]).append(" ");
            sb.append(cCnt[ey][ex] - cCnt[sy - 1][ex] - cCnt[ey][sx - 1] + cCnt[sy - 1][sx - 1]).append("\n");
        }

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end