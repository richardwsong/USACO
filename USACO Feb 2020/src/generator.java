import java.io.*;
import java.util.*;

public class generator { //change to program name
	static String file = "generator"; //program name
	public static void main(String[] args) throws IOException{
//		Input in = fromFile(file + ".in");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")));
		int r = 650; 
		int c = 650; 
		int k = 40; 
		int p = 5000; 
		HashSet<Integer> hs = new HashSet<>(); 
		pw.println(r + " " + c + " " + k); 
		for(int i = 0; i<r; i++)
		{
			for(int j = 0; j<c; j++) {
				int rand = (int)(Math.random()*k + 1);
				hs.add(rand); 
				pw.print(rand + " "); 
			}
			pw.println(); 
		}
		
		pw.println(p); 
		for(int i = 0; i<p; i++) {
			int r1 = (int)(Math.random()*r+1); 
			int c1 = (int)(Math.random()*c+1); 
			int r2 = (int)(Math.random()*r+1); 
			int c2 = (int)(Math.random()*c+1); 
			pw.println(r1 + " " + c1 + " " + r2 + " " + c2);
		}
		System.out.println(hs.size()); 
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

