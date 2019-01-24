package club.wenfan.security.core.vaildate;

import club.wenfan.security.core.properties.SecurityConstants;

import java.security.Security;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/22 9:24
 */
public enum ValidateCodeType {

    /**
     *  短信验证码
     * @author wenfan
     */

    SMS{
        @Override
        public String getParamNameOnValidate(){
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    /**
     * 图片验证码
     * @author wenfan
     */
    IMAGE{
        @Override
        public String getParamNameOnValidate(){
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    public abstract String getParamNameOnValidate();
}
