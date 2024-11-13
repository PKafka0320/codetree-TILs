import java.io.*;
import java.util.*;

public class Main {
	static int failure[];
	static String text;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		String pattern = br.readLine();
		text = br.readLine();
		text = text + text.substring(0, text.length() - 1);

		makeFailure(pattern);

		System.out.println(kmp(pattern));
	}

	public static void makeFailure(String pattern) {
		failure = new int[pattern.length()];
		int j = 0;

		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = failure[j - 1];
			}

			if (pattern.charAt(i) == pattern.charAt(j)) {
				failure[i] = ++j;
			}
		}
	}

	public static int kmp(String pattern) {
		int count = 0;
		int j = 0;
		
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = failure[j - 1];
			}

			if (text.charAt(i) == pattern.charAt(j)) {
				if (++j == pattern.length()) {
					j = failure[j - 1];
					count++;
				}
			}
		}

		return count;
	}
}
