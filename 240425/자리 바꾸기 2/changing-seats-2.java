import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        HashSet<Integer>[] hs = new HashSet[n + 1];

        int[] num = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            hs[i] = new HashSet<>();
            num[i] = i;
            hs[i].add(i);
        }

        int k = Integer.parseInt(st.nextToken());
        int[][] pos = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 3 * k; i++) {
            // System.out.println(i + " | " + Arrays.toString(num));
            int idx1 = pos[i % k][0];
            int idx2 = pos[i % k][1];
            // System.out.printf("change %d(%d), %d(%d)\n", idx1, num[idx1], idx2, num[idx2]);

            hs[num[idx1]].add(idx2);
            hs[num[idx2]].add(idx1);

            int tmp = num[idx1];
            num[idx1] = num[idx2];
            num[idx2] = tmp;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(hs[i].size());
        }
    }
}