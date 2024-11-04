import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		String input = br.readLine();
		int n = 2 * N + 1;
		char[] inputString = new char[n];
		int[] maxHalfLen = new int[n];

		for (int i = 0; i < N; i++) {
			inputString[2 * i] = '#';
			inputString[2 * i + 1] = input.charAt(i);
		}
		inputString[n - 1] = '#';

		int r = -1, p = -1;
		for (int i = 0; i < n; i++) {
			if (r < i) {
				maxHalfLen[i] = 0;
			} else {
				int ii = 2 * p - i;
				maxHalfLen[i] = Math.min(r - i, maxHalfLen[ii]);
			}
			
			while (i - maxHalfLen[i] - 1 >= 0 && i + maxHalfLen[i] + 1 < n &&
					inputString[i - maxHalfLen[i] - 1] == inputString[i + maxHalfLen[i] + 1]) {
				maxHalfLen[i]++;
			}
			
			if (i + maxHalfLen[i] > r) {
				r = i + maxHalfLen[i];
				p = i;
			}
		}
		
		StringBuilder answer = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int leftStr = Integer.parseInt(st.nextToken());
			int rightStr = Integer.parseInt(st.nextToken());
			int left = 2*leftStr - 1;
			int right = 2*rightStr - 1;
			int mid = (left + right) / 2;
			
			answer.append((2*maxHalfLen[mid]+1)/2 >= rightStr - leftStr ? "Yes\n" : "No\n");
		}
		System.out.println(answer.toString());
		
	}
}