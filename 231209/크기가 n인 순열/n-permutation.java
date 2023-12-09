import java.util.*;

public class Main {
    static int n;
    static boolean[] visited;
    static ArrayList<Integer> selected = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n + 1];

        find(0);
    }

    public static void find(int count) {
        if (count >= n) {
            for (int number : selected) {
                System.out.print(number + " ");
            }
            System.out.println();
            return;
        }

        for (int num = 1; num <= n; num++) {
            if (!visited[num]) {
                selected.add(num);
                visited[num] = true;
                find(count + 1);
                selected.remove(selected.size() - 1);
                visited[num] = false;
            }
        }
    }
}