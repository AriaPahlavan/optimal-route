import java.util.Objects;
/*
 * Name: Aria Pahlavan
 * EID: AP44342
 */

public class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public U second() {
        return second;
    }

    public static <A, B> Pair<A, B> of(A var0, B var1) {
        return new Pair<>(var0, var1);
    }

    public String toString() {
        return "Pair[" + this.first + "," + this.second + "]";
    }

    public boolean equals(Object var1) {
        return var1 instanceof Pair
               && Objects.equals(this.first, ((Pair) var1).first)
               && Objects.equals(this.second, ((Pair) var1).second);
    }

    public int hashCode() {
        if (this.first == null) {
            return this.second == null
                    ? 0
                    : this.second.hashCode() + 1;
        } else {
            return this.second == null
                    ? this.first.hashCode() + 2
                    : this.first.hashCode() * 17 + this.second.hashCode();
        }
    }
}