import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 숫자의 개수
        
        Integer[] numbers = new Integer[n]; // [i]: i번째 숫자
        
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(reader.readLine());
        }
        
        Arrays.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String sa = a.toString();
                String sb = b.toString();
                
                String ab = sa + sb;
                String ba = sb + sa;
                
                long lab = Long.parseLong(ab);
                long lba = Long.parseLong(ba);
                
                if (lab < lba) return 1;
                else return -1;
            }
        });

        StringBuilder answer = new StringBuilder();
        for (int idx = 0; idx < n; idx++) {
            answer.append(numbers[idx]);
        }
        
        System.out.println(answer);
    }
}