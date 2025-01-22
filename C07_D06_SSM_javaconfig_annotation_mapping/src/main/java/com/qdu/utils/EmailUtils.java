package com.qdu.utils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import java.util.Random;

public class EmailUtils {

    public static String sendEmailCode(String targetEmail) {
        String code1 = null;
        System.setProperty("https.protocols", "TLSv1.2");
        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        try {
            // 创建邮箱对象
            SimpleEmail mail = new SimpleEmail();
            // 设置发送邮件的服务器
            mail.setHostName("smtp.qq.com");
            mail.setSmtpPort(465);
            // 邮箱认证信息
            mail.setAuthentication("1286363621@qq.com", "gfwgzkoglkfrbafd");
            // 发送者邮箱和昵称
            mail.setFrom("1286363621@qq.com", "BBS中国");
            // 使用安全链接
            mail.setSSLOnConnect(true);
            // 接收用户的邮箱
            mail.addTo(targetEmail);
            // 邮件的主题
            mail.setSubject("注册验证码");

            // 生成验证码
            Random random = new Random();
            code1 = String.valueOf(100000 + random.nextInt(900000));


            // 设置邮件内容
            String htmlContent = "<html><body style='font-family: Arial, sans-serif; background-color: #f4f7fa;'>" +
                    "<h2 style='color: #4CAF50;'>注册验证码</h2>" +
                    "<p style='font-size: 16px; color: #333;'>您的验证码是：</p>" +
                    "<div style='font-size: 24px; font-weight: bold; color: #FF6347; padding: 10px 20px; background-color: #f0f0f0; border-radius: 5px;'>" + code1 + "</div>" +
                    "<p style='font-size: 12px; color: #888;'>（2分钟内有效）</p>" +
                    "<hr style='border: 1px solid #eee;' />" +
                    "<footer style='font-size: 10px; color: #ccc;'>BBS中国</footer>" +
                    "</body></html>";

            // 设置邮件正文为HTML
            mail.setMsg(htmlContent);
            // 发送邮件
            mail.send();
            return code1;
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return code1;
    }

//    public static void main(String[] args) {
//        String code = sendEmailCode("1286363621@qq.com");
//        System.out.println(code);
//    }
}
