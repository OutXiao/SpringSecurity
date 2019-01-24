package club.wenfan.security.core.vaildate.processor;

import club.wenfan.security.core.vaildate.code.ImgCode;
import club.wenfan.security.core.vaildate.processor.impl.AbstractVaildateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 15:25
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractVaildateCodeProcessor<ImgCode> {

    @Override
    protected void send(ServletWebRequest request, ImgCode imgCode) throws Exception {
        ImageIO.write(imgCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }

}
