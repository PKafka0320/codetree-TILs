import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int flag = 0;
		
		StringBuilder answer = new StringBuilder();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			if (cmd.equals("add")) {
				int x = Integer.parseInt(st.nextToken());
				flag |= (1 << x);
			} else if (cmd.equals("delete")) {
				int x = Integer.parseInt(st.nextToken());
				flag &= ~(1 << x);
			} else if (cmd.equals("print")) {
				int x = Integer.parseInt(st.nextToken());
				answer.append((flag & (1 << x)) == 0 ? 0 : 1).append("\n");
			} else if (cmd.equals("toggle")) {
				int x = Integer.parseInt(st.nextToken());
				flag ^= (1 << x);
			} else if (cmd.equals("clear")) {
				flag = 0;
			}
		}
		
		System.out.println(answer.toString());
	}
}