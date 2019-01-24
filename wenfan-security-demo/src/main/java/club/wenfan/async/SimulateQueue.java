package club.wenfan.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimulateQueue {

	private Logger log= LoggerFactory.getLogger(getClass());
	
	private String placeOrder;
	
	private String completeOrder;

	
	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) throws Exception {
		
		new Thread(()->{
			log.info("接到下单请求"+placeOrder);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
			log.info("下单请求处理完毕"+placeOrder);
		}) .start();
		
		
	}

	public String getCompleteOrder() {
		return completeOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
	
	
}
