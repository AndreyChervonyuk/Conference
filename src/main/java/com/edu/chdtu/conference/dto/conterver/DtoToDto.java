package com.edu.chdtu.conference.dto.conterver;


import com.edu.chdtu.conference.dto.MailDto;
import com.edu.chdtu.conference.dto.NotificationDto;
import com.edu.chdtu.conference.model.Member;

import java.util.List;


public class DtoToDto {

    public static MailDto convert(NotificationDto notificationDto, List<Member> members) {

        String[] setTo = new String[members.size()];

        for(int i= 0; i < setTo.length; i++) {
            setTo[i] = members.get(i).getUser().getEmail();
        }

        return new MailDto (
                notificationDto.getSubject(),
                notificationDto.getText(),
                notificationDto.getEventId(),
                setTo,
                notificationDto.getFileAttachment()
        );
    }
}
