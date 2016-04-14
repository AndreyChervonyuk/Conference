package com.edu.chdtu.conference.service.impl;

import com.edu.chdtu.conference.dao.DocumentsDao;
import com.edu.chdtu.conference.dao.MemberDao;
import com.edu.chdtu.conference.dao.UserDao;
import com.edu.chdtu.conference.model.Member;
import com.edu.chdtu.conference.model.User;
import com.edu.chdtu.conference.model.dto.MailDto;
import com.edu.chdtu.conference.model.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MailSenderService {

    private static String ROOT_DIRECTORY = "C:\\temp";


    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MemberDao memberDao;

    @Autowired
    UserDao userDao;

    @Autowired
    DocumentsDao documentsDao;

    private MimeMessage buildMessage(MailDto mailDto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        User sender = userDao.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Multipart multipart = new MimeMultipart();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            InternetAddress senderMail = new InternetAddress(sender.getEmail());
            senderMail.setPersonal("From: " + sender.getSurname() + " " + sender.getName());
            helper.setFrom(String.valueOf(senderMail));
            helper.setTo(mailDto.getSetTo());
            helper.setSubject(mailDto.getSubject());

            if (mailDto.getFileAttachment() != null) {
                for (Integer documentsId : mailDto.getFileAttachment()) {
                    attachFile(multipart, documentsDao.findById(documentsId).getPath());
                }
            }

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(mailDto.getText());
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

        } catch (UnsupportedEncodingException | MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public void attachFile(Multipart multipart, String path) throws MessagingException {
        DataSource source = new FileDataSource(ROOT_DIRECTORY + path);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(Paths.get(path).getFileName().toString());
        multipart.addBodyPart(messageBodyPart);
    }


    public void sendEmail(MailDto mailDto) {
        javaMailSender.send(buildMessage(mailDto));
    }

    public void sendNotification(NotificationDto notificationDto) {
        List<Member> members = memberDao.findAllBy("event.id", notificationDto.getEventId());
        String[] setTo = new String[members.size()];

        for(int i= 0; i < setTo.length; i++) {
            setTo[i] = members.get(i).getUser().getEmail();
        }

        MailDto mailDto = new MailDto(
                notificationDto.getSubject(),
                notificationDto.getText(),
                notificationDto.getEventId(),
                setTo,
                notificationDto.getFileAttachment()
        );

        sendEmail(mailDto);
    }

}

