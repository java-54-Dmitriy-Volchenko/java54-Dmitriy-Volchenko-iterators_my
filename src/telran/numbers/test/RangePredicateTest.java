package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.numbers.RangePredicate;

class RangePredicateTest extends RangeTest{

	@Test
	void iterableIteratorTest() {
	    RangePredicate range = RangePredicate.getRange(10, 15, null); 
	    Integer[] rangeExpected = {10, 11, 12, 13, 14, 15};
	    assertArrayEquals(rangeExpected, toArrayFromIterable(new Integer[rangeExpected.length], range));
	    
	
	    RangePredicate rangeEven = RangePredicate.getRange(1, 7, n -> n % 2 == 0);
	    Integer[] rangeEvenExpected = {2, 4, 6};
	    assertArrayEquals(rangeEvenExpected, toArrayFromIterable(new Integer[rangeEvenExpected.length], rangeEven));
	    
	    RangePredicate rangeOdd = RangePredicate.getRange(1, 7, n -> n % 2 != 0); 
	    Integer[] rangeOddExpected = {1, 3, 5, 7};
	    assertArrayEquals(rangeOddExpected, toArrayFromIterable(new Integer[rangeOddExpected.length], rangeOdd));
	}
	@Test
	void iteratorIncorrectUsageTest() {
		RangePredicate rangePredicate = RangePredicate.getRange(1, 9, n -> n % 10 == 0 );
		
		Iterator<Integer> it = rangePredicate.iterator();
		assertThrowsExactly(NoSuchElementException.class, ()->it.next());
	}

}
