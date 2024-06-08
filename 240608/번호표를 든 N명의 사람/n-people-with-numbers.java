import java.io.*;
import java.util.*;

class Person implements Comparable<Person> {
    int index;
    long time;
    
    public Person(int index, long time) {
        this.index = index;
        this.time = time;
    }
    
    @Override
    public int compareTo(Person other) {
        return this.time > other.time ? 1 : -1;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 사람의 수
        int T = Integer.parseInt(tokenizer.nextToken()); // 모든 사람이 무대에서 내려갈 때 까지 걸리는 시간의 최댓값
        
        int[] times = new int[N]; // [i]: i번째 사람이 무대 위에 머무르는 시간
        
        for (int idx = 0; idx < N; idx++) {
            times[idx] = Integer.parseInt(reader.readLine());
        }
        
        int min = 1; // 무대 위에 올라올 수 있는 사람 수의 범위 시작
        int max = N; // 무대 위에 올라올 수 있는 사람 수의 범위 끝
        int answer = N; // T를 넘지 않는 시간 내에 모든 사람이 무대를 올라왔다 내려갈 수 있는 무대 수용 인원의 최솟값
        
        while (min <= max) {
            int mid = (min + max) / 2; // 중앙값
//            System.out.printf("check %d\n", mid);
            
            long time = 0;
            int nextPersonIndex = 0;
            boolean cannot = false;
            TreeSet<Person> onStage = new TreeSet<>();
            
            for (int idx = 0; idx < mid; idx++) {
//                System.out.println("person on stage | index: " + nextPersonIndex + ", time: " + (time + times[nextPersonIndex]));
                onStage.add(new Person(idx, times[idx]));
                nextPersonIndex++;
            }
            
            while (!onStage.isEmpty()) {
                Person nextPerson = onStage.pollFirst();
//                System.out.println("current time: " + time);
                
                if (time < nextPerson.time) {
//                    System.out.println("time lapse: " + (nextPerson.time - time));
                    long diff = nextPerson.time - time;
                    time += diff;
                }
                
//                System.out.println("person out stage | index: " + nextPerson.index);
                
                if (time > T) {
//                    System.out.println("time out: " + time);
                    cannot = true;
                    break;
                }
                
                if (nextPersonIndex < N) {
//                    System.out.println("person on stage | index: " + nextPersonIndex + ", time: " + (time + times[nextPersonIndex]));
                    onStage.add(new Person(nextPersonIndex, time + times[nextPersonIndex]));
                    nextPersonIndex++;
                }
            }
            
            if (cannot) {
                min = mid + 1;
            }
            else {
                answer = Math.min(answer, mid);
                max = mid - 1;
            }
//            System.out.println();
        }
        
        System.out.println(answer);
    }
}