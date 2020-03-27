import java.io.*;
import java.util.*;

public class timeline { // change to program name
	static String file = "timeline"; // program name
	static int n;
	static int m;
	static int c;
	static int[] s;
	static HashMap<Integer, ArrayList<int[]>> edge = new HashMap<>();
	static int[] par;
	

	public static void main(String[] args) throws IOException {
		Input in = fromFile(file + ".in");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")));
		n = in.nextInt();
		m = in.nextInt();
		c = in.nextInt();
		s = new int[n + 1];
		par = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			s[i] = in.nextInt();
			edge.put(i, new ArrayList<int[]>());
		}
		for (int i = 0; i < c; i++) {
			int p = in.nextInt();
			int c = in.nextInt();
			int w = in.nextInt();
			edge.get(p).add(new int[] { c, w });
			par[c] = 1;
		}
		Queue<Integer> q = new LinkedList<>(); 
		for (int i = 1; i <= n; i++) {
			if (par[i] == 0)
				q.add(i); 
		}
		while(!q.isEmpty()) {
			int t = q.poll(); 
			for(int[] i: edge.get(t))
			{
				int a = i[0]; int b = i[1]; 
				int old = s[a];
				s[a] = Math.min(m, Math.max(s[a], s[t] + b)); 
				if(s[a]>old)
					q.add(a); 
			}
		}
		for(int i = 1; i<=n; i++)
		{
			pw.println(s[i]);
		}
		pw.close();

	}
	public static void bfs(int r)
	{
		Queue<Integer> q = new LinkedList<>(); 
		q.add(r); 
		while(!q.isEmpty()) {
			int t = q.poll(); 
			for(int[] i: edge.get(t))
			{
				int a = i[0]; int b = i[1]; 
				s[a] = Math.min(m, Math.max(s[a], s[t] + b)); 
				q.add(a); 
			}
		}
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
