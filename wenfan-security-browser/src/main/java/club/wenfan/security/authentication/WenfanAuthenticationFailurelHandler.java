package club.wenfan.security.authentication;

import club.wenfan.security.core.properties.LoginType;
import club.wenfan.security.core.properties.SecurityProperties;
import club.wenfan.security.support.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/30 11:24
 */
@Component("wenfanAuthenticationFailureHandler")
public class WenfanAuthenticationFailurelHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        logger.info("登陆失败");
        if(LoginType.JSON.equals(securityProperties.getLoginType())) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse( e.getMessage())));
        }else {
            super.onAuthenticationFailure(request,response,e);
        }

    }
}
