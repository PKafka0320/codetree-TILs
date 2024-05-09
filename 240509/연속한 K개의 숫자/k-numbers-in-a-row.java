import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(reader.readLine());
        int totalNumbers = Integer.parseInt(token.nextToken());
        int seqNumber = Integer.parseInt(token.nextToken());
        int totalBlanks = Integer.parseInt(token.nextToken());

        int[] blanks = new int[totalNumbers + 1];
        for (int count = 1; count <= totalBlanks; count++) {
            int blank = Integer.parseInt(reader.readLine());
            blanks[blank] = 1;
        }

        int min = seqNumber;
        for (int start = 1; start <= totalNumbers - seqNumber; start++) {
            int count = 0;
            for (int seq = 0; seq < seqNumber; seq++) count += blanks[start + seq];
            min = Math.min(min, count);
        }

        System.out.println(min);
    }
}