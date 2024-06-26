import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 카드의 개수
        
        int[] b = new int[n]; // [i]: b의 i번째 카드
        TreeSet<Integer> a = new TreeSet<>();
        
        for (int number = 1; number <= 2 * n; number++) {
            a.add(number);
        }
        
        for (int idx = 0; idx < n; idx++) {
            b[idx] = Integer.parseInt(reader.readLine());
            a.remove(b[idx]);
        }
        
        Arrays.sort(b);

        int answer = 0;
        
        for (int idx = 0; idx < n; idx++) {
            Integer ceiling = a.ceiling(b[idx]);
            
            if (ceiling == null) {
                a.pollFirst();
            }
            else {
                a.remove(ceiling);
                answer++;
            }
        }
        
        System.out.println(answer);
    }
}