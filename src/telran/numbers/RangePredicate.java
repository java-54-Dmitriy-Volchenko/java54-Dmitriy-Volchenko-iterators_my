package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range{
    private Predicate<Integer> predicate;
    
    protected RangePredicate(int min, int max, Predicate<Integer> predicate) {
        super(min, max);   
        this.predicate = predicate;
    }
    
    public static RangePredicate getRange(int min, int max, Predicate<Integer> predicate) {
        checkMinMax(min, max);
        return new RangePredicate(min, max, predicate);//adds state of predicate to instance
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new RangePredicateIterator();
    }
    
    private class RangePredicateIterator implements Iterator<Integer> {
        private int current = min;
        
        public RangePredicateIterator() {
            findNext(); // finding next element without changing state of current in hasNext
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
            while (current <= max) {
                if (predicate == null || predicate.test(current)) {
                    return;
                }
                current++;
            }
        }
    }
}