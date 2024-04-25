import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        
        HashSet<Integer> assure = new HashSet<>();
        for (int i = 0; i < g; i++) {
            st = new StringTokenizer(br.readLine());
            
            int m = Integer.parseInt(st.nextToken());
            int cnt = 0, candidate = 0;
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (j == 0) {
                    assure.add(num);
                    cnt++;
                }
                else if (assure.contains(num)) {
                    cnt++;
                }
                else {
                    candidate = num;
                }
            }

            if (m - cnt == 1) {
                assure.add(candidate);
            }
        }

        System.out.println(assure.size());
    }
}