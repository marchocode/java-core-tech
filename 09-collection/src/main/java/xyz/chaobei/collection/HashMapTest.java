package xyz.chaobei.collection;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String[] args) {

        HashMap<Key, Integer> map = new HashMap<>();

        // index=6 hash=118
        map.put(new Key("vishal"), 20);
        // index=3 hash=115
        map.put(new Key("sachin"), 30);
        // index=6 hash=118
        // 这个位置会形成链表
        map.put(new Key("vaibhav"), 40);
        // 再添加一个
        // index=6 hash=118
        map.put(new Key("vecho"), 50);
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        HashMap.Node<K, V>[] tab;
        HashMap.Node<K, V> p;
        int n, i;
        // tab 是方法内部关于节点数组的引用
        if ((tab = table) == null || (n = tab.length) == 0)
            // 首次添加元素的时候需要进行扩容，首次扩容默认大小16
            n = (tab = resize()).length;
        // (i = (n - 1) & hash) 计算出这个元素应该放置到数组的哪个位置。
        if ((p = tab[i = (n - 1) & hash]) == null)

            // 如果计算出的位置是空的，直接放进去，结束
            tab[i] = newNode(hash, key, value, null);
        else {

            // 发现已经有元素存在于数组的某个位置了，表示冲突了。
            // 但不确定仅仅是hash值冲突了，内容值是否也冲突了。

            // 假设："k1"-> 6 “abc” -> 6
            // 表示不仅是hash冲突了，而且键的内容也不一样

            HashMap.Node<K, V> e;
            K k;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k)))) ;
            // 说明原有的key和后面添加的key相同
            // 例如前面添加的是 ("k1","k1") 后一次添加的是("k1","abc")
                e = p;
            else if (p instanceof HashMap.TreeNode)
                // 如果节点已经变成了一棵树，直接添加
                e = ((HashMap.TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
            else {
                // 循环查找
                for (int binCount = 0; ; ++binCount) {

                    // 找到这条链上最后一个元素的位置
                    if ((e = p.next) == null) {
                        // 将新元素添加到尾部
                        p.next = newNode(hash, key, value, null);

                        // TREEIFY_THRESHOLD = 8;
                        // 如果循环次数达到8次，转换为树结构
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }

                    // 在循环查找的时候，找到了与之有相同hash，并且equals也相同的情况。
                    // 直接结束即可。进行后续替换或者不做任何操作结束即可。
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            // 已经存在一个映射的值
            // 这里处理的是：hash相同，并且key.equals(otherKey) == true的情况
            // 就是将新的value替换到旧位置去
            if (e != null) { // existing mapping for key
                V oldValue = e.value;

                // 如果是相同key的情况，后面的新值会替换原有的旧值，并且将旧值进行返回
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
     **/

}
