import java.util.PriorityQueue;
import java.util.function.Function;
/*
 * Name: Aria Pahlavan
 * EID: AP44342
 */

public class OptimalTime {
    private Integer dist;
    public OptimalTime(Graph G, int source, int dest, Function<Edge, Integer> weightOf) {
//        for each vertex v in Graph:
//            dist[v] := infinity
//            previous[v] := undefined ; // Previous node in optimal path from source
//
//        dist[source] := 0
//        Q := the set of all nodes in Graph // All nodes unoptimized - thus are in Q
//
//        while Q is not empty:
//            u := vertex in Q with smallest distance in dist[]
//            if dist[u] = infinity: fixme maybe !=
//                break
//
//            remove u from Q
//
//            if u = sink then:
//                break
//
//            for each neighbor v of u: // where v has not yet been removed from Q
//                altPath := dist[u] + dist between(u, v)
//
//                if altPath < dist[v]: // Relax (u,v,a)
//                    dist[v] := alt
//                    previous[v] := u
//                    decrease-key v in Q // Reorder v in the Queue
//
//         return dist[sink]
        Integer[] distTo = new Integer[G.V()];
        Edge[] prev = new Edge[G.V()];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Integer.MAX_VALUE;
            prev[v] = null;
        }

        distTo[source] = 0;
        pq.add(source);

        while (!pq.isEmpty()) {
            int u = pq.poll();

            if (distTo[u] == Integer.MAX_VALUE)
                break;

            if (u == dest)
                break;

            for (Edge e : G.adjOf(u)) {
                int v = e.dest();

                if (distTo[v] <= distTo[u] + weightOf.apply(e))
                    continue;

                distTo[v] = distTo[u] + weightOf.apply(e);
                prev[v] = e;

                if (!pq.contains(v)) pq.add(v);
            }
        }

        dist = distTo[dest];
    }

    int optimalTime() { return dist; }
}
