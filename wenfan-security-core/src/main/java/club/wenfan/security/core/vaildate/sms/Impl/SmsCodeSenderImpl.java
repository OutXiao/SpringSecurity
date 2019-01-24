package club.wenfan.security.core.vaildate.sms.Impl;

import club.wenfan.security.core.vaildate.sms.SmsCodeSender;

/**
 * @author:wenfan
 * @description:
 *
 *  发送短信的默认配置
 *
 * @data: 2019/1/21 10:15
 */


public class SmsCodeSenderImpl implements SmsCodeSender {


    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机号"+mobile+"发送"+code);
    }
}
