package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range{
	private Predicate<Integer> predicate;
	
	protected RangePredicate(int min, int max) {
		super(min, max);		
	}
	
	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}
	
	public static RangePredicate getRange(int min, int max) {
		checkMinMax(min, max);
		return new RangePredicate(min, max);
	}
	@Override
	public Iterator<Integer> iterator() {
		return new RangePredicateIterator();
	}
	private class RangePredicateIterator implements Iterator<Integer> {
		  private int current = min;
		@Override
		public boolean hasNext() {
			 while (current <= max) {
	                if (predicate == null || predicate.test(current)) {
	                    return true;
	                }
	                current++;
	            }
	            return false;
		}

		@Override
		public Integer next() {
			   if (!hasNext()) {
	                throw new NoSuchElementException();
	            }
	            return current++;
		}
		
		
	}

}
