package lrucache.implement.LRUCache;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;


public class LRUCacheTest {
	LRUCache lruCache;
	
	@Before	
	public void setup() {
		lruCache = new LRUCache(10);
	}
	
	@Test
	public void testAddFirstElement() {
		lruCache.add(1, 1);
		String expected = " 1->1";
		String actual = lruCache.iterateForward();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetKeyReturnsInvalidValueWhenKeyIsNotPresentInTheCache() {
		int i = lruCache.get(1);
		assertEquals(-1,i);
	}
	
	@Test
	public void testAddElemetWorksBeforeCacheIsFull() {
		lruCache.add(1, 1);
		lruCache.add(2, 2);
		lruCache.add(3, 3);
		lruCache.add(4, 4);
		lruCache.add(5, 5);
		lruCache.add(6, 6);
		lruCache.add(7, 7);
		lruCache.add(8, 8);
		lruCache.add(9, 9);
		String expected = " 1->1 2->2 3->3 4->4 5->5 6->6 7->7 8->8 9->9";
		String actual = lruCache.iterateForward();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testAddElemetsWhenCacheSizeEqualsMaxCacheSize() {
		lruCache.add(1, 1);
		lruCache.add(2, 2);
		lruCache.add(3, 3);
		lruCache.add(4, 4);
		lruCache.add(5, 5);
		lruCache.add(6, 6);
		lruCache.add(7, 7);
		lruCache.add(8, 8);
		lruCache.add(9, 9);
		lruCache.add(10, 10);
		String expected = " 1->1 2->2 3->3 4->4 5->5 6->6 7->7 8->8 9->9 10->10";
		String actual = lruCache.iterateForward();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testNextPointersAreCorrectlySet() {
		lruCache.add(1, 1);
		lruCache.add(2, 2);
		lruCache.add(3, 3);
		lruCache.add(4, 4);
		lruCache.add(5, 5);
		lruCache.add(6, 6);
		lruCache.add(7, 7);
		lruCache.add(8, 8);
		lruCache.add(9, 9);
		lruCache.add(10, 10);
		String expected = " 1->1 2->2 3->3 4->4 5->5 6->6 7->7 8->8 9->9 10->10";
		String actual = lruCache.iterateForward();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testPrevPointersAreCorrectlySet() {
		lruCache.add(1, 1);
		lruCache.add(2, 2);
		lruCache.add(3, 3);
		lruCache.add(4, 4);
		lruCache.add(5, 5);
		lruCache.add(6, 6);
		lruCache.add(7, 7);
		lruCache.add(8, 8);
		lruCache.add(9, 9);
		lruCache.add(10, 10);
		String expected = " 10->10 9->9 8->8 7->7 6->6 5->5 4->4 3->3 2->2 1->1";
		String actual = lruCache.iterateBackward();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testFirstElementGetsRemovedWhenMaxCacheSizeExceeds() {
		lruCache.add(1, 1);
		lruCache.add(2, 2);
		lruCache.add(3, 3);
		lruCache.add(4, 4);
		lruCache.add(5, 5);
		lruCache.add(6, 6);
		lruCache.add(7, 7);
		lruCache.add(8, 8);
		lruCache.add(9, 9);
		lruCache.add(10, 10);
		lruCache.add(11, 11);
		String expected = " 2->2 3->3 4->4 5->5 6->6 7->7 8->8 9->9 10->10 11->11";
		String actual = lruCache.iterateForward();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testRecentlyAccessedElementIsPlacedInTheEnd() {
		lruCache.add(1, 1);
		lruCache.add(2, 2);
		lruCache.add(3, 3);
		lruCache.add(4, 4);
		lruCache.add(5, 5);
		lruCache.add(6, 6);
		lruCache.add(7, 7);
		lruCache.add(8, 8);
		lruCache.add(9, 9);
		lruCache.add(10, 10);
		lruCache.get(2);
		String expected = " 1->1 3->3 4->4 5->5 6->6 7->7 8->8 9->9 10->10 2->2";
		String actual = lruCache.iterateForward();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetWorksForMultipleUpdates() {
		lruCache.add(1, 1);
		lruCache.add(2, 2);
		lruCache.add(3, 3);
		lruCache.add(4, 4);
		lruCache.add(5, 5);
		lruCache.add(6, 6);
		lruCache.add(7, 7);
		lruCache.add(8, 8);
		lruCache.add(9, 9);
		lruCache.add(10, 10);
		lruCache.get(10);
		lruCache.get(9);
		lruCache.get(8);
		lruCache.get(7);
		lruCache.get(6);
		lruCache.get(5);
		lruCache.get(4);
		lruCache.get(3);
		lruCache.get(2);
		lruCache.get(1);
		
		String expected = " 10->10 9->9 8->8 7->7 6->6 5->5 4->4 3->3 2->2 1->1";
		String actual = lruCache.iterateForward();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetAndAddWorksInterleavingly() {
		lruCache.add(1, 1);
		lruCache.add(2, 2);
		lruCache.add(3, 3);
		lruCache.add(4, 4);
		lruCache.add(5, 5);
		lruCache.add(6, 6);
		lruCache.add(7, 7);
		lruCache.add(8, 8);
		lruCache.add(9, 9);
		lruCache.add(10, 10);
		
		lruCache.get(2);
		
		lruCache.add(11, 11);
		
		lruCache.get(3);
		
		lruCache.add(12, 12);
		
		String expected = " 5->5 6->6 7->7 8->8 9->9 10->10 2->2 11->11 3->3 12->12";
		String actual = lruCache.iterateForward();
		assertEquals(expected,actual);
	}
}
