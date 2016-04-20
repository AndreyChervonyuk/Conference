package com.edu.chdtu.conference.service.impl;



import com.edu.chdtu.conference.dao.MemberDao;
import com.edu.chdtu.conference.dao.UserDao;
import com.edu.chdtu.conference.dao.UserGroupsDao;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.Member;
import com.edu.chdtu.conference.dto.MemberDto;
import com.edu.chdtu.conference.model.enums.Group;
import com.edu.chdtu.conference.service.MemberService;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import com.edu.chdtu.conference.dao.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends GenericServiceImpl<Member, Integer> implements MemberService {

    private MemberDao memberDao;
    private EventDao eventDao;
    private UserDao userDao;
    private UserGroupsDao userGroupsDao;

    @Autowired
    public MemberServiceImpl(GenericDao<Member, Integer> genericDao,
                             EventDao eventDao,
                             UserDao userDao,
                             UserGroupsDao userGroupsDao) {
        super(genericDao);
        this.memberDao = (MemberDao) genericDao;
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.userGroupsDao = userGroupsDao;
    }


    @Override
    public Member getByEventId(Integer id, String memberEmail) {
        Member member = memberDao.getByEventId(id, memberEmail);
        member.getUserGroup().getEventPermissions();
        return member;
    }

    @Override
    public Member createMember(Integer eventId, String email) {
        Member member = new Member(
                        userDao.findByEmail(email),
                        eventDao.findById(eventId),
                        userGroupsDao.findBy("name", Group.NOT_CONFIRMED.name())
        );
        create(member);
        return member;
    }

    // TODO check if member send request
    @Override
    public Member addToEvent(Integer eventId, String email) {
        Member member = memberDao.getByEventId(eventId, email);
        member.setUserGroup(userGroupsDao.findBy("name", Group.MEMBERS.name()));
        memberDao.update(member);
        return member;
    }

    @Override
    public Member update(MemberDto memberDto) {
        Member member = memberDao.getByEventId(memberDto.getEventId(), memberDto.getUserEmail());
        member.setUserGroup(userGroupsDao.findById(memberDto.getUserGroupId()));
        memberDao.update(member);
        return member;
    }
}
