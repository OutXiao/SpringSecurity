package club.wenfan.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/27 18:38
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {


    @Override
    public String execute(Connection<?> connection) {

        //根据社交用户信息  默认创建用户并返回唯一标识
        return connection.getDisplayName();
    }
}
