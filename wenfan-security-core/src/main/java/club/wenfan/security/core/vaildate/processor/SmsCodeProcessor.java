package club.wenfan.security.core.vaildate.processor;

import club.wenfan.security.core.properties.SecurityConstants;
import club.wenfan.security.core.properties.SmsCodeProperties;
import club.wenfan.security.core.vaildate.code.VaildateGenerator;
import club.wenfan.security.core.vaildate.code.ValidateCode;
import club.wenfan.security.core.vaildate.processor.impl.AbstractVaildateCodeProcessor;
import club.wenfan.security.core.vaildate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 15:13
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractVaildateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode vaildateCode) throws Exception {
        String paramName=SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        String mobile=ServletRequestUtils.getRequiredStringParameter(request.getRequest(),paramName);
        smsCodeSender.send(mobile,vaildateCode.getCode());
    }
}
