import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        System.out.println(T);

        StringBuilder ans = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int StringLen = Integer.parseInt(br.readLine());
            String str = br.readLine();

            Stack<Character> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();
            for (char c : str.toCharArray()) {
                if (c == '(')
                    stack.push(c);
                else if (c == ')') {
                    char top = stack.pop();
                    while (top != '(') {
                        sb.append(top);
                        top = stack.pop();
                    }
                } else if (c == '+') { // +와 동등하거나 상위인 연산자가 나올때까지 postfix에 추가
                    char top = stack.peek();
                    while (top == '+' || top == '*') {
                        sb.append(top);
                        stack.pop();
                        top = stack.peek();
                    }
                    stack.push(c); // 연산자 추가
                } else if (c == '*') { // *와 동등하거나 상위인 연산자가 나올때까지 postfix에 추가
                    char top = stack.peek();
                    while (top == '*') {
                        sb.append(top);
                        stack.pop();
                        top = stack.peek();
                    }
                    stack.push(c); // 연산자 추가
                } else
                    sb.append(c); // 숫자 추가
            }

            String postfix = sb.toString();
            Stack<Long> calc = new Stack<>();

            for (char c : postfix.toCharArray()) {
                if (c == '+')
                    calc.push(calc.pop() + calc.pop());
                else if (c == '*')
                    calc.push(calc.pop() * calc.pop());
                else
                    calc.push((long) Character.getNumericValue(c));
            }

            ans.append("#").append(test_case).append(" ").append(calc.pop()).append("\n");
        }

        System.out.println(ans);
    }
}
