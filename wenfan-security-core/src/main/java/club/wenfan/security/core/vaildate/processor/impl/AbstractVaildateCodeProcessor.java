package club.wenfan.security.core.vaildate.processor.impl;

import club.wenfan.security.core.vaildate.ValidateCodeType;
import club.wenfan.security.core.vaildate.code.VaildateGenerator;
import club.wenfan.security.core.vaildate.code.ValidateCode;
import club.wenfan.security.core.vaildate.exception.VaildateCodeException;
import club.wenfan.security.core.vaildate.processor.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 12:08
 */
public abstract class  AbstractVaildateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    private Logger log= LoggerFactory.getLogger(getClass());
    /**
     *   收集系统中所有的VaildateGenerator的实现的接口
     *   将收集的bean的名字存在String中，bean存在VaildateGenerator
     *
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @Autowired
    private Map<String, VaildateGenerator> validateCodeGenerators;



    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }

    private C generate(ServletWebRequest request){
        String type=getValidateCodeType(request).toString().toLowerCase();
        String generatorName= type + VaildateGenerator.class.getSimpleName();
        log.info("验证码生成器"+generatorName);
        VaildateGenerator vaildateGenerator=validateCodeGenerators.get(generatorName);
        if (vaildateGenerator == null) {
            throw new VaildateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) vaildateGenerator.CreateCode(request);
    }

    /**
     * 保存校验码
     * @author wenfan
     * @date
     * @param
     * @return
     */
    private void save(ServletWebRequest request,C validateCode){
        sessionStrategy.setAttribute(request,getSessionKey(request),validateCode);
        log.info("sessionKey="+getSessionKey(request));
        log.info("sessionValue="+validateCode.getCode());
    }


    /**
     *  发送校验码由子类实现
     * @author wenfan
     * @date
     * @param
     * @return
     */
    protected abstract void send(ServletWebRequest request,C vaildateCode) throws Exception;


    /**
     *  根据请求个url 获取校验码的类型
     * @author wenfan
     * @date
     * @param
     * @return
     */
    public ValidateCodeType getValidateCodeType(ServletWebRequest request){
        String type = StringUtils.substringBefore(getClass().getSimpleName(),"CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());  //   IMAGE/SMS

    }

    public String getSessionKey(ServletWebRequest request){
        return SESSION_KEY_PREFIX+ getValidateCodeType(request).toString().toUpperCase();
    }

    @Override
    public void validate(ServletWebRequest servletWebRequest) {
        ValidateCodeType processorType = getValidateCodeType(servletWebRequest);
        log.info("验证类型"+processorType);
        String sessionKey = getSessionKey(servletWebRequest);
        log.info("验证时的sessionKey="+sessionKey);
        C codeInSession = (C) sessionStrategy.getAttribute(servletWebRequest, sessionKey);
        log.info("验证时的sessionValue="+codeInSession.getCode());
        String codeInRequest ;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),
                    processorType.getParamNameOnValidate());
            log.info("请求中的校验参数："+codeInRequest);
        } catch (ServletRequestBindingException e) {
            throw new VaildateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new VaildateCodeException(processorType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new VaildateCodeException(processorType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(servletWebRequest, sessionKey);
            throw new VaildateCodeException(processorType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new VaildateCodeException(processorType + "验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest, sessionKey);
    }
}
