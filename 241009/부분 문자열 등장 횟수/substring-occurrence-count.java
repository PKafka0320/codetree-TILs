import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String text = br.readLine();
		String pattern = br.readLine();
		
		int n = text.length();
		int m = pattern.length();
		text = "#" + text;
		pattern = "#" + pattern;
		
		int[] f = new int[m+1]; // failure function
		
		f[0] = -1;
		for (int idx = 1; idx <= m; idx++) {
			int fIdx = f[idx - 1];
			
			while (fIdx >= 0 && pattern.charAt(fIdx + 1) != pattern.charAt(idx)) {
				fIdx = f[fIdx];
			}
			
			f[idx] = fIdx + 1;
		}
		
		int count = 0;
		int fIdx = 0;
		for (int idx = 1; idx <= n; idx++) {
			while (fIdx >= 0 && pattern.charAt(fIdx + 1) != text.charAt(idx)) {
				fIdx = f[fIdx];
			}
			
			fIdx++;
			
			if (fIdx == m) {
				count++;
				fIdx = f[fIdx];
			}
		}
		
		System.out.println(count);
	}
}