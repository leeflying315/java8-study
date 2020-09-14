# java8-study
 学习异步响应式编程

**HashMap数据结构**

HashMap采用了数组+链表/红黑树数据结构，在链表长度大于8时升级为红黑树，小于六时退化为链表。避免频繁数据结构转换。

HashMap插入数据过程：

1. 判断数组是否为空，为空进行初始化。
2. 不为空，计算 k 的 hash 值，通过`(n - 1) & hash`计算应当存放在数组中的下标 index;
3. 查看 table[index] 是否存在数据，没有数据就构造一个Node节点存放在 table[index] 中；
4. 存在数据，说明发生了hashssz(存在二个节点key的hash值一样), 继续判断key是否相等，相等，用新的value替换原数据(onlyIfAbsent为false)；
5. 如果不相等，判断当前节点类型是不是树型节点，如果是树型节点，创造树型节点插入红黑树中；
6. 如果不是树型节点，创建普通Node加入链表中；判断链表长度是否大于 8， 大于的话链表转换为红黑树；
7. 插入完成之后判断当前节点数是否大于阈值，如果大于开始扩容为原数组的二倍。

**红黑树与平衡二叉树比较**

虽然平衡树解决了二叉查找树退化为近似链表的缺点，能够把查找时间控制在 O(logn)，不过却不是最佳的，因为平衡树要求每个节点的左子树和右子树的高度差至多等于1，这个要求实在是太严了，导致每次进行插入/删除节点的时候，几乎都会破坏平衡树的第二个规则，进而我们都需要通过左旋和右旋来进行调整，使之再次成为一颗符合要求的平衡树。

实际应用中，若搜索的次数远远大于插入和删除，那么选择AVL，如果搜索，插入删除次数几乎差不多，应该选择RB。

**NIO和BIO区别**



**堆外内存优点**

1. 减少垃圾回收工作，因为垃圾回收会暂停其他工作。
2. 加快了复制速度，因为堆内在flush到远程时，会先复制到直接内存（非堆内存），然后在发送；而堆外内存相当于省略掉了这个工作。 福之祸所依，自然也有不好的一面。

**堆外内存缺点**

1. 堆外内存难以控制，如果内存泄漏，那么很难排查 
2. 堆外内存相对来说，不适合存储很复杂的对象。一般简单的对象或者扁平化的比较适合。

**由于堆外内存并不直接控制于JVM，因此只能等到full GC的时候才能垃圾回收！（direct buffer归属的的JAVA对象是在堆上且能够被GC回收的，一旦它被回收，JVM将释放direct buffer的堆外空间。前提是没有关闭**DisableExplicitGC**）**



**堆外内存回收方法**

堆外内存回收的几张方法：

1. Full GC，一般发生在年老代垃圾回收以及调用System.gc的时候，但这样不一顶能满足我们的需求。
2. 调用ByteBuffer的cleaner的clean()，内部还是调用System.gc(),所以一定不要**-XX:+DisableExplicitGC**



1. 减少GC时间
2. 进程间可以共享,减少虚拟机间的复制



**线程不安全含义**

## Spring

**Spring autowired 和resource注解区别**

​	@Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了。@Resource有两个属性是比较重要的，分是name和type，Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。