package club.wenfan.security.core.properties;


/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/29 23:38
 */
public class BrowserProperties {

    // 默认注册页面
    private String signUrl="/register.html";
    // 默认登陆页面
    private String loginPage=SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl;
    }
}
