import java.util.*;
public class TopSort {
	static ArrayList<Integer> succ = new ArrayList<>(); 
	static ArrayList<Integer> pred = new ArrayList<>(); 
	static ArrayList<ArrayList<Integer>> edges = new ArrayList<>(); 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		topsort();
	}
	static boolean topsort(int n, int k) {
		  // Setup the graph from the first k observations.
		  for (int i = 0; i < n; i++) {
		    succ.set(i, 0);
		    pred.set(i, 0);
		  }
		  for (int i = 0; i < k; i++) {
		    for (Integer p : edges.get(i)) {
		      succ[p.first].push_back(p.second);
		      pred[p.second]++;
		    }
		  }

		  // Initialize the queue with cows that can be first.
		  priority_queue<int> q;
		  for (int i = 0; i < n; i++) {
		    if (pred[i] == 0) {
		      // Use the negative of the ID because we want
		      // to get the min when we pop, but priority_queue
		      // returns the max.
		      q.push(-i);
		    }
		  }

		  for (int i = 0; i < n; i++) {
		    if (q.empty()) {
		      // Nothing in queue - topological sort is impossible.
		      return false;
		    }
		    int v = -q.top();
		    q.pop();

		    result[i] = v;
		    for (int next : succ[v]) {
		      pred[next]--;
		      if (pred[next] == 0) {
		        q.push(-next);
		      }
		    }
		  }

		  return true;
		}
}
