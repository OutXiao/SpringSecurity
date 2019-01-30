package club.wenfan.security.core.social.qq.connect;

import club.wenfan.security.core.social.qq.api.QQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;


/**
 * @author:wenfan
 * @description:  控制 QQServiceProvider的创建，QQAdapt的创建
 * @data: 2019/1/24 19:30
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapt());
    }
}
