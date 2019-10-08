package edu.umuc.cmsc495.controllers;

import edu.umuc.cmsc495.models.Post;
import edu.umuc.cmsc495.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: stanislav
 * Date: 10/5/19
 * Project: blog-mvc
 * Package: edu.umuc.blogz.controllers
 */
@Controller
public class PostController extends AbstractController {

    @RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
    public String newPostForm() {
        return "newpost";
    }

    @RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
    public String newPost(HttpServletRequest request, Model model) {
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        HttpSession newSession = request.getSession();
        User author = getUserFromSession(newSession);

        if (title == null || title.isEmpty()) {
            model.addAttribute("error", "Please enter a title for your post");
            return  "newpost";
        }

        if (body == null || body.isEmpty()) {
            model.addAttribute("title", title);
            model.addAttribute("error", "Please add a body to your post");
            return "newpost";
        }

        Post post = new Post(title, body, author);
        postDao.save(post);

        return "redirect:/blog/" + author.getUsername() + "/" + post.getUid();
    }

    @RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
    public String userPosts(@PathVariable String username, Model model) {
        User user = userDao.findByUsername(username);
        List<Post> posts = user.getPosts();

        model.addAttribute("posts",posts);
        return "blog";
    }
}
