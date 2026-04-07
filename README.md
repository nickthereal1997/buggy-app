# Buggy App

## 介绍

Buggy App 是一个用于 JVM 性能问题教学演示的 Java 应用程序，可以模拟各种常见的性能问题，帮助开发者学习和实践 JVM 调优、故障排查和监控技术。

### 可模拟的问题类型

| 类别 | 参数 | 说明 |
|------|------|------|
| 正常示例 | `sample` | 生产者-消费者模式 |
| **内存问题** | `bug1` / `PROBLEM_OOM` | HashMap 无限增长导致 OOM |
| | `bug1.1` | 线程内存泄漏 |
| | `bug1.2` / `PROBLEM_MEMORY` | 高内存占用但不 OOM |
| | `bug9` / `PROBLEM_OOMCRASH` | OOM 崩溃 |
| | `bug9.1` | OOM 但不崩溃（捕获异常） |
| | `bug9.2` | 超大数组 OOM |
| | `bug10` / `PROBLEM_METASPACE` | Metaspace 泄漏 |
| **CPU 问题** | `bug3` / `PROBLEM_CPU` | CPU 飙升 |
| **线程问题** | `bug4` / `PROBLEM_THREADLEAK` | 线程泄漏 |
| | `bug5` / `PROBLEM_BLOCKED` | 线程阻塞 |
| | `bug6` / `PROBLEM_DEADLOCK` | 死锁 |
| **栈问题** | `bug7` / `PROBLEM_STACKOVERFLOW` | 栈溢出 |
| **I/O 问题** | `bug8` / `PROBLEM_IO` | I/O 密集型操作 |
| **Finalizer** | `bug2` | 慢速 finalize |
| **集合性能** | `bug11` / `PROBLEM_LIST` | List 无初始容量 |
| | `bug11.1` | List 有初始容量 |
| | `bug11.2` | 数组性能对比 |
| **代码效率** | `bug12` / `PROBLEM_EFFICIENT` | 高效代码示例 |
| | `bug12.1` | 低效代码示例 |
| | `bug12.2` | 随机低效代码 |
| **引用类型** | `bug13` / `PROBLEM_REFERENCES` | 对象引用链 |

---

## 快速开始

### 编译项目

```bash
# 编译并打包
mvn clean package
```

打包后的 JAR 文件位于 `target/buggy-app-1.0.0.jar`

### 运行方式

```bash
java -jar target/buggy-app-1.0.0.jar <参数>
```

---

## 使用指南

### 1. 正常示例 (sample)

生产者-消费者模式示例，展示正常的多线程协作。

```bash
java -Xmx256m -jar target/buggy-app-1.0.0.jar sample
```

### 2. 内存泄漏导致 OOM (bug1 / PROBLEM_OOM)

HashMap 无限增长，最终导致 `OutOfMemoryError`。

```bash
java -Xmx512m -jar target/buggy-app-1.0.0.jar bug1
# 或
java -Xmx512m -jar target/buggy-app-1.0.0.jar PROBLEM_OOM
```

**排查工具**：
```bash
# 查看堆内存使用
jmap -heap <pid>

# 生成堆转储文件
jmap -dump:format=b,file=heap.hprof <pid>

# 分析堆转储
jhat heap.hprof
```

### 3. 线程内存泄漏 (bug1.1)

在线程中创建 HashMap 并无限增长。

```bash
java -Xmx512m -jar target/buggy-app-1.0.0.jar bug1.1
```

### 4. 高内存占用但不 OOM (bug1.2 / PROBLEM_MEMORY)

智能控制内存占用在 80-90%，不会触发 OOM，模拟"慢性"内存问题。

```bash
java -Xmx512m -jar target/buggy-app-1.0.0.jar PROBLEM_MEMORY
```

### 5. Finalizer 问题 (bug2)

创建带有慢速 `finalize()` 方法的对象，导致 Finalizer 线程堆积。

```bash
java -Xmx512m -jar target/buggy-app-1.0.0.jar bug2
```

### 6. CPU 飙升 (bug3 / PROBLEM_CPU)

启动 6 个线程进行无限空转，CPU 使用率飙升至 100%。

```bash
java -Xmx256m -jar target/buggy-app-1.0.0.jar bug3
# 或
java -Xmx256m -jar target/buggy-app-1.0.0.jar PROBLEM_CPU
```

**排查工具**：
```bash
# 查看 CPU 使用率
top -H -p <pid>

# 查看线程状态
jstack <pid> | grep "java.lang.Thread.State"
```

### 7. 线程泄漏 (bug4 / PROBLEM_THREADLEAK)

无限创建线程，每个线程休眠 10 分钟不退出。

```bash
java -Xmx1g -jar target/buggy-app-1.0.0.jar bug4
# 或
java -Xmx1g -jar target/buggy-app-1.0.0.jar PROBLEM_THREADLEAK
```

**排查工具**：
```bash
# 查看线程数
jstack <pid> | grep -c "java.lang.Thread.State"

# 查看线程详情
jstack <pid> > threads.txt
```

### 8. 线程阻塞 (bug5 / PROBLEM_BLOCKED)

启动 10 个线程，全部进入 BLOCKED 状态，模拟应用无响应。

```bash
java -Xmx1g -jar target/buggy-app-1.0.0.jar bug5
# 或
java -Xmx1g -jar target/buggy-app-1.0.0.jar PROBLEM_BLOCKED
```

**排查工具**：
```bash
# 查看 BLOCKED 线程
jstack <pid> | grep -A 1 "java.lang.Thread.State: BLOCKED"
```

### 9. 死锁 (bug6 / PROBLEM_DEADLOCK)

经典的双线程死锁场景：ThreadA 持有锁 A 请求锁 B，ThreadB 持有锁 B 请求锁 A。

```bash
java -Xmx1g -jar target/buggy-app-1.0.0.jar bug6
# 或
java -Xmx1g -jar target/buggy-app-1.0.0.jar PROBLEM_DEADLOCK
```

**排查工具**：
```bash
# jstack 会自动检测死锁
jstack -l <pid> | grep -A 30 "Found one Java-level deadlock"
```

### 10. 栈溢出 (bug7 / PROBLEM_STACKOVERFLOW)

无限递归调用，导致 `StackOverflowError`。

```bash
# 使用较小的栈空间，更快触发错误
java -Xmx256m -Xss128k -jar target/buggy-app-1.0.0.jar bug7

# 使用较大的栈空间，运行更久
java -Xmx256m -Xss1m -jar target/buggy-app-1.0.0.jar bug7
```

### 11. I/O 问题 (bug8 / PROBLEM_IO)

模拟 I/O 密集型操作。

```bash
java -jar target/buggy-app-1.0.0.jar bug8
# 或
java -jar target/buggy-app-1.0.0.jar PROBLEM_IO
```

### 12. OOM 崩溃 (bug9 / PROBLEM_OOMCRASH)

无限 HashMap 增长导致 OutOfMemoryError，应用崩溃退出。

```bash
java -Xmx512m -jar target/buggy-app-1.0.0.jar bug9
# 或
java -Xmx512m -jar target/buggy-app-1.0.0.jar PROBLEM_OOMCRASH
```

### 13. OOM 但不崩溃 (bug9.1)

捕获 OutOfMemoryError 异常后应用继续运行。

```bash
java -Xmx512m -jar target/buggy-app-1.0.0.jar bug9.1
```

### 14. 超大数组 OOM (bug9.2)

直接分配超大数组导致 OOM。

```bash
java -Xmx512m -jar target/buggy-app-1.0.0.jar bug9.2
```

### 15. Metaspace 泄漏 (bug10 / PROBLEM_METASPACE)

持续创建类加载器导致 Metaspace 溢出。

```bash
java -XX:MaxMetaspaceSize=64m -jar target/buggy-app-1.0.0.jar bug10
# 或
java -XX:MaxMetaspaceSize=64m -jar target/buggy-app-1.0.0.jar PROBLEM_METASPACE
```

**排查工具**：
```bash
# 查看 Metaspace 使用情况
jstat -gcutil <pid> 1000

# 查看类加载情况
jcmd <pid> VM.classloader_stats
```

### 16. List 无初始容量 (bug11 / PROBLEM_LIST)

演示 ArrayList 频繁扩容的性能开销。

```bash
java -jar target/buggy-app-1.0.0.jar bug11
# 或
java -jar target/buggy-app-1.0.0.jar PROBLEM_LIST
```

### 17. List 有初始容量 (bug11.1)

演示指定初始容量的性能优势。

```bash
java -jar target/buggy-app-1.0.0.jar bug11.1
```

### 18. 数组性能对比 (bug11.2)

对比 ArrayList 与原生数组的性能差异。

```bash
java -jar target/buggy-app-1.0.0.jar bug11.2
```

### 19. 高效代码示例 (bug12 / PROBLEM_EFFICIENT)

延迟初始化示例，展示高效编码实践。

```bash
java -jar target/buggy-app-1.0.0.jar bug12
# 或
java -jar target/buggy-app-1.0.0.jar PROBLEM_EFFICIENT
```

### 20. 低效代码示例 (bug12.1)

立即初始化开销示例。

```bash
java -jar target/buggy-app-1.0.0.jar bug12.1
```

### 21. 随机低效代码 (bug12.2)

常见编码效率问题演示。

```bash
java -jar target/buggy-app-1.0.0.jar bug12.2
```

### 22. 对象引用链 (bug13 / PROBLEM_REFERENCES)

演示对象间引用关系及垃圾回收行为。

```bash
java -jar target/buggy-app-1.0.0.jar bug13
# 或
java -jar target/buggy-app-1.0.0.jar PROBLEM_REFERENCES
```

---

## 项目结构

```
├── pom.xml                     # Maven 配置文件
├── src/main/java/com/buggyapp/ # 源代码目录
│   ├── LaunchPad.java          # 程序入口
│   ├── sampleapp/              # 正常示例
│   ├── memoryleak/             # 内存泄漏 (OOM)
│   ├── memoryleaknooom/        # 高内存占用 (不 OOM)
│   ├── memoryleakthread/       # 线程内存泄漏
│   ├── slowfinalize/           # Finalizer 问题
│   ├── cpuspike/               # CPU 飙升
│   ├── threadleak/             # 线程泄漏
│   ├── blockedapp/             # 线程阻塞
│   ├── deadlock/               # 死锁
│   ├── stackoverflow/          # 栈溢出
│   ├── io/                     # I/O 问题
│   ├── oomcrash/               # OOM 崩溃示例
│   ├── metaspaceleak/          # 元空间泄漏
│   ├── inefficientlist/        # 低效集合使用
│   ├── efficientcode/          # 高效代码示例
│   ├── references/             # 引用类型示例
│   └── util/                   # 工具类
└── target/                     # 编译输出目录
    └── buggy-app-1.0.0.jar     # 打包后的 JAR 文件
```

---

## 常用 JVM 调优参数

```bash
# 堆内存设置
java -Xms512m -Xmx512m -jar target/buggy-app-1.0.0.jar bug1

# 栈空间设置
java -Xss256k -jar target/buggy-app-1.0.0.jar bug7

# 开启 GC 日志
java -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log -jar target/buggy-app-1.0.0.jar bug1

# 生成堆转储 on OOM
java -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heap_dump.hprof -jar target/buggy-app-1.0.0.jar bug1

# 元空间设置
java -XX:MaxMetaspaceSize=128m -jar target/buggy-app-1.0.0.jar <args>
```

---

## 故障排查工具速查

| 工具 | 用途 | 示例命令 |
|------|------|----------|
| `jps` | 查看 Java 进程 | `jps -lvm` |
| `jstat` | 监控 GC 和内存 | `jstat -gcutil <pid> 1000` |
| `jmap` | 生成堆转储 | `jmap -dump:format=b,file=heap.hprof <pid>` |
| `jstack` | 打印线程栈 | `jstack -l <pid>` |
| `jinfo` | 查看 JVM 参数 | `jinfo -flags <pid>` |
| `jconsole` | 图形化监控 | `jconsole <pid>` |
| `jvisualvm` | 可视化分析 | `jvisualvm` |
| `arthas` | 阿里巴巴开源诊断工具 | `java -jar arthas-boot.jar` |

---

## 学习建议

1. **内存问题**：使用 `jmap` + `jhat` / VisualVM 分析堆转储，查找大对象
2. **CPU 问题**：使用 `jstack` 找出占用 CPU 的线程，分析热点代码
3. **死锁问题**：`jstack` 会自动检测死锁，重点查看死锁报告
4. **线程问题**：使用 `jstack` 分析线程状态，关注 BLOCKED、WAITING 状态的线程
5. **GC 问题**：使用 `jstat` 或 GC 日志分析 GC 频率和耗时

---

## 作者

- **原作者**: Ram Lakshmanan
- **项目用途**: JVM 性能问题教学与培训

---

## 许可证

本项目仅供学习和教学使用。
