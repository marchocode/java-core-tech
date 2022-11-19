package xyz.chaobei.logger.layout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLayoutTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyLayoutTest.class);

    public static void main(String[] args) {

        LOGGER.info("MyLayoutTest info");
        LOGGER.debug("MyLayoutTest debug");

    }

}
