package club.wenfan.security.support;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/29 23:19
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
