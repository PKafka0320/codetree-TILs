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
        
        int totalLength = 0; // 하나도 제외하지 않았을 때 전체 길이
        int prevPosition = -1; // 마지막으로 확인한 위치
        HashSet<Integer> lineNumbers = new HashSet<>(); // 현재 위치에 있는 선분의 번호
        int[] weights = new int[N]; // [i]: i번째 선분을 제외했을 때 빠지게 되는 길이 (가중치)
        
        for (Tuple tuple : points) {
            int count = lineNumbers.size();

            // 현재 위치에 선분의 수가 1개 이상이라면 전체 길이의 합 갱신
            if (count > 0) {
                totalLength += tuple.position - prevPosition;
            }

            // 현재 위치에 선분의 수가 1개라면 해당 선분의 가중치 갱신
            if (count == 1) {
                int targetIndex = new ArrayList<>(lineNumbers).get(0);
                weights[targetIndex] += tuple.position - prevPosition;
            }

            // 시작점인 경우 선분 번호 추가
            if (tuple.value == 1) {
                lineNumbers.add(tuple.index);
            }

            // 끝점인 경우 선분 번호 제거
            else {
                lineNumbers.remove(tuple.index);
            }

            // 이전 위치 갱신
            prevPosition = tuple.position;
        }

        // 특정 선분을 제외했을 때 빠지게 되는 길이 중 최댓값 계산
        int answer = 0;
        for (int idx = 0; idx < N; idx++) {
            answer = Math.max(answer, totalLength - weights[idx]);
        }

        System.out.println(answer);
    }
}