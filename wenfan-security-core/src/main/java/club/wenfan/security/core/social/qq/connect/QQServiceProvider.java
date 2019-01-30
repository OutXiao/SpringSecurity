package club.wenfan.security.core.social.qq.connect;

import club.wenfan.security.core.social.qq.api.QQ;
import club.wenfan.security.core.social.qq.api.QQImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;


/**
 *
 *   服务商： 控制OAuth2Template 的创建 和 获取qqApi
 *
 * @author:wenfan
 * @description:
 * @data: 2019/1/24 18:31
 */

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {


    private String appId;


    //获取Authorization Code
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    // 通过Authorization Code获取Access Token
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId=appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
