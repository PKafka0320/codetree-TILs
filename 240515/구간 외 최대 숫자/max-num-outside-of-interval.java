import java.io.*;
import java.util.*;

class Number implements Comparable<Number> {
    int number, idx;

    public Number(int number, int idx) {
        this.number = number;
        this.idx = idx;
    }

    @Override
    public int compareTo(Number n) {
        return this.number - n.number;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 숫자의 개수
        int q = Integer.parseInt(tokenizer.nextToken()); // 질의의 개수
        Number[] numbers = new Number[n]; // 숫자 배열
        
        // 숫자 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = new Number(Integer.parseInt(tokenizer.nextToken()), idx);
        }
        
        // 숫자를 크기 순으로 정렬
        Arrays.sort(numbers);
        
        StringBuilder answer = new StringBuilder();
        while (q-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            int idxFrom = Integer.parseInt(tokenizer.nextToken()) - 1;
            int idxTo = Integer.parseInt(tokenizer.nextToken()) - 1;
            
            // 가장 큰 수부터 수의 번호가 범위에 속하는지 확인
            for (int idx = n - 1; idx >= 0; idx--) {
                if (idxFrom <= numbers[idx].idx && numbers[idx].idx <= idxTo) continue;
                answer.append(numbers[idx].number).append("\n");
                break;
            }
        }
        System.out.println(answer);
    }
}