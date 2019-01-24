package club.wenfan.security.core.vaildate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/31 10:25
 */
public class ValidateCode {

    private String code;
    private LocalDateTime expireTime;

    public boolean isExpired(){

        return LocalDateTime.now().isAfter(expireTime);
    }

    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime =LocalDateTime.now().plusSeconds(expireTime) ;
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
