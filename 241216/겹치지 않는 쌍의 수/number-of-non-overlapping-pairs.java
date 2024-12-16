import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			
			int number = 0;
			for (int j = 0; j < M; j++) {
				int val = Integer.parseInt(st.nextToken());
				number |= (1 << val);
			}
			numbers[i] = number;
		}
		
		int answer = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if ((numbers[i] & numbers[j]) != 0) continue;
				
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}
