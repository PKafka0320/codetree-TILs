import java.io.*;
import java.util.*;

public class Main {
    static int sum;
    static List<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n -1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()) - 1;
            int child = Integer.parseInt(st.nextToken()) - 1;

            tree[parent].add(child);
        }

        sum = 0;
        dfs(0);

        if (sum % 2 == 0) {
            System.out.println(0);
        } else {
            System.out.println(1);
        }
    }

    public static void dfs(int node) {
        for (int child : tree[node]) {
            sum++;
            dfs(child);
        }
    }
}