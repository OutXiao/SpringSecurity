package club.wenfan.web.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/25 13:04
 */
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
