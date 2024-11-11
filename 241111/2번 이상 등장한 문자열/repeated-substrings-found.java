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

		int POWER = 31, MOD = (int) 1e9 + 7;
		long[] patternPower = new long[patternLength + 1];
		patternPower[0] = 1;
		for (int i = 1; i <= patternLength; i++) {
			patternPower[i] = (patternPower[i - 1] * POWER) % MOD;
		}

		long textHash = 0;
		for (int i = 0; i < patternLength; i++) {
			long hash = toInt(text.charAt(i)) * patternPower[patternLength - 1 - i];
			textHash = (textHash + hash) % MOD;
		}
		if (textHash < 0) {
			textHash += MOD;
		}

		hashes.add(textHash);

		for (int i = patternLength; i < textLen; i++) {
			textHash = ((textHash * POWER) - (toInt(text.charAt(i - patternLength)) * patternPower[patternLength])
					+ toInt(text.charAt(i))) % MOD;
			if (textHash < 0) {
				textHash += MOD;
			}
			
			if (hashes.contains(textHash)) {
				return true;
			}
		}

		return false;
	}

	public static int toInt(char ch) {
		return ch - 'a' + 1;
	}
}