package com.dao.imp;

import com.dao.ICommentDao;
import com.entities.Comment;
import com.gnericdao.imp.HibernateGenericDaoImp;

public class CommentDaoImp extends HibernateGenericDaoImp<Comment, Long> implements ICommentDao {

	public CommentDaoImp() {
		super(Comment.class);
	}
}
