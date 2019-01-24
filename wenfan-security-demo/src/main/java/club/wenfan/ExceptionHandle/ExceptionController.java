package club.wenfan.ExceptionHandle;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/*
 * 
 * 处理全局controller异常
 * @author:wenfan
 * @description:
 * @data: 2018/12/12 18:01
 */
@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleException(CostomerException ex){
    	Map<String,Object> map=new HashMap<>();
    	map.put("id", ex.getId());
    	map.put("message", ex.getMessage());
        return map;
    }
}
