/*
 * Name: Aria Pahlavan
 * EID: AP44342
 */

/**
 * Representing an edge between two nodes
 */
class Edge {
    private int dest;
    private int travelTime;
    private int capacity;

    Edge(int d, int t, int c) {
        this.dest = d;
        travelTime = t;
        capacity = c;
    }

    public int dest() { return dest; }

    public int time() {
        return travelTime;
    }

    public int capacity() {
        return capacity;
    }

    public String toString() {
        return "" + dest + " w(" + capacity + ')';
    }
}