package it.luke.log4j_2x;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
