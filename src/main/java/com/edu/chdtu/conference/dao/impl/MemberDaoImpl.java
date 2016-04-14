package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.MemberDao;
import com.edu.chdtu.conference.model.Member;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImpl extends GenericDaoImpl<Member, Integer> implements MemberDao {

    Logger logger = LoggerFactory.getLogger(getEntity());

    public MemberDaoImpl() {
        super(Member.class);
    }

    @Override
    public Member getByEventId(int id, String memberEmail) {
        logger.debug("load member by event id :" + id  + " and member email : " + memberEmail);

        return (Member) createCriteria()
                .createAlias("event", "e")
                .createAlias("user", "u")
                .add(Restrictions.eq("e.id", id))
                .add(Restrictions.eq("u.email", memberEmail))
                .uniqueResult();
    }

}
