package club.wenfan.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener  implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private SimulateQueue simulateQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	private Logger log= LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		new Thread(()->{
			
			while(true) {
				if(StringUtils.isNotBlank(simulateQueue.getCompleteOrder())) {
					String orderNumber=simulateQueue.getCompleteOrder();
					log.info("订单处理结果："+orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success!");
					simulateQueue.setCompleteOrder(null);
					
				}else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();;
		
		
		
	}
	
	

}
