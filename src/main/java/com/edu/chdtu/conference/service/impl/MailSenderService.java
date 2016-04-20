package com.edu.chdtu.conference.service.impl;

import com.edu.chdtu.conference.dao.DocumentsDao;
import com.edu.chdtu.conference.dao.EmailDao;
import com.edu.chdtu.conference.dao.MemberDao;
import com.edu.chdtu.conference.dao.UserDao;
import com.edu.chdtu.conference.dao.impl.EmailDaoImpl;
import com.edu.chdtu.conference.dto.conterver.DtoToDto;
import com.edu.chdtu.conference.dto.conterver.DtoToEntity;
import com.edu.chdtu.conference.model.Email;
import com.edu.chdtu.conference.model.Member;
import com.edu.chdtu.conference.model.SendTo;
import com.edu.chdtu.conference.model.User;
import com.edu.chdtu.conference.dto.MailDto;
import com.edu.chdtu.conference.dto.NotificationDto;
import com.edu.chdtu.conference.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MailSenderService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MemberDao memberDao;

    @Autowired
    UserDao userDao;

    @Autowired
    DocumentsDao documentsDao;

    @Autowired
    EmailDao emailDao;

    private MimeMessage buildMessage(MailDto mailDto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multipart = new MimeMultipart();
        User sender = userDao.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        try {
            EmailUtil.setSubject(mailDto, sender, message);

            if (mailDto.getFileAttachment() != null) {
                for (Integer documentsId : mailDto.getFileAttachment()) {
                    EmailUtil.attachFile(multipart, documentsDao.findById(documentsId).getPath());
                }
            }

            EmailUtil.setEmailText(multipart, mailDto.getText());
            message.setContent(multipart);

        } catch (UnsupportedEncodingException | MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public void sendEmail(MailDto mailDto) {
        createEmail(mailDto);
        javaMailSender.send(buildMessage(mailDto));
    }

    public void createEmail(MailDto mailDto) {
        Email email = DtoToEntity.convert(mailDto);
        Set<SendTo> sendTos = new HashSet<>();

        for (String member : mailDto.getSetTo()) {
            sendTos.add(
                    new SendTo(memberDao.getByEventId(mailDto.getEventId(), member), email)
            );
        }

        email.setSendTo(sendTos);
        email.setAuthor(userDao.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        emailDao.create(email);
    }

    public void sendNotification(NotificationDto notificationDto) {
        List<Member> members = memberDao.findAllBy("event.id", notificationDto.getEventId());
        sendEmail(DtoToDto.convert(notificationDto, members));
    }
}

