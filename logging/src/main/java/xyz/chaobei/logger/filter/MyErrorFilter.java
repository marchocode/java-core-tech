package xyz.chaobei.logger.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class MyErrorFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {

        if (Level.ERROR_INTEGER.equals(event.getLevel().toInteger())) {
            return FilterReply.ACCEPT;
        }

        return FilterReply.NEUTRAL;
    }
}
