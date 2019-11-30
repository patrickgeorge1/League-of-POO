package utils;

public class TupleInt {
    private int first;
    private int second;

    public TupleInt(final int first, final int second) {
        this.first = first;
        this.second = second;
    }

    public final int getFirst() {
        return first;
    }

    public final void setFirst(final int first) {
        this.first = first;
    }

    public final int getSecond() {
        return second;
    }

    public final void setSecond(final int second) {
        this.second = second;
    }
}
