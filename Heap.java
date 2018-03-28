import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Name: Aria Pahlavan
 * EID: AP44342
 */

public class  Heap<T>{
    private PriorityQueue<Pair<T, Integer>> heap;
    private Map<T, Integer> keyValueMap;

    public Heap(Comparator<Pair<T, Integer>> c) {
        heap = new PriorityQueue<>(c);
        keyValueMap = new HashMap<>();
    }

    public void insert(T key, Integer value) {
        heap.add(Pair.of(key, value));
        keyValueMap.put(key, value);
    }

    public void setOrInsert(T key, Integer value) {
        if (keyValueMap.containsKey(key)) {
            int prevVal = keyValueMap.replace(key, value);
            heap.remove(Pair.of(key, prevVal));
            heap.add(Pair.of(key, value));
        } else {
            keyValueMap.put(key, value);
            heap.add(Pair.of(key, value));
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public T poll() {
        T top = heap.poll().first();
        keyValueMap.remove(top);
        return top;
    }
}
