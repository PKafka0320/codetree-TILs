import java.io.*;
import java.util.*;

public class Main {
	static String text, pattern;
	static int[] failure;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		text = br.readLine();
		pattern = br.readLine();

		makeFailure();

		System.out.println(kmp());
	}

	public static int kmp() {
		int count = 0;
		
		int j = 0;
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = failure[j - 1];
			}
			
			if (text.charAt(i) == pattern.charAt(j)) {
				if (++j == pattern.length()) {
					count++;
					j = 0;
				}
			}
		}
			
		return count;
	}

	public static void makeFailure() {
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
}
