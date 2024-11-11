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
		while (left < right) {
			int mid = (left + right) / 2;
//			System.out.printf("%d %d : %d%n", left, right, mid);

			if (patternExist(mid)) {
				left = mid + 1;
				maxLen = mid;
			} else {
				right = mid;
			}
		}

		System.out.println(maxLen);
	}

	public static boolean patternExist(int patternLength) {
		int textLen = text.length();
		Map<Long, Integer> counts = new HashMap<>();

		int POWER = 31, MOD = (int) 1e9 + 7;
		long[] patternPower = new long[patternLength + 1];
		patternPower[0] = 1;
		for (int i = 1; i <= patternLength; i++) {
			patternPower[i] = (patternPower[i - 1] * POWER) % MOD;
		}
//		System.out.println(Arrays.toString(patternPower));

		long textHash = 0;
		for (int i = 0; i < patternLength; i++) {
			long hash = toInt(text.charAt(i)) * patternPower[patternLength - 1 - i];
			textHash = (textHash + hash) % MOD;
		}
		if (textHash < 0) {
			textHash += MOD;
		}
//		System.out.printf("init hash: %d%n", textHash);

		counts.put(textHash, 1);

		for (int i = patternLength; i < textLen; i++) {
			textHash = ((textHash * POWER) - (toInt(text.charAt(i - patternLength)) * patternPower[patternLength])
					+ toInt(text.charAt(i))) % MOD;
			if (textHash < 0) {
				textHash += MOD;
			}
//			System.out.printf("hash: %d%n", textHash);

			int hashCount = counts.getOrDefault(textHash, 0);
			counts.put(textHash, hashCount + 1);

			if (hashCount + 1 > 1) {
				return true;
			}
		}

		return false;
	}

	public static int toInt(char ch) {
		return ch - 'a' + 1;
	}
}