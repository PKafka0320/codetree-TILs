import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		Stack<Integer> stack = new Stack<>();
		int index = 0;
		int[] postorder = new int[n];
		for (int i = 0; i < n; i++) {
			int number = numbers[i];
			if (stack.isEmpty()) {
				stack.push(number);
			}
			else {
				if (stack.peek() > number) {
					stack.push(number);
				} else {
					int top = stack.pop();
					while (!stack.isEmpty() && stack.peek() < number) {
						postorder[index++] = top;
						top = stack.pop();
					}
					stack.push(top);
					stack.push(number);
				}
			}
        }
		while (!stack.isEmpty()) {
			postorder[index++] = stack.pop();
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < n; i++) {
			answer.append(postorder[i]).append("\n");
		}
		System.out.println(answer);
	}
}