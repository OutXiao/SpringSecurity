package club.wenfan.security.core.vaildate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/31 21:40
 */
public class VaildateCodeException extends AuthenticationException {

    public VaildateCodeException(String msg) {
        super(msg);
    }
}
