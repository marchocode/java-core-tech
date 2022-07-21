## 09-Collection

> JAVA集合相关的整理

定义《集合》这个抽象对象所拥有的一些基本方法，

```java
    boolean add(E e);
    boolean isEmpty();
    boolean contains(Object o);
    ...
```

### Iterable

表示实现这个接口的方法支持迭代,并且默认提供了一个 `forEach` 方法可供使用，传入一个消费者 `Consumer` 
即可使用这个默认的循环迭代方法。

```java
    Iterator<T> iterator();

    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
```

### Iterator

> 真正意义上的迭代器，最基础的迭代器

```java
    boolean hasNext();
    E next();
    void remove();
```

通过不断的访问next方法，可以将所有的元素依次遍历完成。
注意：需要先调用hasNext(); 查看下方是否有元素可以调用。如果有，才可以继续使用next方法进行访问。
否则就会抛出`throw new NoSuchElementException();`

remove() 方法删除上一个通过next()访问到的元素。可以将迭代器理解为存在于每个元素的中间位置。每调用一个next() 方法，就向前走一步，并且返回跨过的元素。

![](https://file.chaobei.xyz/202207202100873.png_imagess)

### ListIterator

ListIterator 作为 Iterator 的子类，又有了几个新的方法。
原有的迭代器只支持向后迭代，ListIterator 支持`向前`迭代。

```java
    boolean hasPrevious();
    E previous();
    ...
```

这对于链表的访问来说非常有益,所以链表LinkedList使用了这个迭代器。

### List

LIST在继承原有的Collection接口的基本上，添加了一些自有的接口定义：

```java
    E get(int index);
    E set(int index, E element);
    int indexOf(Object o);
    ...
```

LIST是一个`有序集合` 即：假设添加的顺序是abcd,那么输出的顺序也应该是abcd.

#### ArrayList

> 以数组的方式实现有序集合。是最常见和最常用的集合。
> 
> 优点：检索很方便，支持随机访问，但是插入和删除操作非常不友好

![](https://file.chaobei.xyz/202207202149711.png_imagess)

初始化如果不指定大小，则默认是一个空的对象数组，没有任何元素，长度为0，

注意：在首次插入一个元素的时候，会进行扩容操作，首次扩容大小为10

```java
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }
```

之后的每次扩容都是增加原有的一半：

```java
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
```

#### LinkedList

> 以双向链表的形式实现有序集合和队列，大小不做限制

![](https://file.chaobei.xyz/202207202215561.png_imagess)

链表有一个好处就是：插入和删除很方便，而检索就很繁琐，必须通过迭代器一个一个找，不能实现`随机访问` 

![](https://file.chaobei.xyz/202207212056167.png_imagess)

`first` 是一个Node类型的引用变量，指向第一个节点，一般C语言或者是数据结构中，将这个节点称作头节点，方便后续的遍历等操作。

`last` 同样也是一个Node类型的引用变量，包含最后一个元素，方便从尾部向前遍历。

```java
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```



#### get(x)

数组实现的ArrayList，其完美支持随机访问，即通过下标x,花费一个常数O(1)量级的时间，即可返回下标的元素。但LinkedList 也提供了一个通过下标来访问的方法：node(x)

```java
    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
```

因为是链表，所以元素的访问只能从初始位置开始，依次向下遍历，不过好的一点是：这里使用了二分法的思想，即对半遍历。

#### listIterator

链表很友好的支持 listIterator 迭代器，既可以向下遍历，也可以向上遍历。

迭代器的位置可以想象为如下所示：初始位置为-1. 每次调用 next 方法即向前移动一位，并且返回跨过位置的元素。

![](https://file.chaobei.xyz/202207212108637.png_imagess)



### Set

> SET  接口等同于Collection接口，但是其插入有着严格的限制
> 
> 集（set）不允许存在重复的元素。如果插入相同的元素会被忽略掉。



```java
    public HashSet() {
        map = new HashMap<>();
    }
```

较为常用的HashSet底层维护了一个HashMap的对象，所有通过`add()` 方法添加的元素都被添加进了 map.keySet

```java
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }
```



### MAP

> MAP又称作是映射，允许你通过某种方式快速的查找到现有的元素。
> 
> 按照函数的定义：一个KEY只能对应一个VALUE完成满射。

例如：

1. KEY-VALUE 键值对的映射

![](https://file.chaobei.xyz/202207212130313.png_imagess)



#### Hash

也可以叫做散列，通过一个散列函数，得到当前对象唯一对应的值，通过这个值，来确定元素的位置。

![](https://file.chaobei.xyz/202207212123432.png_imagess)



> 不过这个值可能会冲突，应该选择合适的hash函数来获得相对分布均匀的hash值。

链表和数组允许你记住元素的位置，即下标。并且元素是有词序的。

但如果你不在意元素的次序和位置，并且需要快速定位元素的位置，那么Hash是个合适的选择。



#### Tree

tree 也可以看作是一种散列集，不过相对于于hash的无序性，tree保持了元素的有序性。

往树上面添加元素，会自动进行排序。

```java
    public TreeSet(Comparator<? super E> comparator) {
        this(new TreeMap<>(comparator));
    }
```

树中的任意两个元素都是可进行比较的，可以在初始化的时候提供一个比较器。



#### HashMap





#### TreeMap
