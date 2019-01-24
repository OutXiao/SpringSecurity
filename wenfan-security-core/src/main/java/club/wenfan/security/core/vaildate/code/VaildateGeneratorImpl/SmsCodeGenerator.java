package club.wenfan.security.core.vaildate.code.VaildateGeneratorImpl;

import club.wenfan.security.core.properties.SecurityProperties;
import club.wenfan.security.core.vaildate.code.ImgCode;
import club.wenfan.security.core.vaildate.code.VaildateGenerator;
import club.wenfan.security.core.vaildate.code.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 18:33
 */
@Component("smsVaildateGenerator")
public class SmsCodeGenerator implements VaildateGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode CreateCode(ServletWebRequest request) {
        String code=RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getCodeCount());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpiredTime());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
