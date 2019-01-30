package club.wenfan.security.core.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/25 16:18
 */
public class QQOAuth2Template extends OAuth2Template {


    private Logger log = LoggerFactory.getLogger(getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        // //设置系统自动填充clientId和clientSecret等参数
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);
        log.info("获取accessToken的响应"+responseStr);

        String items[] = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr,"&");

        // 获取的accessToken
        String accessToken = StringUtils.substringAfterLast(items[0],"=");
        log.info("获取的accessToken:"+accessToken);
        Long expireIn = new Long(StringUtils.substringAfterLast(items[1],"="));
        String refreshToken = StringUtils.substringAfterLast(items[2],"=");
        return new AccessGrant(accessToken,null,refreshToken,expireIn);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate=super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
