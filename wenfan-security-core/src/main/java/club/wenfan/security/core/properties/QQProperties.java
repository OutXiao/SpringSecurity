package club.wenfan.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/24 20:09
 */
public class QQProperties extends SocialProperties {

    private String providerId="qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
