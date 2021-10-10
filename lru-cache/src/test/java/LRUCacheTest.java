import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class LRUCacheTest {
    int cap = 10;
    int count = 12;

    @Test
    public void orderTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(cap);
        for (int i = 0; i < count; i++) {
            cache.put(i, Integer.toString(i));
        }
        ArrayList<Integer> keys = cache.getAllKeys();
        for (int i = 0; i < cap; i++) {
            Assert.assertEquals(cap + 1 - i, (int) keys.get(i));
        }
    }

    @Test
    public void getAndExpandTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(cap);
        for (int i = 0; i < count; i++) {
            cache.put(i, Integer.toString(i));
        }
        Assert.assertNull(cache.get(0));
        Assert.assertNull(cache.get(1));
        Assert.assertEquals("2", cache.get(2));
        Assert.assertEquals("11", cache.get(11));
    }

    @Test
    public void removeTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(cap);
        cache.put(1, "Will exist");
        cache.put(2, "Will exist");
        cache.put(3, "Will be deleted");
        cache.put(4, "Will exist");
        cache.remove(3);
        Assert.assertNull(cache.get(3));
        Assert.assertNotNull(cache.get(4));
    }

    @Test
    public void sizeTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(cap);
        for (int i = 0; i < count; i++) {
            cache.put(i, Integer.toString(i));
            Assert.assertEquals(cache.size(), Math.min(i + 1, cap));
        }
    }

    @Test
    public void capacityTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(cap);
        for (int i = 0; i < count; i++) {
            cache.put(i, Integer.toString(i));
            Assert.assertEquals(cache.cap(), cap);
        }
    }

}
