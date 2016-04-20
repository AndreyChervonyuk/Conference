package com.edu.chdtu.conference.dto.conterver;

import com.edu.chdtu.conference.dto.MailDto;
import com.edu.chdtu.conference.model.Email;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DtoToEntity {

    public static Email convert(MailDto mailDto) {
        return new Email(
                mailDto.getSubject(),
                mailDto.getText(),
                new Date()
        );
    }

}
