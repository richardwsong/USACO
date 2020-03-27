import java.util.Comparator;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "a";
		String b = "a"; 
		System.out.println(a==b); 
	}
	private static class Pair implements Comparator<Pair>{

		@Override
		public int compare(Pair o1, Pair o2) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
