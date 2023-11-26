import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        boolean exploded = false;
        do {
            if (arr.length == 0) break;
            int count;
            exploded = false;
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < arr.length; i += count) {
                count = 0;
                for (int j = i; j < arr.length; j++) {
                    if (arr[i] == arr[j]) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (count < m) {
                    for (int j = i; j < i + count; j++) {
                        list.add(arr[j]);
                    }
                } else {
                    exploded = true;
                }
            }
            arr = list.stream().mapToInt(Integer::intValue).toArray();
        } while (exploded);

        System.out.println(arr.length);
        for (int num : arr) {
            System.out.println(num);
        }
    }
}