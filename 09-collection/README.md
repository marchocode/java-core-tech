## 09-Collection
> JAVA集合相关的整理

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

### ListIterator
ListIterator 作为 Iterator 的子类，又有了几个新的方法。
原有的迭代器只支持向后迭代，ListIterator 支持向前迭代。

```java
    boolean hasPrevious();
    E previous();
    ...
```

### Collection
定义《集合》这个抽象对象所拥有的一些基本方法，
```java
    boolean add(E e);
    boolean isEmpty();
    boolean contains(Object o);
    ...
```

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
> 以数组的方式实现有序集合。





#### LinkedList
> 以双向链表的形式实现有序集合和队列


