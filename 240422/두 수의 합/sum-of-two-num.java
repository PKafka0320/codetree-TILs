import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static long k;
    static long[] nums;
    static HashMap<Long, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());

        nums = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            long diff = k - nums[i];

            ans += hm.getOrDefault(diff, 0);
            hm.put(nums[i], hm.getOrDefault(nums[i], 0) + 1);
        }

        System.out.println(ans);
    }
}