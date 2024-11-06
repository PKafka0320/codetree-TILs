import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String text = br.readLine();
		String pattern = br.readLine();

		int tLen = text.length();
		int pLen = pattern.length();
		int p = 31, m = (int) 1e9 + 7;

		long[] pPow = new long[pLen + 1]; // pattern power(p, i)
		pPow[0] = 1;
		for (int i = 1; i <= pLen; i++) {
			pPow[i] = (pPow[i - 1] * p) % m;
		}

		long pH = 0; // pattern hash [0, pLen]
		for (int i = 0; i < pLen; i++) {
			pH = (pH + (toInt(pattern.charAt(i)) * pPow[pLen - i - 1])) % m;
		}

		long tH = 0; // text hash [0, pLen]
		for (int i = 0; i < pLen; i++) {
			tH = (tH + (toInt(text.charAt(i)) * pPow[pLen - i - 1])) % m;
		}

		if (pH == tH) {
			System.out.println(0);
			return;
		}
		
		int answer = -1;
		for (int i = 1; i <= tLen - pLen; i++) {
			tH = (tH * p - (toInt(text.charAt(i-1)) * pPow[pLen]) + toInt(text.charAt(i+pLen -1))) % m;
			
			if (tH < 0) {
				tH += m;
			}
			
			if (pH == tH) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
	}

	public static int toInt(char c) {
		return c - 'a' + 1;
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 500000;
    
    // 변수 선언
    public static String text, pattern;
    
    public static int n, l;
    
    // 2개의 polynomial rolling 해싱을 위한 p, m 값을 정의합니다.
    public static int[] p = new int[]{31, 37};
    public static int[] m = new int[]{(int)1e9 + 7, (int)1e9 + 9};
    
    // p^i, 값을 m으로 나눈 나머지를 관리합니다.
    public static long[][] pPow = new long[2][MAX_N + 1];
    
    // 소문자 알파벳을 수로 변경합니다.
    public static int toInt(char c) {
        return c - 'a' + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        text = sc.next();
        pattern = sc.next();

        n = text.length();
        l = pattern.length();

        // pPow 값을 계산합니다.
        for(int k = 0; k < 2; k++) {
            // pPow[i] = p^i % m
            pPow[k][0] = 1;
            for(int i = 1; i <= n; i++)
                pPow[k][i] = (pPow[k][i - 1] * p[k]) % m[k];
        }

        // pattern에 대한 해싱값인 pH값을 계산합니다.
        // pH = (pattern[0] * p^(l - 1) + pattern[1] * p^(l - 2) + ... + pattern[l - 1] * 1) % m
        // 소문자 알파벳은 a부터 z까지 순서대로 1부터 26까지의 수와 대응됩니다.
        long[] pH = new long[2];
        for(int k = 0; k < 2; k++) {
            for(int i = 0; i < l; i++)
                pH[k] = (pH[k] + toInt(pattern.charAt(i)) * pPow[k][l - 1 - i]) % m[k];
        }
        
        // text에서 구간 [0, l - 1]에 해당하는 해싱값을 계산합니다.
        long[] tH = new long[2];
        for(int k = 0; k < 2; k++) {
            for(int i = 0; i < l; i++)
                tH[k] = (tH[k] + toInt(text.charAt(i)) * pPow[k][l - 1 - i]) % m[k];
        }

        // 이미 일치한다면 답은 0이 됩니다.
        if(pH[0] == tH[0] && pH[1] == tH[1]) {
            System.out.print(0);
            System.exit(0);
        }

        // text에서
        // 길이가 l인 부분문자열을 전부 잡아보며
        // 해싱값이 일치하는 위치를 찾습니다.
        int ans = -1;
        for(int i = 1; i <= n - l; i++) {
            for(int k = 0; k < 2; k++) {
                // 이전 [i - 1, i + l - 2]에 해당하는 해싱값은 tH에 있습니다.
                // 이전 값(tH)은 (text[i - 1] * p^(l - 1) + text[i] * p^(l - 2) + ... + text[i + l - 2] * 1) % m입니다.
                // 이제 tH * p - text[i - 1] * p^l + text[i + l - 1]를 계산하면
                // 새로 계산을 원하는 해싱값인 (text[i] * p^(l - 1) + text[i + 1] * p(l - 2) + ... + text[i + l - 1] * 1) % m이 됩니다.
                tH[k] = (tH[k] * p[k] - toInt(text.charAt(i - 1)) * pPow[k][l] + toInt(text.charAt(i + l - 1))) % m[k];
                // tH값을 양수로 변환해줍니다.
                if(tH[k] < 0)
                    tH[k] += m[k];
            }

            // 값이 일치한다면 답이 됩니다.
            if(pH[0] == tH[0] && pH[1] == tH[1]) {
                ans = i;
                break;
            }
        }

        System.out.print(ans);
    }
}
*/