package club.wenfan.security.browser;

import club.wenfan.security.authentication.WenfanAuthenticationFailurelHandler;
import club.wenfan.security.authentication.WenfanAuthenticationSuccessHandler;
import club.wenfan.security.core.authentication.AbstractChannelSecurityConfig;
import club.wenfan.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import club.wenfan.security.core.properties.SecurityConstants;
import club.wenfan.security.core.properties.SecurityProperties;
import club.wenfan.security.core.vaildate.config.VaildateCodeSecurityConfig;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/18 17:35
 */
@Configuration
public class BrowserSecurityConfig  extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private VaildateCodeSecurityConfig vaildateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

            http.apply(vaildateCodeSecurityConfig)
                .and()
                //加入smsCodeAuthenticationSecurityConfig的配置类
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getRememberSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL
                        ,securityProperties.getSignUrl()  //注册
                        ,SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE
                        ,securityProperties.getLoginPage()
                        ,SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*"
                        ,"/static/*"
                        ,"/user/regist"
                        ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

}
