# Buggy App

## 介绍

Buggy App 是一个用于 JVM 性能问题教学演示的 Java 应用程序，可以模拟各种常见的性能问题，帮助开发者学习和实践 JVM 调优、故障排查和监控技术。

### 可模拟的问题类型

- **内存泄漏** - 模拟 HashMap 无限增长导致的内存泄漏
- **OutOfMemoryError** - 模拟堆内存溢出
- **CPU 飙升** - 模拟无限循环导致的 CPU 使用率 100%
- **线程泄漏** - 模拟无限创建线程导致的资源耗尽
- **StackOverflowError** - 模拟无限递归导致的栈溢出
- **死锁** - 模拟经典的双线程死锁场景
- **线程阻塞** - 模拟多线程阻塞导致的应用无响应
- **Finalizer 问题** - 模拟慢速 finalize 导致的对象堆积
- **I/O 问题** - 模拟 I/O 密集型操作

---

## 快速开始

### 编译项目

```bash
# 编译所有 Java 文件
javac -d out java/com/buggyapp/**/*.java

# 打包成 JAR
cd out
jar cvfm ../buggyApp.jar ../META-INF/MANIFEST.MF com/
```

### 运行方式

```bash
java -jar buggyApp.jar <参数>
```

---

## 使用指南

### 1. 正常示例 (sample)

生产者-消费者模式示例，展示正常的多线程协作。

```bash
java -Xmx256m -jar buggyApp.jar sample
```

### 2. 内存泄漏导致 OOM (bug1 / PROBLEM_OOM)

HashMap 无限增长，最终导致 `OutOfMemoryError`。

```bash
java -Xmx512m -jar buggyApp.jar bug1
# 或
java -Xmx512m -jar buggyApp.jar PROBLEM_OOM
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
java -Xmx512m -jar buggyApp.jar bug1.1
```

### 4. 高内存占用但不 OOM (bug1.2 / PROBLEM_MEMORY)

智能控制内存占用在 80-90%，不会触发 OOM，模拟"慢性"内存问题。

```bash
java -Xmx512m -jar buggyApp.jar PROBLEM_MEMORY
```

### 5. Finalizer 问题 (bug2)

创建带有慢速 `finalize()` 方法的对象，导致 Finalizer 线程堆积。

```bash
java -Xmx512m -jar buggyApp.jar bug2
```

### 6. CPU 飙升 (bug3 / PROBLEM_CPU)

启动 6 个线程进行无限空转，CPU 使用率飙升至 100%。

```bash
java -Xmx256m -jar buggyApp.jar bug3
# 或
java -Xmx256m -jar buggyApp.jar PROBLEM_CPU
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
java -Xmx1g -jar buggyApp.jar bug4
# 或
java -Xmx1g -jar buggyApp.jar PROBLEM_THREADLEAK
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
java -Xmx1g -jar buggyApp.jar bug5
# 或
java -Xmx1g -jar buggyApp.jar PROBLEM_BLOCKED
```

**排查工具**：
```bash
# 查看 BLOCKED 线程
jstack <pid> | grep -A 1 "java.lang.Thread.State: BLOCKED"
```

### 9. 死锁 (bug6 / PROBLEM_DEADLOCK)

经典的双线程死锁场景：ThreadA 持有锁 A 请求锁 B，ThreadB 持有锁 B 请求锁 A。

```bash
java -Xmx1g -jar buggyApp.jar bug6
# 或
java -Xmx1g -jar buggyApp.jar PROBLEM_DEADLOCK
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
java -Xmx256m -Xss128k -jar buggyApp.jar bug7

# 使用较大的栈空间，运行更久
java -Xmx256m -Xss1m -jar buggyApp.jar bug7
```

### 11. I/O 问题 (bug8 / PROBLEM_IO)

模拟 I/O 密集型操作。

```bash
java -jar buggyApp.jar bug8
# 或
java -jar buggyApp.jar PROBLEM_IO
```

---

## 项目结构

```
java/com/buggyapp/
├── LaunchPad.java              # 程序入口
├── sampleapp/                  # 正常示例
├── memoryleak/                 # 内存泄漏 (OOM)
├── memoryleaknooom/            # 高内存占用 (不 OOM)
├── memoryleakthread/           # 线程内存泄漏
├── slowfinalize/               # Finalizer 问题
├── cpuspike/                   # CPU 飙升
├── threadleak/                 # 线程泄漏
├── blockedapp/                 # 线程阻塞
├── deadlock/                   # 死锁
├── stackoverflow/              # 栈溢出
├── io/                         # I/O 问题
├── oomcrash/                   # OOM 崩溃示例
├── metaspaceleak/              # 元空间泄漏
├── inefficientlist/            # 低效集合使用
├── efficientcode/              # 高效代码示例
├── references/                 # 引用类型示例
└── util/                       # 工具类
```

---

## 常用 JVM 调优参数

```bash
# 堆内存设置
java -Xms512m -Xmx512m -jar buggyApp.jar bug1

# 栈空间设置
java -Xss256k -jar buggyApp.jar bug7

# 开启 GC 日志
java -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log -jar buggyApp.jar bug1

# 生成堆转储 on OOM
java -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heap_dump.hprof -jar buggyApp.jar bug1

# 元空间设置
java -XX:MaxMetaspaceSize=128m -jar buggyApp.jar <args>
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
