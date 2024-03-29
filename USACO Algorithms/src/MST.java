import java.io.*;
import java.util.*;
public class MST {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		int n = Integer.parseInt(br.readLine());
		int[] x = new int[n];
		int[] y = new int[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		parent = new int[n];
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < n; i++) {
			parent[i] = i;
			for(int j = 0; j < i; j++) {
				int distance = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
				edges.add(new Edge(i, j, distance));
			}
		}
		Collections.sort(edges);
		int lastWeight = 0;
		int numComponents = n;
		for(Edge curr: edges) {
			if(find(curr.i) != find(curr.j)) {
				merge(curr.i, curr.j);
				lastWeight = curr.w;
				if(--numComponents == 1) {
					break;
				}
			}
		}
		pw.println(lastWeight);
		pw.close();
	}
	
	static int[] parent;
	public static int find(int curr) {
		return parent[curr] == curr ? curr : (parent[curr] = find(parent[curr]));
	}
	
	public static void merge(int x, int y) {
		parent[find(x)] = find(y);
	}
	
	static class Edge implements Comparable<Edge> {
		public int i, j, w;
		public Edge(int a, int b, int c){
			i=a;
			j=b;
			w=c;
		}
		public int compareTo(Edge e) {
			return w - e.w;
		}
	}
	
}