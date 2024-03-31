package lab11.graphs;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private static Stack<Integer> stack = new Stack<>();
    private static ArrayList<Integer> prevPoints;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        int s = 0;
        prevPoints = new ArrayList<>(maze.V());
        for (int i = 0; i < maze.V(); i++) {
            prevPoints.add(0);
        }
        prevPoints.set(s, 0); //the previous point of the start is itself
        stack.push(s);
        dfs(s);

    }

    // Helper methods go here
    private void dfs(int s) {
        marked[s] = true;
        while (!stack.isEmpty()) {
          int v = stack.pop();
          for (int w : maze.adj(v)) {
              if (!marked[w]) {
                  marked[w] = true;
                  prevPoints.set(w, v);
                  stack.push(w);
                  edgeTo[w] = v;
                  announce();
              } else {
                  //if it is marked
                  if (cycleDetect(v, w)) { //v is the current node and w is the next node
                      return;
                  }
              }
          }
      }
    }

    private boolean cycleDetect(int v, int w) {
        // invariant
        int prev = v;
        int current = w;
        if (prevPoints.get(current) != prev) {
            //cycle exist
            edgeTo[current] = prev;
            current = prev;
            while ((prev = prevPoints.get(prev)) != w) {
                edgeTo[current] = prev;
                current = prev;
            }
            edgeTo[current] = prev;
            announce();
            return true;
        }
        return false;
    }
}

