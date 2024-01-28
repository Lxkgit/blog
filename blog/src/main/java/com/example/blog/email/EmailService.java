package com.example.blog.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author: lxk
 * @date: 2021/7/25 18:55
 * @description: 邮件服务
 * @modified By:
 */
@Service
public class EmailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 配置发送邮箱
     */
    @Value("${spring.mail.from}")
    private String from;

    /**
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */

    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            //邮件发送人
            helper.setFrom(from);
            //邮件主题
            helper.setSubject(subject);
            //邮件接收人
            helper.setTo(to);
            //邮件内容
            helper.setText(content, true);
            //发送邮件
            mailSender.send(message);
            //日志信息

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
