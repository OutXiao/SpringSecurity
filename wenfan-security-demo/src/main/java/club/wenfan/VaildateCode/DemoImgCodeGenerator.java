package club.wenfan.VaildateCode;

import club.wenfan.security.core.vaildate.code.ImgCode;
import club.wenfan.security.core.vaildate.code.VaildateGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 *  以增量的形式实现变化*
 *
 *  自定义图形验证码实现类  -- 此时会产生空指针
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 19:19
 */
//@Component("imgCodeGenerator")
public class DemoImgCodeGenerator implements VaildateGenerator {

    @Override
    public ImgCode CreateCode(ServletWebRequest request) {

        return null;
    }
}
