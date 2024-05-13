import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] infos;
    static int[][] L, R;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(reader.readLine()); // 정보의 개수
        infos = new int[N + 2]; // B가 내는 정보
        L = new int[N + 2][3]; // L[i][j] : i번째까지 j를 냈을때 이기는 게임 수
        R = new int[N + 2][3]; // R[i][j] : i번째부터 j를 냈을때 이기는 게임 수
        
        // B가 내는 것
        // 0: 가위, 1: 바위, 2: 보
        for (int idx = 1; idx <= N; idx++) {
            String info = reader.readLine();

            if (info.equals("S")) {
                infos[idx] = 0;
            }
            else if (info.equals("H")) {
                infos[idx] = 1;
            }
            else if (info.equals("P")) {
                infos[idx] = 2;
            }
        }

        // L 배열 생성
        for (int idx = 1; idx <= N; idx++) {
            for (int strat = 0; strat < 3; strat++) {
                L[idx][strat] = L[idx - 1][strat];
                if ((infos[idx] + 1) % 3 == strat) L[idx][strat]++;
            }
        }

        // R 배열 생성
        for (int idx = N; idx >= 1; idx--) {
            for (int strat = 0; strat < 3; strat++) {
                R[idx][strat] = R[idx + 1][strat];
                if ((infos[idx] + 1) % 3 == strat) R[idx][strat]++;
            }
        }

        int ans = 0;
        for (int idx = 1; idx < N; idx++) {
            int win = Arrays.stream(L[idx]).max().getAsInt() + Arrays.stream(R[idx + 1]).max().getAsInt();
            ans = Math.max(ans, win);
        }
        System.out.println(ans);
    }
}