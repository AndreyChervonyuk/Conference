package com.edu.chdtu.conference.util;


import com.edu.chdtu.conference.dto.MailDto;
import com.edu.chdtu.conference.model.User;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;

public class EmailUtil {

    public static void setEmailText(Multipart multipart, String text) throws MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(text);
        multipart.addBodyPart(messageBodyPart);
    }

    public static void attachFile(Multipart multipart, String path) throws MessagingException {
        DataSource source = new FileDataSource(FileUtil.relativeToAbsolute(path).toString());
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(Paths.get(path).getFileName().toString());
        multipart.addBodyPart(messageBodyPart);
    }

    public static void setSubject(MailDto mailDto, User sender, MimeMessage message) throws MessagingException, UnsupportedEncodingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        InternetAddress senderMail = new InternetAddress(sender.getEmail());
        senderMail.setPersonal("From: " + sender.getSurname() + " " + sender.getName());
        helper.setFrom(String.valueOf(senderMail));
        helper.setTo(mailDto.getSetTo());
        helper.setSubject(mailDto.getSubject());
    }
}
