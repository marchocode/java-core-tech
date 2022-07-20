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

LIST是一个`有序集合` 即：这个集合可以按照一定的顺序访问，并不是随机出现的，两次迭代的结果是一致的。



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

![](https://file.chaobei.xyz/202207202204744.png_imagess)

`first` 是一个Node类型的引用变量，指向第一个节点，一般C语言或者是数据结构中，将这个节点称作头节点，方便后续的遍历等操作。

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



### Set

> SET  接口等同于Collection接口，但是其插入有着严格的限制
> 
> 集（set）不允许存在重复的元素。需要





### Map








