# shensuanzi


HashMap初始化




 HashMap map =new HashMap(16); 
 根据四个构造函数得知：
 放入16，并不是





1、table 的初始化时机是什么时候，初始化的 table.length 是多少、阀值（threshold）是多少，实际能容下多少元素
是在put的时候 有个resize()方法 有初始化和扩容；初始化的长度是16，阀值是12
 
2、什么时候触发扩容，扩容之后的 table.length、阀值各是多少？
默认情况下12， table.length是2倍 阀值也是2倍

3、table 的 length 为什么是 2 的 n 次幂/
减少hash碰撞

4、求索引的时候为什么是：h&(length-1)，而不是 h&length，更不是 h%length/
减少hash碰撞

4、Map map = new HashMap(1000); 当我们存入多少个元素时会触发map的扩容； Map map1 = new HashMap(10000); 我们存入第 10001个元素时会触发 map1 扩容吗


5、为什么加载因子的默认值是 0.75，并且不推荐我们修改
如果太小不停的扩容，性能有问题
如果太大，导致map容量很大，hash冲入的几率更大，冲突的链表也会越来越长
0.75是个折中思想

