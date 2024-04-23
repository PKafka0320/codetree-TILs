import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Pair implements Comparable<Pair> { 
    int cnt, num;
    public Pair(int cnt, int num) { 
        this.cnt = cnt; 
        this.num = num; 
    }
    @Override
    public int compareTo(Pair b) {
        if(cnt != b.cnt) return cnt - b.cnt;
        return num - b.num;
    }
}

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, k;
    public static int[] arr = new int[MAX_N];
    public static HashMap<Integer, Integer> freq = new HashMap<>();
    public static ArrayList<Pair> v = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        k = sc.nextInt();

        // 각 숫자가 몇 번씩 나왔는지를
        // hashmap에 기록해줍니다.
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if(!freq.containsKey(arr[i]))
                freq.put(arr[i], 1);
            else
                freq.put(arr[i], freq.get(arr[i]) + 1);
        }

        // hashmap을 순회하며
        // 중복되지 않게 새 배열을 만들어 줍니다.
        for(Integer key : freq.keySet()) {
            v.add(new Pair(freq.get(key), key));
        }

        // 문제에서 요구한 정렬 기준에 맞추어 정렬합니다.
        Collections.sort(v);

        // 출력:
        for(int i = (int)v.size() - 1; i >= (int)v.size() - k; i--)
            System.out.print(v.get(i).num + " ");
    }
}