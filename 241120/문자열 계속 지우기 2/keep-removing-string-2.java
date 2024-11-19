import java.io.*;
import java.util.*;

public class Main {
	static String text, pattern;
	static int textLen, patternLen;
	static int[] failure;
	static Queue<Integer> indexs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		text = br.readLine();
		pattern = br.readLine();
		textLen = text.length();
		patternLen = pattern.length();
		indexs = new LinkedList<>();

		text = "#" + text;
		pattern = "#" + pattern;

		makeFailure();
//		System.out.println(Arrays.toString(failure));

		while (kmp()) {
//			System.out.println(indexs);
			while (!indexs.isEmpty()) {
				int idx = indexs.poll();
//				System.out.printf("text: %s -> ", text);
				text = text.substring(0, idx) + text.substring(idx + patternLen);
//				System.out.printf("%s%n", text);
			}
			textLen = text.length() - 1;
		}
		
		System.out.println(text.substring(1));
	}

	public static boolean kmp() {
		boolean deleted = false;
		int j = 0;
		for (int i = 1; i <= textLen; i++) {
			while (j >= 0 && text.charAt(i) != pattern.charAt(j + 1)) {
				j = failure[j];
			}

			if (++j == patternLen) {
				indexs.add(i - patternLen + 1);
				j = 0;
				deleted = true;
			}
		}

		return deleted;
	}

	public static void makeFailure() {
		failure = new int[patternLen + 1];

		failure[0] = -1;
		for (int i = 1; i <= patternLen; i++) {
			int j = failure[i - 1];

			while (j >= 0 && pattern.charAt(i) != pattern.charAt(j + 1)) {
				j = failure[j];
			}

			failure[i] = ++j;
		}
	}
}
