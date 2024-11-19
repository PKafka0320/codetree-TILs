import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static String text;
	static boolean[][] isPos;
	static int[] patternLength;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		text = st.nextToken();
		N = text.length();
		text = "#" + text;
		M = Integer.parseInt(st.nextToken());
		isPos = new boolean[N + 1][M + 1];
		patternLength = new int[M + 1];
		dp = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			String pattern = st.nextToken();
			int patternLen = pattern.length();
			patternLength[i] = patternLen;

			pattern = "#" + pattern;

			int[] failure = makeFailure(pattern, patternLen);

			kmp(pattern, i, failure, patternLen);
		}

		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1];
			for (int j = 1; j <= M; j++) {
				if (isPos[i][j]) {
					dp[i] = Math.max(dp[i], patternLength[j] + dp[i - patternLength[j]]);
				}
			}
		}

		System.out.println(dp[N]);
	}

	public static void kmp(String pattern, int idx, int[] failure, int patternLen) {
		int j = 0;
		for (int i = 1; i <= N; i++) {
			while (j >= 0 && text.charAt(i) != pattern.charAt(j + 1)) {
				j = failure[j];
			}

			if (++j == patternLen) {
				isPos[i][idx] = true;
				j = failure[j];
			}
		}
	}

	public static int[] makeFailure(String pattern, int patternLen) {
		int[] failure = new int[patternLen + 1];

		failure[0] = -1;
		for (int i = 1; i <= patternLen; i++) {
			int j = failure[i - 1];

			while (j >= 0 && pattern.charAt(i) != pattern.charAt(j + 1)) {
				j = failure[j];
			}

			failure[i] = j + 1;
		}

		return failure;
	}
}
