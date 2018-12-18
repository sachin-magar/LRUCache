System Requirements:
1. JRE (1.7 / 1.8)
2. Eclipse(Or any IDE that supports Java)

Project Description:
- Given project is implementation of Least Recently Used (LRU) cache, i.e. when cache is completely filled 
  and user wants to add a new element, new element will be replaced with least recently used element in the 
  cache.
- A least recently used element is element which has been added / accessed least recently.
- Get operation on element is considered to be use on element as well.

Performance Requirements:
- Add and Get operations should run in constant time i.e. O(1)

Implementation Strategy:
- Idea here to accomplish add and get method in constant runtime is to use a HashMap where objects are 
  placed in the map based on the hashcode. 
- To tackle with the requirement of modification on get operation, whenever an existing element is accessed
  that element is removed from the cache and added newly so it becomes most recently used.  
- Use of hashmap guarantees constant time add operation but traditional hashMap does not support
  constant time get operation in this particular scenario.
- To deal with this, a new class LinkedMapObject is created which has prev and next pointers of type 
  LinkedMapObject along with key and value. These pointers are used to store the insertion order.
- The class LRUCache keeps head and tail pointers which are first and last elements added to the cache at 
  any given point respectively.
- The implementation makes sure that head is always least recently used element and tail is always most 
  recently added element.
- When the cache is full and used wants to add a new element, head is removed, new element is added to the 
  cache and made as the new tail.
- If an element is accessed, it is removed from the cache. Before removing the element, it is made sure that
  its previous element points to its next element and its next element points to its previous element. 
  By doing this, access is guaranteed to be a constant time operation as well. 
  
  
  Assumption:
  
  Valid input: In the interest of time, no validations are performed on inputs.
  Persistence requirements: Cache only supports in memory storage.