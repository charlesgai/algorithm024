//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。 
//
// 
// 
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
// 
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 3000 
// 0 <= value <= 104 
// 最多调用 3 * 104 次 get 和 put 
// 
// Related Topics 设计 
// 👍 1298 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    /**
     * 定义缓存节点
     */
    class CacheNode {
        int key;
        int value;

        CacheNode prev;
        CacheNode next;

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 利用 HashMap 实现 O(1) 时间获取元素
    private Map<Integer, CacheNode> cache = new HashMap<>();
    // 当前大小
    private int size;
    // 缓存容量
    private int capacity;
    // 双向链表头尾指针，命中元素移动到尾部，淘汰头部节点
    private CacheNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new CacheNode(-1, -1);
        tail = new CacheNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        CacheNode cacheNode = cache.get(key);
        if(cacheNode == null) {
            return -1;
        }

        // key 存在，最近使用，需要移动到头部
        moveToTail(cacheNode);

        return cacheNode.value;
    }
    
    public void put(int key, int value) {
        CacheNode cacheNode = cache.get(key);

        // 缓存没有
        if(cacheNode == null) {
            CacheNode newCache = new CacheNode(key, value);
            cache.put(key, newCache);

            addToTail(newCache);
            size ++;
            // 超过缓存容量，需要淘汰头部节点
            if(size > capacity) {
                CacheNode tail = removeHead();
                // 淘汰节点
                cache.remove(tail.key);
                size --;
            }
        } else {
            // 存在，修改 value，并且最近使用，移动到头部
            cacheNode.value = value;

            moveToTail(cacheNode);
        }
    }

    // 添加新节点到尾部
    private void addToTail(CacheNode cacheNode) {
        // 添加到尾部
        tail.prev.next = cacheNode;
        cacheNode.prev = tail.prev;
        cacheNode.next = tail;
        tail.prev = cacheNode;
    }

    // 将节点移动到尾部
    private void moveToTail(CacheNode cacheNode) {
        // 移除当前节点
        removeNode(cacheNode);

        // 添加到尾部
        addToTail(cacheNode);
    }

    private void removeNode(CacheNode cacheNode) {
        cacheNode.prev.next = cacheNode.next;
        cacheNode.next.prev = cacheNode.prev;
    }

    // 淘汰头部节点，一般超过容量才会淘汰，所以先不考虑空指针问题
    private CacheNode removeHead() {
        CacheNode node = head.next;
        removeNode(node);
        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
