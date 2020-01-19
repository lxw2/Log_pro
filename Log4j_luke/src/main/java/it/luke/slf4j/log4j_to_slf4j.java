package it.luke.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class log4j_to_slf4j {

    public static void main(String[] args) {

        //创建记录日志的对象
        Logger log = LoggerFactory.getLogger(log4j_to_slf4j.class);

        log.debug("debug信息");
        log.info("info信息");
        log.warn("warn信息");
        log.error("error信息");

    }

}
