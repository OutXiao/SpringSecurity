package club.wenfan.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/18 21:46
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger log=LoggerFactory.getLogger(getClass());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息  持久层注入
        log.info("登陆名："+username);

        String encodedPWD=passwordEncoder.encode("123");
        System.out.println(encodedPWD);
        //根据查找到的用户的信息判断用户账户是否被冻结    accountNonLocked==false
        return new User(username,encodedPWD,  true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
