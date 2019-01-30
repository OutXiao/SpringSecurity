package club.wenfan.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/25 12:58
 */
@Component
public class MyListener implements ApplicationListener<MyEvent> {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        log.info(String.format("%s监听到事件源："),MyListener.class.getSimpleName(),myEvent.getSource());
    }

}
