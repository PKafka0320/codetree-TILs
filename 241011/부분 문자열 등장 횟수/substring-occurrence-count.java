import java.util.*;
import java.io.*;

public class Main {
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String parent = br.readLine();
        String pattern = br.readLine();
        startKMP(parent, pattern);
        System.out.println(answer);
    }

    public static int[] makePatternTable(String str) {
        int strSize = str.length();
        int j = 0;
        int[] table = new int[strSize];
        
        for(int i=1; i<strSize; i++) {
            while(j > 0 && str.charAt(j) != str.charAt(i)) {
                j = table[j-1];
            }
            
            if(str.charAt(j) == str.charAt(i)) {
                table[i] = j+1;
                j += 1;
            }
        }

        return table;
    }

    public static void startKMP(String parent, String pattern) {
        int[] table = makePatternTable(pattern);

        int parentSize = parent.length();   
        int patternSize = pattern.length();
        int j = 0;

        for(int i=0; i<parentSize; i++) {
            while(j>0 && parent.charAt(i) != pattern.charAt(j)) {
                j = table[j-1];
            }

            if(parent.charAt(i) == pattern.charAt(j)) {
                if(j == patternSize - 1) {
                    answer++;
                    j = table[j];
                } else {
                    j += 1;
                }
            }
        }
    }
}