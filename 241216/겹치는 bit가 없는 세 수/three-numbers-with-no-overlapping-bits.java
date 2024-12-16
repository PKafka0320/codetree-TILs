import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int flag = 0;
		int answer = 0;
		for (int i = 0; i < N-2; i++) {
			flag = numbers[i];
			
			for (int j = i+1; j < N-1; j++) {
				if ((flag & numbers[j]) != 0) continue;
				flag |= numbers[j];
				
				for (int k = j+1; k < N; k++) {
					if ((flag & numbers[k]) != 0) continue;
					
					answer = Math.max(answer, numbers[i] + numbers[j] + numbers[k]);
				}
				
				flag &= ~(numbers[j]);
			}
		}
		
		System.out.println(answer);
	}
}
