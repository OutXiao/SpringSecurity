package club.wenfan.security.core.social.qq.connect;

import club.wenfan.security.core.social.qq.api.QQ;
import club.wenfan.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/24 19:14
 */
public class QQAdapt implements ApiAdapter<QQ> {

    /**
     *  测试服务商是否正常通信
     * @author wenfan
     * @date
     * @param
     * @return
     */

    @Override
    public boolean test(QQ qq) {
        return true;
    }


    @Override
    public void setConnectionValues(QQ qq, ConnectionValues values) {

        QQUserInfo qqUserInfo = qq.getQQUserInfo();
        values.setDisplayName(qqUserInfo.getNickname());
        values.setImageUrl(qqUserInfo.getFigureurl_qq_1());
        values.setProfileUrl(null); //个人主页的url
        values.setProviderUserId(qqUserInfo.getOpenId());

    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    // 更新主页
    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
