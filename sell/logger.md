# 日志框架

## 日志框架的能力

1. 定制输出目标
2. 定制输出格式
3. 携带上下文信息
4. 运行时选择性输出
5. 灵活的配置

## 常见日志框架

### 日志门面

- JCL
- SLF4J
- Jboss-logging（不是服务于大众）

### 日志实现

- Log4j （作者说太烂了）
- Log4j2（开源框架对其支持有限）
- Logback
- JUL(实现过于简陋)

## 使用注解

- 引入依赖

```java

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.16.18</version>
			<scope>provided</scope>
		</dependency>
```

- 使用@Slf4j直接使用日志

  ```java
  log.debug("debug...");
  ```

  



