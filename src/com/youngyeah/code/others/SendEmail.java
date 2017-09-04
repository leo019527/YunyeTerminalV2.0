package com.youngyeah.code.others;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by 李凌耀 on 2017/9/4.
 */
public class SendEmail {
    public static MimeMessage create(Session session, String email, String content,String myemail)
    {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);     // 创建邮件对象
        try {
            //TODO:修改邮箱地址
            //发件人
            message.setFrom(new InternetAddress(myemail, "云叶科技公司", "UTF-8"));
            //收件人
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, "管理者", "UTF-8"));
            //主题
            message.setSubject("云叶自助云打印", "UTF-8");
            //正文
            message.setContent(content, "text/html;charset=UTF-8");
            //其余信息
            message.setSentDate(new Date());
            message.saveChanges();
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void send(String email,String content)
    {
        String myEmailAccount = "lly_2009.9@163.com";
        String myEmailPassword = "19961120li";
        String myEmailSMTPHost = "smtp.163.com";
        String receiveMailAccount = email;



        try {
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getDefaultInstance(props);
            session.setDebug(false);                                 // 设置为debug模式, 可以查看详细的发送 log

            // 3. 创建一封邮件
            MimeMessage message = SendEmail.create(session,email,content,myEmailAccount);
            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            // 5. 使用 邮箱账号 和 密码 连接邮件服务器
            transport.connect(myEmailAccount, myEmailPassword);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        SendEmail.send("15281070@bjtu.edu.cn","testemail");
    }
}
