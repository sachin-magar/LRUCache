package lrucache.implement.LRUCache;

public class LinkedMapObject {
	int key;
	int value;
	LinkedMapObject prev;
	LinkedMapObject next;
	
	/*
	 * Constructor to create LinkedMapObject from key and value
	 */
	public LinkedMapObject(int key, int value) {
		this.key = key;
		this.value = value;
		this.prev = null;
		this.next = null;
	}
	
	/*
	 * Function to compare the objects based on key
	 */
	public boolean equals(LinkedMapObject a, LinkedMapObject b) {
		return a.value == b.value;
	}
}
