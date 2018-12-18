package lrucache.implement.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	int DEFAULT_SIZE = 15;
	Map<Integer, LinkedMapObject> cache;	
	LinkedMapObject head;
	LinkedMapObject tail;
	int maxCacheSize;
	
	/*
	 * Constructor for LRUCache if maxCacheSize is not provided
	 */
	public LRUCache() {
		this.cache = new HashMap(DEFAULT_SIZE);
		this.maxCacheSize = DEFAULT_SIZE;
		head = null;
		tail = null;
	}
	
	/*
	 * Constructor for LRUCache if maxCacheSize is specified
	 */
	public LRUCache(int maxSize) {
		this.cache = new HashMap(maxSize);
		this.maxCacheSize = maxSize;
		head = null;
		tail = null;
	}
	
	/*
	 * Public function to add entry to the cache 
	 */
	public void add(int key, int value) {
		LinkedMapObject newObjectToInsert = new LinkedMapObject(key, value);
		
		if (cache.size() == 0) {			
			addFirstObject(newObjectToInsert);
		} else if (cache.size() >= maxCacheSize ) {			
			removeHead();			
			insertTail(newObjectToInsert);
		} else {
			insertTail(newObjectToInsert);
		}
	}

	/*
	 * Public function to access the entry from the cache
	 */
	public int get(int key) {
		if (!cache.containsKey(key)) {
			return -1;
		} else {
			LinkedMapObject objectToUpdate = cache.get(key);
			
			removeCurrentObject(objectToUpdate);
			cache.remove(key);
			insertTail(new LinkedMapObject(key, objectToUpdate.value));
			return objectToUpdate.value;
		}
	}

	/*
	 * Private function to remove least recently used element
	 */
	private void removeHead() {
		LinkedMapObject nextHead = head.next;
		cache.remove(head.key);
		head = nextHead;
		head.prev = null;		
	}

	/*
	 * Private function to add current element in most recently used place
	 */
	private void insertTail(LinkedMapObject currentElement) {
		cache.put(currentElement.key, currentElement);
		tail.next = currentElement;
		currentElement.prev = tail;
		tail = currentElement;
	}

	/*
	 * Private function to initialize first entry in the cache to both head and tail
	 */
	private void addFirstObject(LinkedMapObject currentElement) {
		head = currentElement;
		tail = currentElement;
		cache.put(currentElement.key, currentElement);
	}
	
	/*
	 * Private function to remove current key from the cache by rearranging prev and next
	 * pointers based on position of the object
	 */
	private void removeCurrentObject(LinkedMapObject objectToUpdate) {
		if (objectToUpdate.equals(head)) {
			removeHead();
		} else if (objectToUpdate.equals(tail)) {
			removeTail();			
		} else {
			removeMiddle(objectToUpdate);			
		}
	}

	/*
	 * Private function to remove middle element
	 */
	private void removeMiddle(LinkedMapObject currentValObject) {
		currentValObject.prev.next = currentValObject.next;
		currentValObject.next.prev = currentValObject.prev;
	}

	/*
	 * Private function to remove tail element
	 */
	private void removeTail() {
		tail = tail.prev;
		tail.next = null;		
	}

	/*
	 * Utility function to check forward ordering between entries in the map
	 */
	public String iterateForward() {
		String res = "";
		LinkedMapObject temp = head;
		while (temp != null) {
			res += " " + temp.key + "->" + temp.value;
			temp = temp.next;
		}
		return res;
	}
	
	/*
	 * Utility function to check backward ordering between entries in the map
	 */
	public String iterateBackward() {
		String res = "";
		LinkedMapObject temp = tail;
		while (temp != null) {
			res += " " + temp.key + "->" + temp.value;
			temp = temp.prev;
		}
		return res;
	}
}
