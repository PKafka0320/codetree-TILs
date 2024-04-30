import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Arrays;

class Tuple implements Comparable<Tuple> {
    int pairSum, idx1, idx2;

    public Tuple(int pairSum, int idx1, int idx2) {
        this.pairSum = pairSum;
        this.idx1 = idx1;
        this.idx2 = idx2;
    }

    @Override
    public int compareTo(Tuple t) {
        if(this.pairSum != t.pairSum)
            return this.pairSum - t.pairSum;  // pairSum 기준 오림차순 정렬
        else if(this.idx1 != t.idx1)
            return this.idx1 - t.idx1;        // idx1 기준 오름차순 정렬
        else
            return this.idx2 - t.idx2;        // idx2 기준 오름차순 정렬
    }

    @Override
    public String toString() {
        return "[" + idx1 + "," + idx2 + "]";
    }
}

public class Main {
    public static final int MAX_NUM = 100000;
    
    // 변수 선언
    public static int n, m, k;
    
    public static int[] arr1 = new int[MAX_NUM];
    public static int[] arr2 = new int[MAX_NUM];
    public static PriorityQueue<Tuple> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr1[i] = sc.nextInt();

        for(int i = 0; i < m; i++)
            arr2[i] = sc.nextInt();

        // 주어진 배열을 정렬해줍니다.
        Arrays.sort(arr1, 0, n);
        Arrays.sort(arr2, 0, m);
        
        // 처음에는 n개의 원소에 대해 각각 
        // 두 번째 수열의 첫 번째 원소를 대응시켜줍니다.
        for(int i = 0; i < n; i++)
            pq.add(new Tuple(arr1[i] + arr2[0], i, 0));
        
        // 1번부터 k - 1번까지 합이 작은 쌍을 골라줍니다.
        for(int i = 0; i < k - 1; i++) {
            Tuple bestT = pq.poll();
            int idx1 = bestT.idx1;
            int idx2 = bestT.idx2;

            // 만약 첫 번째 수열의 idx1번째 원소와 더 매칭할 두 번째 수열의 원소가 남아있다면
            // 우선순위 큐에 넣어줍니다.
            idx2++;
            if(idx2 < m)
                pq.add(new Tuple(arr1[idx1] + arr2[idx2], idx1, idx2));
        }

        // k번째 합을 가져옵니다.
        System.out.print(pq.peek().pairSum);
    }
}