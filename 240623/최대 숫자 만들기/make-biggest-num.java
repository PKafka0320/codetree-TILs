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
                String s1 = Integer.toString(a) + Integer.toString(b);
                String s2 = Integer.toString(b) + Integer.toString(a);
                return s2.compareTo(s1);
            }
        });

        StringBuilder answer = new StringBuilder();
        for (int idx = 0; idx < n; idx++) {
            answer.append(numbers[idx]);
        }
        
        System.out.println(answer);
    }
}