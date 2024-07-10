import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static char[] before, after;
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(reader.readLine()); // 문자열의 길이
		before = reader.readLine().toCharArray(); // 시작 문자열
		after = reader.readLine().toCharArray(); // 목표 문자열
		int count = 0; // 구간의 개수
		int length = 0; // 구간의 길이
		boolean isCounting = false; // 구간 확인중일 때 true
		
		for (int idx = 0; idx < n; idx++) {
			if (before[idx] == after[idx] && isCounting) {
				isCounting = false;
			}
			else if (before[idx] != after[idx]) {
				if (!isCounting || length == 4) {
					count++;
					isCounting = true;
					length = 1;
				}
				else {
					length++;
				}
			}
		}
		
		System.out.println(count);
	}
}