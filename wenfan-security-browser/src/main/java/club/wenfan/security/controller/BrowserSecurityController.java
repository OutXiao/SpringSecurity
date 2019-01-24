package club.wenfan.security.controller;

import club.wenfan.security.core.properties.SecurityProperties;
import club.wenfan.security.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/28 23:07
 */
@RestController
@ResponseStatus(code = HttpStatus.UNAUTHORIZED) //返回状态吗
public class BrowserSecurityController {

    private RequestCache requestCache=new HttpSessionRequestCache();

    private Logger logger=LoggerFactory.getLogger(getClass());

    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;


    /**
     * 当需要身份认证时，跳转到这里
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @RequestMapping("/authenticaion/require")
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException{

        SavedRequest savedRequest=requestCache.getRequest(request,response);
        if(savedRequest != null){
            String target=savedRequest.getRedirectUrl();
            logger.info("引发跳转的url："+target);
            if(StringUtils.endsWithIgnoreCase(target,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getLoginPage());
            }

        }
        // 访问需要授权的url 不是以html 结尾，返回会如下信息
        return new SimpleResponse("访问的服务需要身份认证，请引导到用户登陆");
    }

}
