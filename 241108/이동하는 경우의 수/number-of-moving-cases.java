import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		long totalWeight;
		int count;

		public Node(long totalWeight, int count) {
			this.totalWeight = totalWeight;
			this.count = count;
		}
	}

	static class Path {
		int node, weight;

		public Path(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Node[] nodes = new Node[N + 1];
		List<Path>[] edges = new ArrayList[N + 1];
		int[] indegrees = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			nodes[i] = new Node(-1, -1);
		}
		nodes[1] = new Node(0, 0);

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[from].add(new Path(to, weight));
			indegrees[to]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (indegrees[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			Node curNode = nodes[cur];

			for (Path path : edges[cur]) {
				int next = path.node;
				int nextWeight = path.weight;

				if (curNode.totalWeight != -1) {
					Node nextNode = nodes[next];
					if (nextNode.totalWeight == -1 || nextNode.totalWeight < curNode.totalWeight + nextWeight) {
						nextNode.totalWeight = curNode.totalWeight + nextWeight;
						nextNode.count = curNode.count + 1;
					} else if (nextNode.totalWeight == curNode.totalWeight + nextWeight) {
						nextNode.count += curNode.count + 1;
					}

				}
				
				if (--indegrees[next] == 0) {
					queue.add(next);
				}
			}
		}

		Node Nnode = nodes[N];
		System.out.printf("%d %d", Nnode.totalWeight, Nnode.count);
	}
}