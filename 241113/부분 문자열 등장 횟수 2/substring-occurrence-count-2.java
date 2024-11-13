import java.io.*;
import java.util.*;

public class Main {
	static String text;
	static int textLen;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		text = st.nextToken();
		textLen = text.length();
		text = "#" + text;
		
		StringBuilder answer = new StringBuilder();
		int Q = Integer.parseInt(st.nextToken());
		while (Q-- > 0) {
			String pattern = br.readLine();

			answer.append(kmp(pattern)).append("\n");
		}
		System.out.println(answer.toString());
	}

	public static int kmp(String pattern) {
		int count = 0, patternLen = pattern.length();
		pattern = "#" + pattern;

		int[] failure = makeFailure(pattern, patternLen);
//		System.out.println(Arrays.toString(failure));

		int j = 0;
		for (int i = 1; i <= textLen; i++) {
			while (j >= 0 && text.charAt(i) != pattern.charAt(j + 1)) {
				j = failure[j];
			}

			if (++j == patternLen) {
//				System.out.printf("pattern found at %d%n", i);
				count++;
				j = failure[j];
			}
		}

		return count;
	}

	public static int[] makeFailure(String pattern, int patternLen) {
		int[] failure = new int[patternLen+1];
		failure[0] = -1;

		for (int i = 1; i <= patternLen; i++) {
			int j = failure[i - 1];

			while (j >= 0 && pattern.charAt(j + 1) != pattern.charAt(i)) {
				j = failure[j];
			}

			failure[i] = j + 1;
		}

		return failure;
	}
}