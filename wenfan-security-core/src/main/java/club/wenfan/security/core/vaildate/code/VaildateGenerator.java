package club.wenfan.security.core.vaildate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 18:32
 */
public interface VaildateGenerator {
    ValidateCode CreateCode(ServletWebRequest request);
}
