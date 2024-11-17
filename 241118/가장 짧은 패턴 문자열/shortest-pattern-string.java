import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String text = br.readLine();

		int[] failure = makeFailure(text);

		int result = 1;
		for (int i = 1; i < text.length(); i++) {
			if (failure[i] == 0) {
				result = i + 1;
			}
		}
		
		System.out.println(result);
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
