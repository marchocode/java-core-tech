package xyz.chaobei.logger.layout;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

public class MyLayout extends LayoutBase<ILoggingEvent> {

    /**
     * 布局设计器
     */
    @Override
    public String doLayout(ILoggingEvent event) {

        /**
         * MyLayout 1668849584391 xyz.chaobei.logger.layout.MyLayoutTest INFO MyLayoutTest info
         */
        StringBuffer sb = new StringBuffer(128);

        sb.append("MyLayout");
        sb.append(" ");
        sb.append(event.getTimeStamp());
        sb.append(" ");
        sb.append(event.getLoggerName());
        sb.append(" ");
        sb.append(event.getLevel());
        sb.append(" ");
        sb.append(event.getMessage());
        sb.append(" ");
        sb.append("\n");

        return sb.toString();
    }
}
