package com.example.mallxx.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

import java.util.concurrent.TimeUnit;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    // 创建一个缓存，缓存验证码，设置过期时间为5分钟
    private final Cache<String, String> captchaCache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)  // 验证码过期时间
            .maximumSize(1000)  // 最大缓存数
            .build();

    // 发送测试邮件
    public void sendTestEmail(String toEmail,String text) {
        // 创建邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1147223321@qq.com");  // 发送者邮箱
        message.setTo(toEmail);                   // 收件人邮箱
        message.setSubject("验证码");         // 邮件主题
        message.setText(text); // 邮件正文

        // 发送邮件
        javaMailSender.send(message);

        // 打印日志或其他操作
        //System.out.println("Test email sent to: " + toEmail);
    }

    // 保存验证码到缓存
    public void saveCaptcha(String email, String captcha) {
        captchaCache.put(email, captcha);
    }

    public boolean validateCaptcha(String email, String captcha) {
        String storedCaptcha = captchaCache.getIfPresent(email);
        if (storedCaptcha != null && storedCaptcha.equals(captcha)) {
            // 验证成功，清除缓存中的验证码

            return true;
        }
        return false;
    }

    public String generateCaptcha() {
        // 定义验证码的字符集，只包含数字
        String digits = "0123456789";
        // 创建一个SecureRandom实例用于生成随机数
        SecureRandom random = new SecureRandom();
        StringBuilder captcha = new StringBuilder();

        // 生成6位数字验证码
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(digits.length());
            captcha.append(digits.charAt(index));
        }

        return captcha.toString();
    }

}

