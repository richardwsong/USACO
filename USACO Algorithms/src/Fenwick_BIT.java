import java.io.*;
import java.util.*;
public class Fenwick_BIT {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("bphoto.in"));
		PrintWriter	pw = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
		int n = Integer.parseInt(br.readLine());
		State[] l = new State[n];
		for(int i = 0; i < n; i++) {
			l[i] = new State(Integer.parseInt(br.readLine()), i);
		}
		Arrays.sort(l);
		int ret = 0;
		int seen = 0;
		BIT bit = new BIT(n);
		for(State curr: l) {
			int lhs = bit.query(curr.index);
			int rhs = seen - lhs;
			if(Math.max(lhs, rhs) > 2 * Math.min(lhs, rhs)) {
				ret++;
			}
			bit.update(curr.index, 1);
			seen++;
		}
		pw.println(ret);
		pw.close();
	}
	
	static class State implements Comparable<State> {
		public int height, index;

		public State(int height, int index) {
			super();
			this.height = height;
			this.index = index;
		}
		public int compareTo(State s) {
			return s.height - height;
		}
	}
	
	static class BIT {
		public int[] tree;
		public BIT(int n) {
			tree = new int[n+5];
		}
		public void update(int index, int val) {
			index++;
			while(index < tree.length) {
				tree[index] += val;
				index += index & -index;
			}
		}
		public int query(int index) {
			int ret = 0;
			index++;
			while(index > 0) {
				ret += tree[index];
				index -= index & -index;
			}
			return ret;
		}
	}
	
}