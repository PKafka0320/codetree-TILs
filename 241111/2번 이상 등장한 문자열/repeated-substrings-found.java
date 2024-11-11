import java.io.*;
import java.util.*;

public class Main {
	static String text;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		text = br.readLine();

		int maxLen = 0;
		int left = 1, right = text.length();
		while (left <= right) {
			int mid = (left + right) / 2;

			if (patternExist(mid)) {
				left = mid + 1;
				maxLen = mid;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(maxLen);
	}

	public static boolean patternExist(int patternLength) {
		int textLen = text.length();
		Set<Long> hashes = new HashSet<>();

		int[] POWER = {31, 37}, MOD = {(int) 1e9 + 7, (int) 1e9 + 9};
		long[][] patternPower = new long[2][patternLength + 1];
		patternPower[0][0] = patternPower[1][0] = 1;
		for (int k = 0; k < 2; k++) {
			for (int i = 1; i <= patternLength; i++) {
				patternPower[k][i] = (patternPower[k][i - 1] * POWER[k]) % MOD[k];
			}
		}

		long[] textHash = {0, 0};
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < patternLength; i++) {
				long hash = toInt(text.charAt(i)) * patternPower[k][patternLength - 1 - i];
				textHash[k] = (textHash[k] + hash) % MOD[k];
			}
		}

		hashes.add(genUniqueKey(textHash[0], textHash[1], MOD));

		for (int i = 1; i <= textLen - patternLength; i++) {
			for (int k = 0; k < 2; k++) {
				textHash[k] = ((textHash[k] * POWER[k]) - (toInt(text.charAt(i - 1)) * patternPower[k][patternLength])
						+ toInt(text.charAt(i + patternLength - 1))) % MOD[k];
				if (textHash[k] < 0) {
					textHash[k] += MOD[k];
				}
			}
			
			if (hashes.contains(genUniqueKey(textHash[0], textHash[1], MOD))) {
				return true;
			}
			hashes.add(genUniqueKey(textHash[0], textHash[1], MOD));
		}

		return false;
	}
	
    public static long genUniqueKey(long h1, long h2, int[] MOD) {
        return h1 * Math.max(MOD[0], MOD[1]) + h2;
    }

	public static int toInt(char ch) {
		return ch - 'a' + 1;
	}
}