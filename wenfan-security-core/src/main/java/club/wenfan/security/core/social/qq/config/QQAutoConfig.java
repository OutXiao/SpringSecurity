package club.wenfan.security.core.social.qq.config;

import club.wenfan.security.core.properties.QQProperties;
import club.wenfan.security.core.properties.SecurityProperties;
import club.wenfan.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/24 20:13
 */
@Configuration
@ConditionalOnProperty(prefix = "club.wenfan.security.browser.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties qqProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties  qq = qqProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(),qq.getAppId(),qq.getAppSecret());
    }
}
