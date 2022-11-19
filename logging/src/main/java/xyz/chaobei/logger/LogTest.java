package xyz.chaobei.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    private final static Logger LOG = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {

        int newValue = 10;
        int oldValue = 8;

        // traditional api
        LOG.debug("LogTest newValue={},oldValue={}", newValue, oldValue);

        LOG.atDebug().setMessage("LogTest newValue={},oldValue={}").addArgument(newValue).addArgument(oldValue).l

        LOG.info("info message");
    }

}
