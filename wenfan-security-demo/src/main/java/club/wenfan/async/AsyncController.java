package club.wenfan.async;


import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 *   线程异步处理REST服务
 *   
 * @author Administrator
 *
 */
//@RestController
public class AsyncController {

	private Logger log= LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SimulateQueue simulateQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@GetMapping("getorder")
	public  DeferredResult<String> order() throws Exception{
		log.info("main thread start");  //主线程
		
//		Callable<String> rusult=new Callable<String>() {
//
//			
//			//副线程启动
//			@Override
//			public String call() throws Exception {
//				log.info("side thread start !!!!");
//				Thread.sleep(1000);
//				log.info("side thread end !!!!");
//				return "success";
//			}
//			
//		};
		
		String orderNumbre=RandomStringUtils.randomNumeric(8);
		simulateQueue.setPlaceOrder(orderNumbre);
		
		DeferredResult<String> result=new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumbre, result);
		
		
		
		
		log.info("main thread end");
		return result;
		
	}
	
}
