package club.wenfan.security.core.vaildate.controller;

import club.wenfan.security.core.properties.SecurityConstants;
import club.wenfan.security.core.vaildate.ValidateCodeProcessorHolder;
import club.wenfan.security.core.vaildate.code.ImgCode;
import club.wenfan.security.core.vaildate.code.VaildateGenerator;
import club.wenfan.security.core.vaildate.code.ValidateCode;
import club.wenfan.security.core.vaildate.processor.ValidateCodeProcessor;
import club.wenfan.security.core.vaildate.sms.SmsCodeSender;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/31 10:32
 */
@RestController
public class VaildateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder holder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        holder.findValidateCodeProcessor(type).create(new ServletWebRequest(request,response));
    }
}
