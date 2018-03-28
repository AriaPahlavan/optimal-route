import java.util.Comparator;
/*
 * Name: Aria Pahlavan
 * EID: AP44342
 */

public class OptimalWeight {
    private int bottleneck;

    public OptimalWeight(Graph G, int source, int dest) {
        Integer[] widestCapTo = new Integer[G.V()];
        Edge[] prev = new Edge[G.V()];

        Comparator<Pair<Integer, Integer>> MAX_HEAP = (o1, o2) -> {
            if (o1.second().equals(o2.second())) return 0;

            return o1.second() > o2.second() ? -1 : 1;
        };
        Heap<Integer> pq = new Heap<>(MAX_HEAP);

        final int MIN = 0;
        for (int v = 0; v < G.V(); v++) {
            widestCapTo[v] = MIN;
            prev[v] = null;
        }

        widestCapTo[source] = Integer.MAX_VALUE;
        pq.insert(source, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {
            int u = pq.poll();

            if (widestCapTo[u] == MIN || u == dest)
                break;

            for (Edge e : G.adjOf(u)) {
                int v = e.dest();
                if (Integer.min(e.capacity(), widestCapTo[u]) > widestCapTo[v]) {
                    widestCapTo[v] = Integer.min(e.capacity(), widestCapTo[u]);
                    prev[v] = e;

                    pq.setOrInsert(v, e.capacity());
                }
            }
        }

        bottleneck = widestCapTo[dest];
        /*while (source != dest) {
            Edge e = prev[dest];
            dest = e.dest(dest);
            System.out.println(e.toString());
            bottleneck = bottleneck > e.capacity()
                    ? e.capacity()
                    : bottleneck;
        }*/
    }

    int bottleneck() {
        return bottleneck;
    }
}