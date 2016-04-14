package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.CommentsDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsDaoImpl extends GenericDaoImpl<Comment, Integer> implements CommentsDao {

    public CommentsDaoImpl() {
        super(Comment.class);
    }
}
