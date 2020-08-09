package com.zr.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemo07TaskApplicationTests {
    @Autowired
    JavaMailSenderImpl javaMailSender;

    /**
     * 发送文本邮件
     */
    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");
        message.setTo("1520633673@qq.com");
        message.setFrom("randylee1130@163.com");
        javaMailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     */
    @Test
    public void test() throws Exception {
        //创建一个复杂的邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //第二个参数是上传邮件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("通知-今晚开会");
        //第二个参数是否解析为html
        helper.setText("<p style='color:red'>今晚7:30开会</p>", true);
        //收件人
        helper.setTo("1520633673@qq.com");
        //发件人
        helper.setFrom("randylee1130@163.com");
        //第一个参数：发送过去的文件名。第二个参数：本地的文件地址
        helper.addAttachment("1.jpg", new File("F:\\1.jpg"));
        helper.addAttachment("2.jpg", new File("F:\\2.jpg"));
        javaMailSender.send(mimeMessage);
    }
}
