package club.wenfan.security.core.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/29 23:38
 */
public class BrowserProperties {

    private String loginPage="/login.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
