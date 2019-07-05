package com.neo.service;

/**
 * 邮件  Service 层  接口
 * @author zhouhb
 * @date 2019/07/01
 *
 */
public interface MailService {

    public void sendSimpleMail(String to, String subject, String content);

    public void sendHtmlMail(String to, String subject, String content);

    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
    
    

}
