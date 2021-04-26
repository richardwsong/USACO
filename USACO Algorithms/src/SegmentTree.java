
public class SegmentTree {
	static int n = 10;
	static int[] a = new int[n];
	static int stHeight = (int) Math.ceil(Math.log10(n)/Math.log10(2)); 
	static int maxsize = (int) ((2*Math.pow(2, stHeight))-1) ; //root tree at 1 
	static int[] st = new int[maxsize];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for (int i = 0; i < n; i++) {
//			a[i] = (int)(Math.random() * 20 + 1);
//		}
		
		a = new int[] {13,15,14,12,18,8,6,11,3,8}; 
		build(1, 0, n - 1);
		for(int i = 0; i<n; i++) {
			System.out.print(a[i] + ",");
		}
		System.out.println(); 
		for(int i = 0; i<st.length; i++) {
			System.out.print(st[i] + " ");
		}
		System.out.println(); 
		System.out.println("Min: " + min_query(0, n-1));
		a[8] = 100; 
		update(8, 100); 
		update(6, 100);
		update(9, 100);
		update(5, 100);
		update(7, 100);
		update(3, 100);
		update(0, 100);
		update(2, 100);
		
//		update(6, 1);
		for(int i = 0; i<st.length; i++) {
			System.out.print(st[i] + " ");
		}
		System.out.println(); 
		System.out.println("Min: " + min_query(0, n-1));
	}

	static void build(int p, int l, int r) {
		if (l == r)
			st[p] = a[l];
		else {
			int pl = 2 * p ;
			int pr = 2 * p + 1;
			build(pl, l, (l + r) / 2);
			build(pr, (l + r)/2+1, r);
			st[p] = Math.min(st[pl], st[pr]); // range min query
		}
	}

	static int min_query(int p, int l, int r, int i, int j) {
		if (i > r || j < l)
			return Integer.MAX_VALUE;
		if (l >= i && r <= j)
			return st[p];
		int pl = 2 * p;
		int pr = 2 * p+1;
		int minl = min_query(pl, l, (l + r) / 2, i, j);
		int minr = min_query(pr, (l + r) / 2 + 1, r, i, j);
		return Math.min(minl, minr);
	}

	static int min_query(int l, int r) {
		return min_query(1, 0, n - 1, l, r);
	}

	static void update(int idx, int l, int r, int i, int v) {
		if (l == r)
			st[idx] = v;
		else {
			int m = (l + r) / 2;
			if (i <= m)
				update(2 * idx, l, m, i, v);
			else
				update(2 * idx + 1, m + 1, r, i, v);
			st[idx] = Math.min(st[2 * idx], st[2 * idx + 1]);
		}
	}

	static void update(int i, int v) {
		update(1, 0, n - 1, i, v);
	}
}
