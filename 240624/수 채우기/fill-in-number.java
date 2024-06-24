import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 만들고자 하는 금액
        int max = n / 5; // 5원 동전의 최대 개수
        
        while (max >= 0) {
            int left = n - (max * 5);
            
            if (left % 2 == 0) {
                System.out.println(max + (left / 2));
                return;
            }
            max--;
        }
        
        System.out.println(-1);
    }
}