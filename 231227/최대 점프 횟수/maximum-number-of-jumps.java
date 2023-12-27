import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] jumpLen;
    static int[] maxJump;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        jumpLen = new int[n];
        maxJump = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            jumpLen[idx] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int idx = 0; idx < n; idx++) {
            answer = Math.max(answer, maxJump[idx]);
            if (idx > 0 && maxJump[idx] == 0) break;
            for (int next = idx + 1; next <= idx + jumpLen[idx]; next++) {
                if (next >= n) break;
                maxJump[next] = Math.max(maxJump[next], maxJump[idx] + 1);
            }
        }

        System.out.println(answer);
    }
}