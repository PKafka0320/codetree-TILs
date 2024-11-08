import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		String S = st.nextToken();

		int p = 31, m = (int) 1e9 + 7;
		long[] pPow = new long[L + 1];
		pPow[0] = 1;
		for (int i = 1; i <= L; i++) {
			pPow[i] = (pPow[i - 1] * p) % m;
		}

		Map<Long, Integer> counts = new HashMap<>();

		long tH = 0;
		for (int i = 0; i < L; i++) {
			long hash = toInt(S.charAt(i)) * pPow[L - i - 1];
			tH = (tH + hash) % m;
		}
		counts.put(tH, counts.getOrDefault(tH, 0) + 1);

		for (int i = L; i < S.length(); i++) {
			tH = ((tH * p) - (toInt(S.charAt(i - L)) * pPow[L]) + toInt(S.charAt(i))) % m;
			
			if (tH < 0) {
				tH += m;
			}
			counts.put(tH, counts.getOrDefault(tH, 0) + 1);
		}
		
		int max = 0;
		for (long value : counts.keySet()) {
			max = Math.max(max, counts.get(value));
		}
		System.out.println(max);
	}

	public static int toInt(char ch) {
		return ch - 'a' + 1;
	}
}