package xyz.chaobei.logger;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class SimpleLogger {

    private final static Logger LOG = LoggerFactory.getLogger(SimpleLogger.class);

    public static void main(String[] args) {

        int newValue = 10;
        int oldValue = 8;

        // traditional api
        LOG.debug("LogTest newValue={},oldValue={}", newValue, oldValue);

        // new fluent log
        LOG.atDebug().setMessage("LogTest newValue={},oldValue={}").addArgument(newValue).addArgument(oldValue).log();
        LOG.atDebug().log("LogTest newvalue={},oldValue={}", newValue, oldValue);

        LOG.atDebug().setMessage("new Logger type").addKeyValue("old", oldValue).addKeyValue("new", newValue).log();

        /**
         * 06:14:41,505 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could
         * NOT find resource [logback-test.xml]
         * 06:14:41,505 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could
         * NOT find resource [logback.xml]
         */
        // LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        // StatusPrinter.print(loggerContext);

        LOG.error("LogTest error, test null pointer",new NullPointerException());
    }
}
