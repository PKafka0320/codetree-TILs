import java.io.*;
import java.util.*;

class Tuple implements Comparable<Tuple> {
    int position, value, index;
    
    public Tuple(int position, int value, int index) {
        this.position = position;
        this.value = value;
        this.index = index;
    }
    
    @Override
    public int compareTo(Tuple t) {
        return this.position - t.position;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine()); // 선분의 개수
        
        ArrayList<Tuple> points = new ArrayList<>(); // 각 선분의 시작점과 끝점 정보
        
        for (int idx = 0; idx < N; idx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            
            points.add(new Tuple(start, 1, idx));
            points.add(new Tuple(end, -1, idx));
        }
        
        Collections.sort(points);
        
        int answer = 0; // 선분 길이의 최댓값
        for (int except = 0; except < N; except++) {
            int sum = 0; // 제외한 선분 이외의 길이의 합
            int count = 0; // 현재 위치에 있는 선분의 개수
            int startPosition = 0; // 선분의 시작 지점

            for (Tuple tuple : points) {
                if (tuple.index == except) continue;
                
                if (tuple.value == 1) {
                    if (count == 0) {
                        startPosition = tuple.position;
                    }
                    count++;
                }
                else {
                    count--;
                    if (count == 0) {
                        sum += tuple.position - startPosition;
                    }
                }
            }
            answer = Math.max(answer, sum);
        }
        System.out.println(answer);
    }
}