package com.edu.chdtu.conference.controller;

import com.edu.chdtu.conference.model.Member;
import com.edu.chdtu.conference.model.dto.MemberDto;
import com.edu.chdtu.conference.model.enums.Group;
import com.edu.chdtu.conference.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MembersController {

    Logger logger = LoggerFactory.getLogger(MembersController.class);

    @Autowired
    MemberService memberService;

    @PreAuthorize("hasPermission(#eventId, 'view_members')")
    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public List<Member> getMembers(@PathVariable("eventId") Integer eventId) {
        return memberService.findAllBy("event.id", eventId);
    }

    @PreAuthorize("hasPermission(#eventId, 'edit_members')")
    @RequestMapping(value = "/remove/{memberEmail}/{eventId}", method = RequestMethod.GET)
    public void remove(@PathVariable("eventId") Integer eventId,
                       @PathVariable("memberEmail") String memberEmail) {
        memberService.delete(memberService.getByEventId(eventId, memberEmail));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/leave/{eventId}", method = RequestMethod.GET)
    public void leaveGroup(@PathVariable("eventId") Integer eventId) {
        memberService.delete(memberService.getByEventId(eventId, SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/join/{eventId}", method = RequestMethod.POST)
    public void joinToEvent(@PathVariable("eventId") Integer eventId) {
        memberService.createMember(eventId, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PreAuthorize("hasPermission(#eventId, 'edit_members')")
    @RequestMapping(value = "/add/{memberEmail}/{eventId}", method = RequestMethod.GET)
    public Member addMember(@PathVariable("eventId") Integer eventId,
                            @PathVariable("memberEmail") String email) {
        return memberService.addToEvent(eventId, email);
    }

    @PreAuthorize("hasPermission(#eventId, 'view_members')")
    @RequestMapping(value = "/request/{eventId}", method = RequestMethod.GET)
    public Long requestCount(@PathVariable("eventId") Integer eventId) {
        List<Member> members = memberService.findAllBy("event.id", eventId);
        return members.stream().filter(member -> member.getUserGroup().getName().equals(Group.WAIT.name())).count();
    }

    @PreAuthorize("hasPermission(#memberDto.eventId, 'edit_members')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Member update(@RequestBody MemberDto memberDto) {
       return memberService.update(memberDto);
    }
}
