## 12-Thread
Java线程


### 多线程程序
一个程序运行多个线程，则称之为这个程序是多线程的

### 进程与线程

- 进程拥有自己一整套的变量，而线程则共享数据。
- 线程更加轻量，共享变量使得线程之间更加容易通信
- 线程开销更小。

### 线程的状态

- NEW 新建
- Runnable 可运行
- Blocked 阻塞
- Waiting 等待
- TimedWaiting 计时等待
- Terminated 终止

```java
public enum State {
        NEW,
        RUNNABLE,
        BLOCKED,
        WAITING,
        TIMED_WAITING,
        TERMINATED;
    }
```
![](https://file.chaobei.xyz/202208071807113.png_imagess)

1. 新建后的线程处于 NEW 状态
2. 调用了线程`Thread`实例的 `start()` 方法，变成`Runnalbe`状态.
   1. 虽然状态变为可运行，但是线程不会立马运行。
   2. 等到操作系统分配CPU时间片给线程的时候，才会执行run方法内的代码。
3. 如果线程需要获得一个锁，一般是对象的内部锁，另外一个线程正在占用的时候，当前线程会被阻塞等待。
4. 等到获得需要的锁之后，又转换为 `Runnable` 状态。
5. 在同步方法块内调用wait() 方法后，会丢失持有的锁，并且当前线程进入 `Waiting` 状态。等待其他线程调用 `notify()` 方法通知启动，否则将会永远等待下去。
6. 计时等待wait(1000L)，给定一个时间等待被通知，如果在给定的时间超时之前还未获得通知，就自动转入 `Runnable`
7. 线程终止 `Terminated` 执行完 `run()` 方法块内的内容后，这个线程将是此状态，或者因为一个未处理的异常，线程也会终止运行。

### Thread类

- void join()
  - 让主线程等待这个线程的完成，再调用后面的代码。理解为插个队 `join()`
- void join(long mills)
  - 给定一个超时时间，如果这个时间内还没执行完，那就不等你了。
- run()
  - 线程需要指定的内容
- start() 
  - 启动线程的方法
- boolean isInterrupted()
  - 判断当前线程是否被设置了中断，如果设置了返回true
- void interrupt()
  - 设置中断

### 线程的中断
如果想让一个持续运行的线程停止运行的话，只能通过设置中断状态来实现。

Thread类的实例方法 `interrupt()` 用于给当前线程设置中断标志，以便于其他位置用于检查这个标志而停止线程。

```java
    if (i == 77) {
        // try stop thread
        Thread.currentThread().interrupt();
    }
```

但是Thread类有一个静态的 `interrupted()` 方法，这个方法也是检查当前线程是否有中断标志。
但是有一个副作用：就是清除掉当前进程的 `中断标志`

```java
    public static boolean interrupted() {
        return currentThread().isInterrupted(true);
    }
```

```java
    /**
     * Tests if some Thread has been interrupted.  The interrupted state
     * is reset or not based on the value of ClearInterrupted that is
     * passed.
     */
    private native boolean isInterrupted(boolean ClearInterrupted);
```

### 守护进程

服务进程，给其他线程提供服务，设置标志位让其作为守护进程。
如果JAVA虚拟机内只剩下守护进程，JAVA虚拟机会自动退出。不管守护进程有没有执行完毕。

```java
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
```


### 线程异常处理
用于处理线程的异常，线程不允许抛出检查型异常。但是可能会抛出非检查型异常。

- Thread.static void setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh)
  - 给线程设置一个默认的异常处理

```java
    @FunctionalInterface
    public interface UncaughtExceptionHandler {
        /**
         * Method invoked when the given thread terminates due to the
         * given uncaught exception.
         * <p>Any exception thrown by this method will be ignored by the
         * Java Virtual Machine.
         * @param t the thread
         * @param e the exception
         */
        void uncaughtException(Thread t, Throwable e);
    }
```

如果没有给当前线程设置线程异常处理器的话，就使用了group,group的实现如下：
group有父组的概念，如果新生成一个线程，它自动增加到 `java.lang.ThreadGroup[name=main,maxpri=10]`

默认的线程组顺序如下：
** main -> system -> null**

```java
public void uncaughtException(Thread t, Throwable e) {
    if (parent != null) {
        parent.uncaughtException(t, e);
    } else {
        Thread.UncaughtExceptionHandler ueh =
            Thread.getDefaultUncaughtExceptionHandler();
        if (ueh != null) {
            ueh.uncaughtException(t, e);
        } else if (!(e instanceof ThreadDeath)) {
            System.err.print("Exception in thread \""
                             + t.getName() + "\" ");
            e.printStackTrace(System.err);
        }
    }
}
```

如果没有给 `Thread` 类添加一个默认的处理器，那么直接使用 `ThreadGroup`类作为默认的异常处理器。


### 线程优先级
默认优先级5，从1-10之间，但现在不推荐使用了，就算设置了高优先级，操作系统也不一定会先调用它。

### 同步
非同步造成数据丢失,根本原因是: 一条代码的指令并不是原子操作，所以指令中间可能会被分解。
导致中间的操作被抹除掉。

```java
        accounts[from] -= amount;
        System.out.printf("name=%s,amount %10.2f,from %d,to %s\n", threadName, amount, from, to);
        accounts[to] += amount;
```


```java
name=Thread-66,amount      27.00,from 0,to 2
name=Thread-66,list=[15510.0, 24447.0, 17865.0, 3958.0, 18638.0, 2695.0, 3979.0, 2232.0, 273.0, 9600.0]
name=Thread-66,System Total Amount Is:  99197.00
```

![](https://file.chaobei.xyz/202208102246416.png_imagess)

### 锁对象

#### 重入锁 ReentrantLock
每一次线程对锁的引用，首先要调用 `lock()` 获得锁，并且在用完之后进行 `unlock()`

```java
try {
            lock.lock();
            // do something...
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
```

#### 条件锁 Conditional Lock
通常用于管理一个进入临界区，表示一个线程已经获得了对应的锁（可以进行下一步的转账操作），但是它没有足够的余额（需要等待其他线程把钱汇入后）才能继续执行
这个时候就需要条件锁：

1. 进入临界区后，如果没有满足条件，就通过条件调用 `await()` 将自生持有的锁释放给其他线程，自己进入阻塞。
2. 其他的线程获得锁后执行，在完成任意一次转账操作后，让所有等待的线程（在等待集）中的线程，全部成为 `可运行状态` 的线程
3. 调度器从众多线程中选择一个，让其获得锁。
   1. 如果恰好刚才经历过等待的线程获得了锁，它会从刚才的 `await()` 方法返回，并且继续执行。
   
- await() 方法让当前线程失去锁，进入等待集
- signal() 从等待集中任意挑选一个并移动到 `可运行`
- signalAll() 将所有的等待线程赋予 `可运行`

**注意：上面三个方法，必须在当前线程持有锁的情况下才可以调用。**
其实和Object类的 `wait()` 方法以及`notify()` 方法类似。

### Synchronized 关键字
JAVA对象从1.0版本开始,都持有一个 `内部锁` 在方法上加入 `synchronized` 关键字，则保护整个方法块，并且
线程要调用整个方法块，必须获得对象的 `内部锁`. `内部锁` 也持有一个内部条件。和`Condition` 类似。 

```java
public void method() {
        try {
            lock.lock();
            // do something...
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }    
}
```

```java
public synchronized void method() {
    // do something...
}
```

**注意：如果对一个静态方法加入 `Synchronized` 关键字也是合法的，但是会获得`Bank.class` 对象的锁**
一个类的Class也是一个对象，静态方法的 `Synchronized` 会获得Class锁。
导致如果一个线程获得了某一个对象的Class锁，其他线程就不能调用该类的其他同步方法。

#### Synchronized Block

```java
public void test() {
    
    synchronized (obj) {
        // do something    
    }
    
}
```

#### 监视器
JAVA中的每一个对象都持有一个 `内部锁` 和 `内部条件`。如果一个方式使用 `Sychronized` 关键字声明，则它就表现的像一个监视器。
通过调用：

1. wait() 当前持有锁的线程放弃这个锁，并且进入等待
2. notify() 唤醒任意一个等待的线程，使其可以在下一次调度中具备获得锁的能力`可运行`。
3. notifyAll()

#### Volatile字段
为实例字段提供一种免锁同步的访问机制。但 volatile 变量不能保证原子性。
如果对共享变量除了赋值之外，不做任何操作，可以将这些变量声明为 `Volatile`

### 原子性
