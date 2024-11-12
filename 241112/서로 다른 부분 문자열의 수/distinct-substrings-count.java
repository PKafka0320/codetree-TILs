import java.io.*;
import java.util.*;

public class Main {
	static final int POWER[] = { 31, 37 }, MOD[] = { (int) 1e9 + 7, (int) 1e9 + 9 };
	static String text;
	static int textLen;
	static long powerValue[][], hash[];
	static Set<Long> hashSet;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		text = br.readLine();
		textLen = text.length();
		powerValue = new long[2][textLen + 1];
		hashSet = new HashSet<>();

		for (int k = 0; k < 2; k++) {
			powerValue[k][0] = 1;
			for (int i = 1; i <= textLen; i++) {
				powerValue[k][i] = (powerValue[k][i - 1] * POWER[k]) % MOD[k];
			}
		}

		for (int patternLen = 1; patternLen <= textLen; patternLen++) {
			check(patternLen);
		}
		
		System.out.println(hashSet.size());
	}

	public static void check(int patternLen) {
		hash = new long[2];

		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < patternLen; i++) {
				hash[k] = (hash[k] + toInt(text.charAt(i)) * powerValue[k][patternLen - 1 - i]) % MOD[k];
			}

			if (hash[k] < 0) {
				hash[k] += MOD[k];
			}
		}

		hashSet.add(getHash(hash[0], hash[1]));

		for (int i = patternLen; i < textLen; i++) {
			for (int k = 0; k < 2; k++) {
				hash[k] = (hash[k] * POWER[k]) - (toInt(text.charAt(i - patternLen)) * powerValue[k][patternLen])
						+ toInt(text.charAt(i));
				
				if (hash[k] < 0) {
					hash[k] += MOD[k];
				}
			}
			
			hashSet.add(getHash(hash[0], hash[1]));
		}
	}

	public static long getHash(long hash1, long hash2) {
		return hash1 * Math.max(MOD[0], MOD[1]) + hash2;
	}

	public static int toInt(char ch) {
		return ch - 'a' + 1;
	}
}