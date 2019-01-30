package club.wenfan.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/24 17:41
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger log = LoggerFactory.getLogger(getClass());

    public QQImpl(String accessToken,String appId){
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId=appId;
        // 将URL_GET_OPENID 和accessToken 拼在一块
        String url = String.format(URL_GET_OPENID,accessToken);
        // 获取的结果
        String result = getRestTemplate().getForObject(url,String.class);
        log.info("回调"+result);
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");

    }



    @Override
    public QQUserInfo getQQUserInfo() {
        String url=String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);
        log.info("result"+result);
        QQUserInfo info = null;
        try {
            info = objectMapper.readValue(result, QQUserInfo.class);
            info.setOpenId(openId);
            return info;
        } catch (IOException e) {
            throw new RuntimeException("获取信息失败",e);
        }
    }






}
