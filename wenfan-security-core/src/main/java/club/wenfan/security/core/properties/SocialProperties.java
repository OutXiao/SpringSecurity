package club.wenfan.security.core.properties;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/24 20:10
 */
public class SocialProperties {


    // 默认qq处理前缀
    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
