package it.luke.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusManager;

public class logback_test {

    public static void main(String[] args) {

        // 创建记录日志的对象
        Logger log = LoggerFactory.getLogger(logback_test.class);

        log.debug("debug信息");
        log.info("info信息");
        log.warn("warn信息");
        log.error("error信息");



    }

}
