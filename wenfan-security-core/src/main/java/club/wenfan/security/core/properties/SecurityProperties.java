package club.wenfan.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  从properties中 读取配置信息
 *
 * @author:wenfan
 * @description:
 * @data: 2018/12/29 23:38
 */
@ConfigurationProperties(prefix = "club.wenfan.security.browser")
public class SecurityProperties {


    // 默认注册页面
    private String signUrl="/register.html";
    // 默认登陆页面
    private String loginPage=SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private int rememberSeconds=60;

    private VaidateCodeProperties code = new VaidateCodeProperties();

    private SocialProperties social =new SocialProperties();

    private LoginType loginType=LoginType.JSON; // 默认返回json格式

    public VaidateCodeProperties getCode() {
        return code;
    }

    public void setCode(VaidateCodeProperties code) {
        this.code = code;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }



    public String getLoginPage() {

        return loginPage;
    }

    public void setLoginPage(String loginPage) {

        this.loginPage = loginPage;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public int getRememberSeconds() {
        return rememberSeconds;
    }

    public void setRememberSeconds(int rememberSeconds) {
        this.rememberSeconds = rememberSeconds;
    }

    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl;
    }
}
