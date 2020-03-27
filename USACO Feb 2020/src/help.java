import java.io.*;
import java.util.*;

public class help { //change to program name
	static String file = "help"; //program name
	public static void main(String[] args) throws IOException{
		Input in = fromFile(file + ".in");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")));
		int n = in.nextInt(); 
		ArrayList<Seg1> s1 = new ArrayList<>(); 
		ArrayList<Seg2> s2 = new ArrayList<>(); 
		for(int i = 0; i<n; i++) {
			int a = in.nextInt(); 
			int b = in.nextInt();
			s1.add(new Seg1(a, b)); 
			s2.add(new Seg2(a, b)); 
		}
		Collections.sort(s1);
		Collections.sort(s2);
		HashMap<Integer, int[]> map = new HashMap<>(); 
//		for(int i = 1; i<=n; i++)
//		{
//			map.put(i, new int[2]); 
//		}
		int cnt = s2.get(0).b; 
		map.put(cnt, new int[2]); 
		int ts = 0; 
		for(int i = 1; i<s2.size(); i++)
		{
			if(s2.get(i).b != cnt) {
				map.get(cnt)[0] = ts; 
				map.get(cnt)[1] = i-1; 
				ts = i; 
				cnt=s2.get(i).b; 
				map.put(cnt, new int[2]); 
			}
		}
		map.put(cnt, new int[] {0, s2.size()-1}); 
		int ans = (int)(Math.pow(2, n)-1); 
		for(int i = 0; i<s1.size(); i++)
		{
			int s = s1.get(i).a; 
			if(map.containsKey(s-1))
			{
				ans += ((int)(Math.pow(2, map.get(s-1)[1]))); 
			}
		}
		pw.println(ans); 
		pw.close(); 
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
				} catch (Exception e) {}
			}
		}
	}
}
class Seg1 implements Comparable<Seg1>
{
	public int a; 
	public int b;
	public Seg1(int x, int y)
	{
		a = x; 
		b = y; 
	}
	public int compareTo(Seg1 o) {
		// TODO Auto-generated method stub
		return a - o.a; 
	} 
	
}
class Seg2 implements Comparable<Seg2>
{
	public int a; 
	public int b;
	public Seg2(int x, int y)
	{
		a = x; 
		b = y; 
	}
	@Override
	public int compareTo(Seg2 o) {
		// TODO Auto-generated method stub
		return b-o.b;
	} 
	
}

