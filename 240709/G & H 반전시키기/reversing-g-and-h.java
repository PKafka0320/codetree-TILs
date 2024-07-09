import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        char[] before = br.readLine().toCharArray();
        char[] after = br.readLine().toCharArray();

        boolean flipping = false;
        int count = 0;

        for (int idx = 0; idx < n; idx++) {
            if (before[idx] == after[idx] && flipping == true) {
                flipping = false;
            }
            else if (before[idx] != after[idx] && flipping == false) {
                flipping = true;
                count++;
            }
        }

        System.out.println(count);
    }
}