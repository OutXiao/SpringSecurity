package club.wenfan.security.core.social;

import club.wenfan.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/24 19:40
 */

@Order(10)
/**
 *
 QQAutoConfig，WeixinAutoConfiguration，SocialConfig 这3个都是 SocialConfigurerAdapter 的子类，
 但是只有 SocialConfig 覆盖了 SocialConfigurerAdapter  的  getUsersConnectionRepository 方法。
 如果SocialConfig 先加载 QQAutoConfig 或 WeixinAutoConfiguration 后加载，
 由于后加载的配置没有重写 getUsersConnectionRepository 方法，
 所以最终会用 SocialConfigurerAdapter 里的默认配置。
 在 SocialConfig 加了 @Order(10) 以后，确保了 SocialConfig 会被最后加载，
 所以 UsersConnectionRepository 会用最后加载的 SocialConfig 里的配置。
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired( required = false)
    private ConnectionSignUp connectionSignUp;


    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText()); // 不做加密解密
        repository.setTablePrefix("wenfan_"); // userconnection的前缀设置
        /*
        如果不想让网站自定义注册 去掉注释
        if(connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }*/

        return repository;
    }

    /**
     *  一定要将 filterProcessesUrl 配置进去
     *   debug 好几个小时  QAQ
     * @author wenfan
     * @date
     * @param
     * @return
     */

    @Bean
    public SpringSocialConfigurer wenfanSocialSecurityConfig() {
        // 默认配置类，进行组件的组装
        // 包括了过滤器SocialAuthenticationFilter 添加到security过滤链中
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        WenfanSpringSocialConfigurer springSocialConfigurer = new WenfanSpringSocialConfigurer(filterProcessesUrl);
        springSocialConfigurer.signupUrl(securityProperties.getSignUrl());
        return springSocialConfigurer;
    }

    /**
     *
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }

}
