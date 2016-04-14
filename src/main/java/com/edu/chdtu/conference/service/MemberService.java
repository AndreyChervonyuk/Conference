package com.edu.chdtu.conference.service;


import com.edu.chdtu.conference.model.Member;
import com.edu.chdtu.conference.model.dto.MemberDto;
import com.edu.chdtu.conference.service.core.GenericService;

public interface MemberService extends GenericService<Member, Integer> {
    Member getByEventId(Integer eventId, String email);
    Member addToEvent(Integer eventId, String email);
    Member createMember(Integer eventId, String email);
    Member update(MemberDto memberDto);
}
