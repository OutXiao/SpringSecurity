package club.wenfan.security.core.vaildate.config;

import club.wenfan.security.core.properties.SecurityProperties;
import club.wenfan.security.core.vaildate.code.VaildateGenerator;
import club.wenfan.security.core.vaildate.code.VaildateGeneratorImpl.ImgCodeGenerator;
import club.wenfan.security.core.vaildate.sms.Impl.SmsCodeSenderImpl;
import club.wenfan.security.core.vaildate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *  扩展验证码的实现类
 *  重构代码
 *
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 19:05
 */
@Configuration
public class VaildateCodeConfig {

    @Autowired
    private SecurityProperties securityProperties;


    /**
     *  当容器中没有imgCodeGenerator 这个Bean的时候，会主动配置下面的默认Bean
     *  以增量的形式实现变化不
     *
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imgCodeGenerator")
    public VaildateGenerator imgCodeGenerator(){
        ImgCodeGenerator codeGenerator=new ImgCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }


    /**
     *  class形式和 name的方式相同
     * @author wenfan
     * @date
     * @param
     * @return
     */

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new SmsCodeSenderImpl();
    }

}
