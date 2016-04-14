package com.edu.chdtu.conference.controller;

import com.edu.chdtu.conference.model.dto.MailDto;
import com.edu.chdtu.conference.service.impl.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailSenderController {

    @Autowired
    MailSenderService mailSenderService;

    @PreAuthorize("hasPermission(#mailDto.eventId, 'send_email')")
    @RequestMapping(method = RequestMethod.POST)
    public void sendMail(@RequestBody MailDto mailDto) {
        mailSenderService.sendEmail(mailDto);
    }
}
