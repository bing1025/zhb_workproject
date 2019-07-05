package com.neo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by summer on 2017/5/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
	
	
	@Value("${mail.receptionMail.addr}")
	private String receptionMailAddr;

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    
    /*
     *     测试   126 邮箱的部分代码
     */
    @Test
    public void testSimpleMail126() throws Exception {
        mailService.sendSimpleMail("ityouknow@126.com","test simple mail"," hello this is simple mail");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("ityouknow@126.com","test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="e:\\tmp\\application.log  --- 找一个本地的文件路径";
        mailService.sendAttachmentsMail("ityouknow@126.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "E:\\zhb_workspace\\spring-boot-mail\\src\\main\\resources\\templates\\emailTemplate.html";
        mailService.sendInlineResourceMail("ityouknow@126.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("ityouknow@126.com","主题：这是模板邮件",emailContent);
    }
    
    /*
     *     测试   个人QQ 邮箱的部分代码  ---  这部分测试通过
     */
    
    @Test
    public void testSimpleMailQQ() throws Exception {
      //  mailService.sendSimpleMail("861218249@qq.com","test simple mail"," hello this is simple mail");
    	// 测试properties配置文件传值
    	mailService.sendSimpleMail(receptionMailAddr,"test simple mail"," 试试邮件开发");
    }
    
    @Test
    public void testHtmlMailQQ() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("861218249@qq.com","test simple mail",content);
    }
    
    @Test
    public void sendAttachmentsMailQQ() {
        String filePath="C:\\Users\\Administrator\\Desktop\\test.txt";   //随便找一个本地的文件
        mailService.sendAttachmentsMail("861218249@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }
    
    
    
}
