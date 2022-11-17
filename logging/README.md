
## 07-Logging and Exception

### 异常分类

1. Error 错误
    1. 这种很少见，并且一旦出现，程序无法自动处理和恢复
    2. 例如内存耗尽
2. Exception 异常
    1. RuntimeException 如果出现此类问题，那么一定是你的问题，需要尽量避免
    2. IoException
        1. 试图打开一个不存在的文件

### 检查型与非检查型异常

1. 检查型异常
   1. 需要进行捕获的异常，例如Error的派生或者是IoException 的派生
   2. 或者通过声明异常抛出
2. 非检查型异常
   1. 其余派生均为非检查型异常，无需检查

### Throws

不必声明所有可能抛出的所有异常，只需要在以下情况下抛出异常：

1. 调用一个抛出型异常的方法：例如 FileInputStream
2. 检测到一个错误
3. 程序出现错误
4. JAVA虚拟机或者运行时库出现内部错误。

总之：一个方法必须声明所有可能抛出的 检查型异常，非检查型异常 Error 在你的控制之外，无法控制。
运行时异常 RuntimeException 则需要多花点时间避免，而不是抛出。

#### 怎么抛出异常

1. 找到合适自己的异常类
2. 创建这个合适的异常
3. 将对象抛出

### 捕获异常
一般经验：处理那些能够处理的检查型异常，继续传播那些不知道怎么处理的异常

```java
        try {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("test.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        System.out.printf(reader.readLine());
        reader.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
```

try 程序块内发生异常，则停止后续try程序块内的执行，直接跳到执行catch
如果 try程序块内没有发生任何异常，则跳过catch块的执行。


#### finally 子句

finally 子句不管有无错误的出现，都会执行，一般用于清理上下文等。


### Try-catch-resources
实现了 AutoCloseable 接口的资源，提供自动关闭的语法糖