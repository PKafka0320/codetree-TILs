import java.io.*;
import java.util.*;

public class Main {
    // 하, 상, 우, 좌
    static int[] dr = new int[] {1, -1, 0, 0};
    static int[] dc = new int[] {0, 0, 1, -1};
    static int[][] grid;
    static boolean[][][] visitedRed;
    static boolean[][][] visitedBlue;
    static int answer = (int) 1e9;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        grid = new int[n][m]; // -1: wall, 1: red, 2: blue, 3: hall
        visitedRed = new boolean[n][m][4]; // [i][j][k]: 빨간 구슬이 i,j 위치에 k 방향으로 왔는지 확인
        visitedBlue = new boolean[n][m][4]; // [i][j][k]: 파란 구슬이 i,j 위치에 k 방향으로 왔는지 확인

        int rr = -1;
        int rc = -1; 
        int br = -1; 
        int bc = -1;

        // 보드 생성
        for (int i = 0; i < n; i++) {
            String line = reader.readLine();

            for (int j = 0; j < m; j++) {
                char tile = line.charAt(j);

                if (tile == '#') {
                    grid[i][j] = -1;
                } else if (tile == 'R') {
                    grid[i][j] = 1;
                    rr = i;
                    rc = j;
                } else if (tile == 'B') {
                    grid[i][j] = 2;
                    br = i;
                    bc = j;
                } else if (tile == 'O') {
                    grid[i][j] = 3;
                }
            }
        }

        // 시작 방향
        for (int d = 0; d < 4; d++) {
            dfs(1, d, rr, rc, br, bc);
        }
        
        if (answer == (int) 1e9) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    public static void dfs(int count, int dir, int rr, int rc, int br, int bc) {
        // System.out.println(rr + "," + rc + " | " + br + "," + bc);
        if (visitedRed[rr][rc][dir] &&
            visitedBlue[br][bc][dir]) return;

        int originrr = rr;
        int originrc = rc;
        int originbr = br;
        int originbc = bc;

        visitedRed[rr][rc][dir] = true;
        visitedBlue[br][bc][dir] = true;

        int nrr = rr + dr[dir];
        int nrc = rc + dc[dir];
        int nbr = br + dr[dir];
        int nbc = bc + dc[dir];
        System.out.println(nrr + "," + nrc + " | " + nbr + "," + nbc);
        System.out.println(grid[nrr][nrc] + "," + grid[nbr][nbc]);

        // 구슬 이동
        while (grid[nrr][nrc] == 0 || grid[nbr][nbc] == 0) {
            visitedRed[nrr][nrc][dir] = true;
            visitedBlue[nbr][nbc][dir] = true;
            boolean blueNotMoved = true;

            // 빨간 구슬 이동
            // 이동 위치에 파란 구슬이 있을 경우 파란 구슬 먼저 이동
            if (grid[nrr][nrc] == 0) {
                grid[nrr][nrc] = 1;
                grid[rr][rc] = 0;

                rr = nrr;
                rc = nrc;
            } else if (grid[nrr][nrc] == 2 && grid[nbr][nbc] == 0) {
                grid[nbr][nbc] = 2;
                grid[nrr][nrc] = 1;
                grid[rr][rc] = 0;

                br = nbr;
                bc = nbc;

                blueNotMoved = false;
            }

            // 빨간 구슬이 이동할 때 파란란구슬이 이동하지 않았다면 파란 구슬 이동
            if (blueNotMoved && grid[nbr][nbc] == 0) {
                grid[nbr][nbc] = 2;
                grid[br][bc] = 0;

                br = nbr;
                bc = nbc;
            }

            if (grid[nbr][nbc] == 3) return;

            nrr = rr + dr[dir];
            nrc = rc + dc[dir];
            nbr = br + dr[dir];
            nbc = bc + dc[dir];
        }

        // 이동한 위치에서 4방향으로 탐색
        for (int d = 0; d < 4; d++) {
            dfs(count + 1, d, rr, rc, br, bc);
        }
    }
}