package it.luke.log4j_1x;


import org.apache.log4j.Logger;

public class log_test {

    private static final Logger logger = Logger.getLogger(log_test.class);

    public static void main(String[] args) {


        logger.info("log_test.info");
        logger.debug("log_test.debug");
        logger.warn("log_test.warn");
        logger.error("log_test.error");

        //生成日志输出到控制台
        /*
        *   log4j.appender.it.luke.Console=org.apache.log4j.ConsoleAppender
            #ConsoleAppender 控制台输出
            #指定生成日志输出到控制台
            #DailyRollingFileAppender
            #

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
        * */



        //生成的日志指定到文件

        /*
        *
        *   log4j.appender.it.luke.DailyRollingFile=org.apache.log4j.DailyRollingFileAppender
            log4j.appender.it.luke.DailyRollingFile.file=log.log
            log4j.appender.it.luke.DailyRollingFile.DatePattern=yyyy-MM-dd
            log4j.appender.it.luke.DailyRollingFile.layout=org.apache.log4j.TTCCLayout

        * */

    }
}
