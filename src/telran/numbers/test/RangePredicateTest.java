package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.numbers.RangePredicate;

class RangePredicateTest extends RangeTest{

	@Test
	void iterableIteratorTest() {
	    RangePredicate range = RangePredicate.getRange(10, 15, null); // Передайте null в качестве предиката
	    Integer[] rangeExpected = {10, 11, 12, 13, 14, 15};
	    assertArrayEquals(rangeExpected, toArrayFromIterable(new Integer[rangeExpected.length], range));
	    
	    // Теперь проверяем на четные и нечетные числа с использованием предиката
	    RangePredicate rangeEven = RangePredicate.getRange(1, 7, n -> n % 2 == 0); // Передайте предикат для четных чисел
	    Integer[] rangeEvenExpected = {2, 4, 6};
	    assertArrayEquals(rangeEvenExpected, toArrayFromIterable(new Integer[rangeEvenExpected.length], rangeEven));
	    
	    RangePredicate rangeOdd = RangePredicate.getRange(1, 7, n -> n % 2 != 0); // Передайте предикат для нечетных чисел
	    Integer[] rangeOddExpected = {1, 3, 5, 7};
	    assertArrayEquals(rangeOddExpected, toArrayFromIterable(new Integer[rangeOddExpected.length], rangeOdd));
	}
}