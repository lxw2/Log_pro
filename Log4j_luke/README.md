

# LOG

```x
log4j:WARN No appenders could be found for logger (it.luke.log4j_1x.log_test).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.

报错信息
因为多个不同版本的maven导致的
log4j1x log4j2x 删除一个防止冲突
```





### Log4j1.x

```java
import org.apache.log4j.Logger;

public class log_test {

    //获取日志对象
    private static final Logger logger = Logger.getLogger(log_test.class);

    public static void main(String[] args) {

	//调用对象api,输出不同级别的日志
        logger.info("log_test.info");
        logger.debug("log_test.debug");
        logger.warn("log_test.warn");
        logger.error("log_test.error");
    }
}

//console -- 输出结果
//2020-01-18 22:01:04,247  INFO (it.luke.log_demo.log_test:log_test.info) - log_test.info
//2020-01-18 22:01:04,249  WARN (it.luke.log_demo.log_test:log_test.warn) - log_test.warn
//2020-01-18 22:01:04,249 ERROR (it.luke.log_demo.log_test:log_test.error) - log_test.error

//总结点 
//1.级别向下靠拢
//2.多个控制器,按照最精确的(指定的类路径名)级别为准 -- > ...log_test > root
```



```properties
# Configure logging for testing: optionally with log file

#log4j.rootLogger=debug,appender
#log4j.rootLogger=info,appender
#log4j.rootLogger=error,appender

log4j.appender.it.luke.Console=org.apache.log4j.ConsoleAppender
#ConsoleAppender 控制台输出
#指定生成日志输出到控制台
#DailyRollingFileAppender


log4j.appender.it.luke.Console.layout=org.apache.log4j.PatternLayout
#PatternLayout 可以灵活指定布局模式
#simpleLayout 包含日志信息的级别和信息字符串
#TTCClayout 包含日志产生的事件,线程,类别等信息


log4j.appender.it.luke.Console.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p (%c:%m) - %m%n

#   %m 输出代码中指定的消息
#   %p 输出优先级,即debug,info,warn,error,fatal
#   %r 输出应用启动到输出该log信息耗费的毫秒数
#   %c 输出所属的类目,通常就是所在类的全名
#   %t 输出产生该日志事件的线程名
#   %n 输出一个回车换行符,windows平台为"\r\n",Unix为"\n"
#   %d 输出日志事件点的日期或事件,默认格式为ISO8601,也可以在气候指定格式
#   比如 %d{yyyy-MM-dd HH:mm:ss,SSS} ,输出类型为: 2015-1-20 18:25:31,758


log4j.appender.it.luke.DailyRollingFile=org.apache.log4j.DailyRollingFileAppender  
log4j.appender.it.luke.DailyRollingFile.file=log.log
log4j.appender.it.luke.DailyRollingFile.DatePattern=yyyy-MM-dd
log4j.appender.it.luke.DailyRollingFile.layout=org.apache.log4j.TTCCLayout


#控制器 控制器的value = 级别,输出选择器1,输出选择器2,...
log4j.logger.it.luke.log_demo.log_test=info,it.luke.Console,it.luke.DailyRollingFile

#指定的流程 指定流程appender(输出选择器) --> layout(输出样式) --> logger(控制器) -->level (输出级别)

```





### Log4j2.x



```java
package it.luke.log4j_2x;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class log4j2_test {

    public static void main(String[] args) {
        //创建记录日志的对象
        Logger log = LogManager.getLogger(log4j2_test.class);

        //下面语句会根据log4j2.xml中的日志级别输出
        log.debug("debug信息");
        log.info("info信息");
        log.warn("warn信息");
        log.error("error信息");

    }
}

```





```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="OFF">
    <Appenders>
        <!-- console -->
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
        <!-- file -->
        <File name="file" fileName="log/output.log" append="true">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </File>
        <!-- rollingFile -->
        <RollingFile name="roolingFlie" fileName="logs/app.log"
            filePattern="logs/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
            <SizeBasedTriggeringPolicy size="1kb" />
        </RollingFile>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

**xml文件的分析**

```xml
若开发者没有设置 log4j2.xml，则系统会使用默认的日志配置：只会输出到控制台 error 级别的信息。

configuration标签

configuration标签的 status 属性用于设置 Log4j2 自身运行的日志显示级别，一般为OFF，不显示,也可以设置为 ERROR、DEBUG 等其它级别。

Console标签
Console标签的 target 属性用于设置输出的目标形式，其值一般为：SYSTEM_OUT 或 SYSTEM_ERR

File标签
File标签的 fileName 属性用于设置文件的文件保存路径及文件名。如本例的意思是，日志文件名为 output.log，将其存放在当前项目的根目录下的 log 子目录中,如果log目录不存在会自动创建。

append 属性用于设置是否以追加方式将日志写入文件中。

RollingFile标签
fileName 指定存放目录及第一个日志文件名。filePattern 指定新创建的日志文件的文件名。本例还会对文件进行压缩。

SizeBasedTriggeringPolicy子标签用于指定每一个日志文件最大文件大小。当达到这个指定值后，会自动再新建一个日志文件。 

loggers标签
用于配置根Logger 对象，用来指定所使用的日志记录器，及显示的级别。
其子标签root用于指定所使用的日志记录器。该子标签的属性 level 用于指定显示级别，主要是通过root的子标签appender-ref来引用appenders中定义好的记录器。
需要注意的是，只要在appenders中定义了File、 RollingFile等，且在其中指定了日志存放的目录，无论是否在appender-ref中引用，这些目录都会自动创建。

```



**生成的日志文件**

![1579358063293](C:\Users\10443\AppData\Roaming\Typora\typora-user-images\1579358063293.png)



### slf4j

### slf4j简介

 

​		在实际应用当中，不同的系统可能使用了不同的库来记录日志。比如你使用log4j2开发了一款产品，而购买你产品的用户所使用的系统是jdk的Logging时，你可能需要对千上万的log4j2的调用进行修改，这肯定是一件费时费力的事情。为了解决该问题，slf4j诞生了。
​		 slf4j，全称是Simple Logging Facade for Java，中文是简单日志门面。它仅仅是一种规范，一个接口，不是具体的日志解决方案，不做具体的日志服务。 Log4j和Log4j2以及后面要学习的logback都是 slf4j 的一种具体实现。
 		slf4j 有自己单独的一套 API，这套 API 与具体的日志实现技术的 API 是不同的。为了能使 slf4j 的 API 与具体日志实现技术 API 结合到一起，在 slf4j 与具体日志技术之间，还需要一个连接的 jar包。这个jar包在 sfl4j 框架中存放着。

![1579358326435](C:\Users\10443\AppData\Roaming\Typora\typora-user-images\1579358326435.png)

**将log4j改成slf4j**



### logback

