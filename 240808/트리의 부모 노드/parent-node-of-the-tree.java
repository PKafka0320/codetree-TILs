import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        
        int[] parents = new int[n];
        
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()) - 1;
            int child = Integer.parseInt(st.nextToken()) - 1;
            
            parents[child] = parent;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            sb.append(parents[i] + 1).append("\n");
        }
        
        System.out.println(sb);
    }

}