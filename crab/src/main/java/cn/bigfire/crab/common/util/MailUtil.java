package cn.bigfire.crab.common.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/11/15  9:47
 * @ Desc   ：邮件发送工具类
 */

public class MailUtil {

    private static String EMAIL_HOST = "smtp.exmail.qq.com";//邮件服务器
    private static String EMAIL_USERNAME = "【邮箱账户】";
    private static String EMAIL_PASSWORD = "【邮箱密码】";
    private static String EMAIL_NICKNAME = "【项目名】·【公司名】";

	private static MimeMessage message;
	private static Session session;
	private static Transport transport;
    private static Properties properties = new Properties();
    /**
     * 
     * 发送邮件
     * @param toEmail  收件人地址
     * @param subject   邮件主题     
     * @param sendHtml  邮件内容
     *            
     */
    public static void send(String toEmail,String subject, String sendHtml) {
    	try {
            session = Session.getInstance(properties);
            session.setDebug(false);                                                        //关闭调试信息
            message = new MimeMessage(session);
            // 发件人
            message.setFrom(EMAIL_NICKNAME.contains("<"+EMAIL_USERNAME+">") ?
                    new InternetAddress(EMAIL_NICKNAME) :
                    new InternetAddress(EMAIL_USERNAME,EMAIL_NICKNAME));
            InternetAddress to = new InternetAddress(toEmail);                              // 收件人
            message.setRecipient(Message.RecipientType.TO, to);                             //还可以有CC、BCC
            message.setSubject(subject);                                                    // 邮件主题
            message.setContent(sendHtml, "text/html;charset=UTF-8");                  // 邮件内容,也可以使纯文本"text/plain"
            message.saveChanges();                                                          // 保存邮件
            transport = session.getTransport("smtp");
            transport.connect(EMAIL_HOST, EMAIL_USERNAME, EMAIL_PASSWORD);                  // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.sendMessage(message, message.getAllRecipients());                     // 发送
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(transport!=null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 发送邮件
     * @param toEmail  收件人地址     
     * @param subject       邮件主题     
     * @param sendHtml      邮件内容
     * @param attachment    附件      
     */
    public static void send(String toEmail,String subject, String sendHtml, File attachment) {
    	try {
            session = Session.getInstance(properties);
            session.setDebug(false);                                                         //关闭调试信息
            message = new MimeMessage(session);
            // 发件人
            if (EMAIL_NICKNAME.contains("<"+EMAIL_USERNAME+">"))
                message.setFrom(new InternetAddress(EMAIL_NICKNAME)) ;
            else message.setFrom(new InternetAddress(EMAIL_USERNAME,EMAIL_NICKNAME));
            InternetAddress to = new InternetAddress(toEmail);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject(subject);                                                    // 邮件主题
            BodyPart contentPart = new MimeBodyPart();                                      // 添加邮件正文
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");

            Multipart multipart = new MimeMultipart();                                      // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            multipart.addBodyPart(contentPart);
            if (attachment != null) {                                                       // 添加附件的内容
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }
            message.setContent(multipart);                                                  // 将multipart对象放到message中
            message.saveChanges();                                                          // 保存邮件
            transport = session.getTransport("smtp");
            transport.connect(EMAIL_HOST, EMAIL_USERNAME, EMAIL_PASSWORD);                  // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.sendMessage(message, message.getAllRecipients());                     // 发送
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
	
}
