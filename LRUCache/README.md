# LRU Cache System

## Requirements

(IMP)

1. Fixed capactiy cache, based on initialization.
2. It should support the following methods:
   - Put: insert an element (key-value pair) into the cache if the capacity allows, otherwise remove the least recently used and then insert.
   - Get: if the key exists in the cache (hit), return the value associated with the given key and move it to the top. else return -1.
3. Optimised read and writes (ideally constant time)

(NTH)

1. thread-safe
2. allow concurrent usage
