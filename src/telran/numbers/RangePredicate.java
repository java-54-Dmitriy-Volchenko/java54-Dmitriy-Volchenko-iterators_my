package telran.numbers;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range {
    private Predicate<Integer> predicate;
    private boolean isPredicateSet = false;

    protected RangePredicate(int min, int max, Predicate<Integer> predicate) {
        super(min, max);
        if (predicate != null) {
            this.predicate = predicate;
            isPredicateSet = true;
        }
    }

    public static RangePredicate getRange(int min, int max, Predicate<Integer> predicate) {
        checkMinMax(min, max);
        return new RangePredicate(min, max, predicate);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangePredicateIterator();
    }

    private class RangePredicateIterator implements Iterator<Integer> {
        private int current = min;

        public RangePredicateIterator() {
            findNext();
        }

        @Override
        public boolean hasNext() {
            return current <= max;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int next = current;
            current++;
            findNext();
            return next;
        }

        private void findNext() {
            if (isPredicateSet) {
                while (current <= max) {
                    if (predicate.test(current)) {
                        return;
                    }
                    current++;
                }
            }
        }
    }
}