package club.wenfan.security.authentication;

        import club.wenfan.security.core.properties.LoginType;
        import club.wenfan.security.core.properties.SecurityProperties;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
        import org.springframework.stereotype.Component;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;


/**
 *
 *     登陆成功处理
 * @author:wenfan
 * @description:
 * @data: 2018/12/30 11:05
 */
@Component("wenfanAuthenticationSuccessHandler")
public class WenfanAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest  request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登陆成功");
        if(LoginType.JSON.equals(securityProperties.getLoginType())) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
