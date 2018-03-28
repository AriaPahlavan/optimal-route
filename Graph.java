import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
 * Name: Aria Pahlavan
 * EID: AP44342
 */

public class Graph implements Program2 {
    private final int V;
    private final LinkedList<Edge>[] adj;
    private int E;

    /**
     * Construct a graph
     * Hint: Do you need dest functions here?
     *
     * @param x number of vertices
     */
    public Graph(int x) {
        V = x;
        adj = (LinkedList<Edge>[]) new LinkedList[V];

        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
    }

    /**
     * @return number of vertices in the graph
     */
    public int V() {
        return V;
    }

    /**
     * @return number of edged in the graph
     */
    public int E() {
        return E;
    }

    /**
     * add a new edge to graph
     *
     * @param port1    first port vertex
     * @param port2    second port vertex
     * @param time     time to travel
     * @param capacity max capacity can be moved
     */
    public void inputEdge(int port1, int port2, int time, int capacity) {
        adj[port1].add(new Edge(port2, time, capacity));
        adj[port2].add(new Edge(port1, time, capacity));
        E++;
    }

    /**
     * Solution for the Shortest Path problem
     *
     * @param sourcePort source vertex
     * @param destPort   destination vertex
     * @return the shortest travel travelTime from source port to destination port
     */
    public int findTimeOptimalPath(int sourcePort, int destPort) {
        OptimalTime ot = new OptimalTime(this, sourcePort, destPort, Edge::time);
        return ot.optimalTime();
    }

    /**
     * Solution for the Widest Path problem
     *
     * @param sourcePort source vertex
     * @param destPort   destination vertex
     * @return the maximum capacity from source port to destination port
     */
    public int findCapOptimalPath(int sourcePort, int destPort) {
        OptimalWeight op = new OptimalWeight(this, sourcePort, destPort);
        return op.bottleneck();
    }

    /**
     * @param node a vertex
     * @return the neighboring vertices (i.e. ports) of {@code node}
     */
    public ArrayList<Integer> getNeighbors(int node) {
        return adj[node].stream()
                        .map(edge -> edge.dest())
                        .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @param v the vertex whose adjacency list is being retrieved
     * @return vertices adjacent to {@code v}
     */
    public LinkedList<Edge> adjOf(int v) {
        return adj[v];
    }

    /**
     * @return the number of vertices in the graph
     */
    public int getNumPorts() {
        return V;
    }

    @Override
    public String toString() {
        String adjListStr = IntStream.range(0, V)
                                     .mapToObj(i -> "\t\t(" + i + ')'
                                                    + adj[i].stream()
                                                            .map(v -> "->"+v)
                                                            .reduce(String::concat).orElse("") + '\n')
                                     .reduce(String::concat).orElse("[NO ADJACENCY LIST]");

        return "Graph{" +
               "\n\tV=" + V +
               ", \n\tE=" + E +
               ", \n\tadj=\n" + adjListStr +
               '}';
    }
}
