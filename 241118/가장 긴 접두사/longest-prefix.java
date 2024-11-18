import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String text = br.readLine();
		
		int left = 1, right = text.length();
		int result = 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			
			StringBuffer sb = new StringBuffer(text.substring(0, mid));
			String pattern = sb.reverse().toString();
			
			int[] failure = makeFailure(pattern);
//			System.out.printf("pattern : %s%n", pattern);
//			System.out.printf("failure : %s%n", Arrays.toString(failure));
			
			if (kmp(text, pattern, failure)) {
//				System.out.println("find");
				left = mid + 1;
				result = mid;
			} else {
//				System.out.println("notfind");
				right = mid - 1;
			}
		}

		System.out.println(result);
	}
	
	public static boolean kmp(String text, String pattern, int[] failure) {
		for (int i = 0, j = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = failure[j - 1];
			}
			
			if (text.charAt(i) == pattern.charAt(j)) {
				if (++j == pattern.length()) {
					return true;
				}
			}
		}
		return false;
	}

	public static int[] makeFailure(String text) {
		int[] result = new int[text.length()];

		for (int i = 1, j = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != text.charAt(j)) {
				j = result[j - 1];
			}

			if (text.charAt(i) == text.charAt(j)) {
				result[i] = ++j;
			}
		}

		return result;
	}
}
