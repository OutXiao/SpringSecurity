package club.wenfan.service;

import org.springframework.stereotype.Service;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/12 17:16
 */
@Service
public class HelloService implements GreetingService{

    @Override
    public String helloGreeting(String name) {
        System.out.print("greeting...");
        return "hello"+name;
    }
}
