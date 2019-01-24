package club.wenfan.web.controller;

import club.wenfan.ExceptionHandle.CostomerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:wenfan
 * @description: 测试自定义异常
 * @data: 2018/12/12 22:31
 */
@RestController
public class TestGlobalCustomExceptionController {

    @GetMapping("exception")
    public void testException(){

        throw new CostomerException(1);
    }

}
