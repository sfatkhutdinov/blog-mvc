package edu.umuc.cmsc495.controllers;

import edu.umuc.cmsc495.models.User;
import edu.umuc.cmsc495.models.dao.PostDao;
import edu.umuc.cmsc495.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * Author: stanislav
 * Date: 10/5/19
 * Project: blog-mvc
 * Package: edu.umuc.cmsc495.controllers
 */
public abstract class AbstractController {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected PostDao postDao;

    public static final String userSessionKey = "user_id";

    protected User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : userDao.findByUid(userId);
    }

    protected void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getUid());
    }
}
