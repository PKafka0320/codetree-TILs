import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String input = br.readLine();
		int inputLen = input.length();
		int n = 2*inputLen + 1;
		char[] string = new char[2*inputLen + 1];
		int[] maxHalfLen = new int[n];
		
		for (int i = 0; i < inputLen; i++) {
			string[2*i] = '#';
			string[2*i + 1] = input.charAt(i);
		}
		string[n-1] = '#';
		
		int r = -1, p = -1;
		for (int i = 0; i < n; i++) {
			if (r < i) {
				maxHalfLen[i] = 0;
			} else {
				int ii = 2 * p - i;
				maxHalfLen[i] = Math.min(r - i, maxHalfLen[ii]);
			}
			
			while (i - maxHalfLen[i] - 1 >= 0 && i + maxHalfLen[i] + 1 < n &&
					string[i - maxHalfLen[i] - 1] == string[i + maxHalfLen[i] + 1]) {
				maxHalfLen[i]++;
			}
			
			if (i + maxHalfLen[i] > r) {
				r = i + maxHalfLen[i];
				p = i;
			}
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				answer += maxHalfLen[i] / 2;
			} else if (i % 2 == 1) {
				answer += maxHalfLen[i] / 2 + 1;
			}
		}
		System.out.println(answer);
	}
}