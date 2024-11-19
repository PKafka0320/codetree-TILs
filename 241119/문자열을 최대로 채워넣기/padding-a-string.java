import java.io.*;
import java.util.*;

public class Main {
	static boolean[] used;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		String text = st.nextToken();
		used = new boolean[text.length()];
		int M = Integer.parseInt(st.nextToken());
		List<String> patterns = new ArrayList<>(M);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			patterns.add(st.nextToken());
		}

		patterns.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});

		for (String pattern : patterns) {
			int[] failure = makeFailure(pattern);
//			System.out.println(Arrays.toString(failure));

			kmp(text, pattern, failure);
		}

		System.out.println(answer);
	}

	public static void kmp(String text, String pattern, int[] failure) {
		for (int i = 0, j = 0; i < text.length(); i++) {
			if (used[i]) {
				j = 0;
				continue;
			}
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = failure[j - 1];
			}

			if (text.charAt(i) == pattern.charAt(j)) {
				if (++j == pattern.length()) {
//					System.out.printf("make true %d ~ %d%n", i - j + 1, i);
					Arrays.fill(used, i - j + 1, i + 1, true);
					answer += pattern.length();
					j = 0;
				}
			}
		}
	}

	public static int[] makeFailure(String pattern) {
		int[] failure = new int[pattern.length()];

		for (int i = 1, j = 0; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = failure[j - 1];
			}

			if (pattern.charAt(i) == pattern.charAt(j)) {
				failure[i] = ++j;
			}
		}

		return failure;
	}
}
