package edu.umuc.cmsc495.controllers;

import edu.umuc.cmsc495.models.Post;
import edu.umuc.cmsc495.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author: stanislav
 * Date: 10/5/19
 * Project: blog-mvc
 * Package: edu.umuc.cmsc495.controllers
 */
@Controller
public class BlogController extends AbstractController {

    @RequestMapping(value = "/")
    public String index(Model model) {

        List<User> users = userDao.findAll();
        model.addAttribute("users",users);
        return "index";
    }

    @RequestMapping(value = "/blog")
    public String blogIndex(Model model) {

        List<Post> posts = postDao.findAll();
        model.addAttribute("posts",posts);
        return "blog";
    }

}
