import java.io.*;
import java.util.*;

public class deleg { // change to program name
	static String file = "deleg"; // program name
	static int n;
	static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
	static HashMap<Integer, ArrayList<Integer>> ch = new HashMap<>();
	static int[] s;

	public static void main(String[] args) throws IOException {
		Input in = fromFile(file + ".in");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")));
		n = in.nextInt();
		for (int i = 1; i <= n; i++) {
			map.put(i, new ArrayList<Integer>());
			ch.put(i, new ArrayList<Integer>());
		}
		for (int i = 0; i < n-1; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			map.get(a).add(b);
			map.get(b).add(a);
		}
		s = new int[n + 1];
		dfs(1, 0);
		for (int i = 1; i <= n-1; i++) {
			pw.print(ok(i));
		}
		pw.println(); 
		pw.close();
	}

	public static void dfs(int c, int p) {
		s[c] = 1;
		for (Integer i : map.get(c)) {
			if (i == p)
				continue;
			dfs(i, c);
			s[c] += s[i];
			ch.get(c).add(s[i]);
		}
		if(s[c] != n) ch.get(c).add(n-s[c]); 
	}
	public static int ok(int k) {
		if ((n-1)%k != 0) return 0;
		int[] score = new int[n+1]; 
		int val = 0; 
		for(int i = 1; i<=n; i++) {
			for(Integer s: ch.get(i)) {
				if(s%k == 0) continue; 
				if(score[k-s%k] != 0) {
					val--; score[k-s%k]--;
				}
				else {
					val++; score[s%k]++;
				}
			}
			if(val != 0) return 0; 
		}
		return 1; 
	}

	private static Input fromFile(String path) throws IOException {
		return new Input(new FileInputStream(new File(path)));
	}

	private static class Input {
		private BufferedReader reader;
		private StringTokenizer stt;

		public Input(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream));
		}

		public String nextLine() {
			try {
				return reader.readLine();
			} catch (IOException e) {
				return null;
			}
		}

		public String nextString() {
			while (stt == null || !stt.hasMoreTokens()) {
				String line = nextLine();
				if (line == null)
					return null;

				stt = new StringTokenizer(line);
			}
			return stt.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(nextString());
		}

		public long nextLong() {
			return Long.parseLong(nextString());
		}

		public String next() {
			return nextString();
		}

		public void close() {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
