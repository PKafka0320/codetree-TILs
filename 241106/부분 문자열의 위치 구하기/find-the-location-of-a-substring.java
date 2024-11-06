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