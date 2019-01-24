package club.wenfan.security.core.vaildate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/31 10:25
 */
public class ImgCode extends ValidateCode {


    public ImgCode(BufferedImage image,String code,int expireTime){
        super(code,expireTime);
        this.image=image;
    }
    public ImgCode(BufferedImage image,String code,LocalDateTime expireTime){
        super(code,expireTime);
        this.image=image;
    }

    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }


}
