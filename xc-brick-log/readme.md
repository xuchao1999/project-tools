# 统一日志处理工具

## 功能
打印输出controller的请求信息，以及记录操作日志

## 造轮子步骤

1. 构建一个spring boot 项目

2. 引入依赖
```java
                <dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
			<version>1.2.3</version>
		</dependency>
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-core</artifactId>
			<version>1.2.3</version>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web-services</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		<dependency>
			<groupId>cn.hutool</groupId>
			<artifactId>hutool-all</artifactId>
			<version>4.6.8</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.swagger</groupId>
			<artifactId>swagger-annotations</artifactId>
			<version>1.5.20</version>
		</dependency>
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.62</version>
		</dependency>
```

3.自定义注解RecordOptLog 如果不需要记录操作日志功能，省略此步
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordOptLog {
    // 描述
    String value();
}
```
4.定义操作日志封装类,同样，不需要的可以不定义
```java
@Data
@EqualsAndHashCode
@ToString
public class OptLogDTO {
    // 操作IP
    private String requestIp;
    //日志类型 LogType{OPT:操作类型;EX:异常类型}
    private String type;
    // 操作人编号
    private String userCode;
    //操作人
    private String userName;
    //操作描述
    private String description;
    // 类路径
    private String classPath;
    // 请求方法名
    private String actionMethod;
    // 请求地址
    private String requestUrl;
    // 请求参数
    private String params;
    // 返回结果
    private Object result;
    // 请求开始时间
    private Date startTime;
    // 请求结束时间
    private Date finishTime;
    // 请求花费时间
    private Long consumingTime;
}
```
5.定义系统日志事件，理由：捕获操作日志的目的就是方便后期进行操作统计，以及锁定责任，
减少扯皮。所以就要把操作日志入库，所以统一记录日志工具只负责捕获日志，具体怎么处置日志
就通知接入系统去做。
```java
public class SysLogEvent extends ApplicationEvent {
    public SysLogEvent(OptLogDTO optLogDTO) {
        super(optLogDTO);
    }
}
```
6.定义事件监听器
```java
@Slf4j
@AllArgsConstructor
public class SysLogListener {
    // 函数式接口，具体的对optLogDTO的操作以consumer传入
    private Consumer<OptLogDTO> consumer;
    @Async//异步处理
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        consumer.accept((OptLogDTO) event.getSource());
    }
}
```
7.定义日志切面，理解代码，请仔细看代码以及注解
```java
@Aspect
@Component
@Slf4j
public class SysLogAspect {
    // spring event 需要用到该接口去发布事件
    @Resource
    private ApplicationContext applicationContext;
    // 记录操作日志信息
    private static final ThreadLocal<OptLogDTO> THREAD_LOCAL = new ThreadLocal<>();
    // 切点：controller目录下所有方法
    @Pointcut("execution (* *..*.*Controller.*(..))")
    public void recordLog(){}
    /**
     * @Author Chao Xu
     * @Description 环绕通知打印日志，以及发布事件
     * @Date 12:22 2022/8/21
     * @Param [joinPoint]
     * @return java.lang.Object
     **/
    @Around(value = "recordLog()")
    public Object sysLog(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = null;
        try {
            handleBefore(joinPoint);
            proceed = joinPoint.proceed();
            handleAfter(proceed);
        } finally {
            //System.lineSeparator():系统换行符
        }
        return proceed;
    }
    /**
     * @Author Chao Xu
     * @Description 前置方法：
     * @Date 12:22 2022/8/21
     * @Param [joinPoint]
     * @return void
     **/
    private void handleBefore(ProceedingJoinPoint joinPoint) {
        // 打印请求信息
        printRequestInfo(joinPoint);
        // 如果controller方法上有@RecordOptLog注解，则构建操作日志类
        if(null != ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RecordOptLog.class)){
            setOptLog(joinPoint);
        }
    }
    private void handleAfter(Object proceed) {
        // 打印返回信息
        log.info("Response           ：{}", JSON.toJSONString(proceed));
        log.info("=========END=========" + System.lineSeparator());
        // 如果optLogDTO不为空，则发布事件
        OptLogDTO optLogDTO = THREAD_LOCAL.get();
        if(null != optLogDTO){
            optLogDTO.setResult(proceed);
            // 发布事件
            pulishEvent(optLogDTO);
        }
    }
    /**
     * @Author Chao Xu
     * @Description 打印请求信息
     * @Date 12:29 2022/8/21
     * @Param [joinPoint]
     * @return void
     **/
    private void printRequestInfo(ProceedingJoinPoint joinPoint){
        // 获取swagger注解controller描述信息
        String controllerDes = "";
        Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
        if(null != api && null !=api.tags() && api.tags().length > 0){
            controllerDes = api.tags()[0];
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("=========Start=========");
        log.info("URL                ：{}", request.getRequestURL());
        log.info("BusinessName       ：{}", controllerDes);
        log.info("HTTP Method        ：{}", request.getMethod());
        log.info("Class Method       ：{}.{}", joinPoint.getSignature().getDeclaringTypeName(), ((MethodSignature) joinPoint.getSignature()).getName());
        log.info("IP                 ：{}", request.getRemoteHost());
        log.info("Request Args       ：{}",  JSON.toJSONString(joinPoint.getArgs()));
    }
    /**
     * @Author Chao Xu
     * @Description 发布记录操作日志事件
     * @Date 10:28 2022/8/21
     * @Param [optLogDTO]
     * @return void
     **/
    private void pulishEvent(OptLogDTO optLogDTO){
        optLogDTO.setFinishTime(DateTime.now());
        optLogDTO.setConsumingTime(optLogDTO.getFinishTime().getTime() - optLogDTO.getStartTime().getTime());
        applicationContext.publishEvent(new SysLogEvent(optLogDTO));
        THREAD_LOCAL.remove();
    }
    /**
     * @Author Chao Xu
     * @Description 构造OptLog
     * @Date 12:25 2022/8/21
     * @Param [joinPoint]
     * @return void
     **/
    public void setOptLog(ProceedingJoinPoint joinPoint){
        OptLogDTO optLogDTO = new OptLogDTO();
        optLogDTO.setStartTime(DateTime.now());
        optLogDTO.setUserCode("1110");
        optLogDTO.setUserName("张三");
        // 获取swagger注解controller描述信息
        String controllerDes = "";
        Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
        if(null != api && null !=api.tags() && api.tags().length > 0){
            controllerDes = api.tags()[0];
        }
        optLogDTO.setDescription(controllerDes);
        // 获取方法描述信息
        String controllerMethodDes = "";
        // 类名
        optLogDTO.setClassPath(joinPoint.getTarget().getClass().getName());
        // 方法名
        optLogDTO.setActionMethod(joinPoint.getSignature().getName());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // ip
        optLogDTO.setRequestIp(request.getRemoteHost());
        // 入参
        optLogDTO.setParams(JSON.toJSONString(joinPoint.getArgs()));
        // 请求路径
        optLogDTO.setRequestUrl(request.getRequestURL().toString());
        THREAD_LOCAL.set(optLogDTO);
    }
}
```
8.定义配置类，实例化日志切面
```java
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "sys.log.enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public SysLogAspect controllerLogAspect(){
        return new SysLogAspect();
    }
}
```
9.编写logback-base.xml
```java
<?xml version="1.0" encoding="UTF-8"?>
<included>
    <contextName>logback</contextName>
    <!--
		name的值是变量的名称，value的值时变量定义的值
		定义变量后，可以使“${}”来使用变量
	-->
    <property name="log.path" value="d:\\logs" />
    <!-- 彩色日志 -->
    <!-- 彩色日志依赖的渲染类 -->
    <conversionRule
            conversionWord="clr"
            converterClass="org.springframework.boot.logging.logback.ColorConverter" />
    <conversionRule
            conversionWord="wex" converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter" />
    <conversionRule conversionWord="wEx" converterClass="org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter" />
    <!-- 彩色日志格式 -->
    <property name="CONSOLE_LOG_PATTERN" value="${CONSOLE_LOG_PATTERN:-%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}"/>
    <!--输出到控制台-->
    <appender name="LOG_CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <Pattern>${CONSOLE_LOG_PATTERN}</Pattern>
            <!-- 设置字符集 -->
            <charset>UTF-8</charset>
        </encoder>
    </appender>
    <!--输出到文件-->
    <appender name="LOG_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 正在记录的日志文件的路径及文件名 -->
        <file>${log.path}/logback.log</file>
        <!--日志文件输出格式-->
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 每天日志归档路径以及格式 -->
            <fileNamePattern>${log.path}/info/log-info-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>100MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
            <!--日志文件保留天数-->
            <maxHistory>5</maxHistory>
        </rollingPolicy>
    </appender>
</included>
```
10.定义logback-spring.xml
```java
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!--引入其他配置文件-->
    <include resource="logback-base.xml" />
    <include resource="logback-property.xml" />
    <!--
    <logger>用来设置某一个包或者具体的某一个类的日志打印级别、
    以及指定<appender>。<logger>仅有一个name属性，
    一个可选的level和一个可选的addtivity属性。
    name:用来指定受此logger约束的某一个包或者具体的某一个类。
    level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，
          如果未设置此属性，那么当前logger将会继承上级的级别。
    addtivity:是否向上级logger传递打印信息。默认是true。
     -->
    <!--开发环境-->
    <springProfile name="dev">
        <logger name="${dev.spring.profile.name}" additivity="false" level="debug">
            <appender-ref ref="LOG_CONSOLE"/>
        </logger>
    </springProfile>
    <!--生产环境-->
    <springProfile name="pro">
        <logger name="${pro.spring.profile.name}" additivity="false" level="info">
            <appender-ref ref="LOG_FILE"/>
        </logger>
    </springProfile>
    <!--
        root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性
        level:设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF 默认是DEBUG
        可以包含零个或多个元素，标识这个appender将会添加到这个logger。
    -->
    <root level="info">
        <appender-ref ref="LOG_CONSOLE" />
        <appender-ref ref="LOG_FILE" />
    </root>
</configuration>
```
11.定义yml文件
```java
server:
  port: 9000
logging:
  #在Spring Boot项目中默认加载类路径下的logback-spring.xml文件
  config: classpath:logback-spring.xml
spring:
  profiles:
    active: dev
```
12.建文件：resources/META-INF/spring.factories, 把LogAutoConfiguration地址配进去，因为springboot的配置类加载都会到这个文件去找
```java
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.xc.xcbricklog.config.LogAutoConfiguration
```

## 统一日志工具接入手册
1. 将刚刚的日志项目打包
2. 新建一个springboot项目
3. 依赖刚刚日志项目的包, 具体依赖哪个包，可以去刚刚日志项目的pom.xml文件中</parent>后面的三行就是
```java
        <dependency>
            <groupId>com.xc</groupId>
            <artifactId>xc-brick-log</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```
4.写一个controller，运行起来，你就可以看到输出的日志了
```java
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(@RequestParam String userName){
        return userName;
    }
}
```
```java
=========Start=========
URL                ：http://localhost:8080/test
BusinessName       ：
HTTP Method        ：GET
Class Method       ：com.xcbricklogapp.controller.user.TestController.test
IP                 ：127.0.0.1
Request Args       ：["xc"]
Response           ："xc"
=========END=========
```
5.如果还想记录操作日志则在controller的方法上加上@RecordOptLog注解
```java
@RestController
public class TestController {
    @GetMapping("/test")
    @RecordOptLog(
            value = "测试"
    )
    public String test(@RequestParam String userName){
        return userName;
    }
}
```
6.并且编写对OptLogDTO的具体操作方法，可以定义为一个service,其实LogService就是传给日志服务中
监听方法里的consumer属性
```java
@Slf4j
@Service
public class LogService {
    public void handleLog(OptLogDTO optLogDTO){
        log.info("optLog: {}", optLogDTO);
    }
}
```
7.定义配置类，让springboot管理LogService
```java
@Configuration
public class LogAutoCofiguration {
    @Bean
    @ConditionalOnMissingBean
    public SysLogListener sysLogListener(LogService logService){
        return new SysLogListener(optLogDTO -> logService.handleLog(optLogDTO));
    }
}
```
8.定义logback-property.xml，用于自定义日志输出文件位置，以及日志输出的层级：比如：dev.spring.profile.name 的值为com.a.b, 
如果你的项目结构为com.a.c,则该xml配置不生效，dev.spring.profile.name的层级结构要是你项目的节点或者父节点

9.yml文件
```java
server:
  port: 8080
spring:
#  设置环境
  profiles:
    active: dev
# 系统操作日志打印开关
sys:
  log:
    enabled: true
```
10.运行
```java
=========Start=========
URL                ：http://localhost:8080/test
BusinessName       ：
HTTP Method        ：GET
Class Method       ：com.xcbricklogapp.controller.user.TestController.test
IP                 ：127.0.0.1
Request Args       ：["xc"]
Response           ："xc"
=========END=========
optLog: OptLogDTO(requestIp=127.0.0.1, type=null, userCode=1110, userName=张三, description=, 
classPath=com.xcbricklogapp.controller.user.TestController, actionMethod=test, 
requestUrl=http://localhost:8080/test, params=["xc"], result=xc, startTime=2022-08-22 19:40:14,
finishTime=2022-08-22 19:40:14, consumingTime=5)
```