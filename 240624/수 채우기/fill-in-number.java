import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 만들고자 하는 금액
        
        int answer = 0;
        answer += n / 5;
        n %= 5;
        answer += n / 2;
        n %= 2;
        
        if (n == 0) {
            System.out.println(answer);
        }
        else {
            System.out.println(-1);
        }
    }
}