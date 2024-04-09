package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;
import java.util.LinkedList;


public class Solver {
    private final MinPQ<node> minPQ = new MinPQ<>(); //priority queue for A*
    private final LinkedList<WorldState> solutions = new LinkedList<>(); //store every best step
    //to the destination

    private node lastNode;


    private final int currentMoves;

    //define a search node
    private static class node implements Comparable<node> {
        WorldState worldState;
        int moveNumbers;
        node prevState;
        int marked;

        node(WorldState ws, int num, node prev) {
            worldState = ws;
            moveNumbers = num;
            prevState = prev;
            marked = 0;
        }

        @Override
        public int compareTo(node n) {
            int currentScore = moveNumbers + worldState.estimatedDistanceToGoal();
            int otherScore = n.moveNumbers + n.worldState.estimatedDistanceToGoal();
            return (currentScore - otherScore);
        }
    }

    public Solver(WorldState initial) {
        node currentNode = new node(initial, 0, null);
        minPQ.insert(currentNode);
        currentNode.marked += 1; //increase the marked
        while (!currentNode.worldState.isGoal()) {
            currentNode = minPQ.delMin();
            addNeighbours(currentNode);
        }
        lastNode = currentNode;
        currentMoves = currentNode.moveNumbers;
    }

    public int moves() {
        return currentMoves;
    }

    public Iterable<WorldState> solution() {
        node currentNode = lastNode;
        while (currentNode.prevState != null) {
            WorldState currentWord = currentNode.worldState;
            solutions.addFirst(currentWord);
            currentNode = currentNode.prevState;
        }
        solutions.addFirst(currentNode.worldState);
        return solutions;
    }
    /*
    * add neighbours node to the minPQ
     */
    private void addNeighbours(node currentNode) {

        //if it is the first node
        if (currentNode.prevState == null) {
            for (WorldState neighbour : currentNode.worldState.neighbors()) {
                minPQ.insert(new node(neighbour, currentNode.moveNumbers + 1, currentNode));
            }
            return;
        }

        if (currentNode.worldState.neighbors() == null) {
            return;
        }
        for (WorldState neighbour : currentNode.worldState.neighbors()) {
            //critical optimization
            if (neighbour.equals(currentNode.prevState.worldState)) {
                continue;
            }
            minPQ.insert(new node(neighbour, currentNode.moveNumbers + 1, currentNode));
        }
    }

}
