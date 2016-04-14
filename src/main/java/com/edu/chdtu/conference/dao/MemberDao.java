package com.edu.chdtu.conference.dao;

import com.edu.chdtu.conference.model.Member;
import com.edu.chdtu.conference.dao.core.GenericDao;

import java.util.List;


public interface MemberDao extends GenericDao<Member, Integer> {
    Member getByEventId(int id, String memberEmail);
}
