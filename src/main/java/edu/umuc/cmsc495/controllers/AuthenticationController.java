package edu.umuc.cmsc495.controllers;

import edu.umuc.cmsc495.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Author: stanislav
 * Date: 10/5/19
 * Project: blog-mvc
 * Package: edu.umuc.cmsc495.controllers
 */
@Controller
public class AuthenticationController extends AbstractController {

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupForm() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(HttpServletRequest request, Model model) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verify = request.getParameter("verify");

        boolean validUsername = User.isValidUsername(username);
        boolean validPassword = User.isValidPassword(password);
        boolean validMatch = password.equals(verify);

        if (validUsername == false) {
            model.addAttribute("username_error", "Wrong username, try a different one");
            return "signup";
        }

        if (validPassword == false) {
            model.addAttribute("password_error", "Wrong password");
            return "signup";
        }

        if (validMatch == false) {
            model.addAttribute("verify_error", "Passwords don't match");
            return "signup";
        }

        User user = new User(username, password);
        userDao.save(user);

        HttpSession newSession = request.getSession();
        setUserInSession(newSession, user);
        return "redirect:blog/newpost";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDao.findByUsername(username);

        boolean matchedPassword = user.isMatchingPassword(password);
        if (username == null) {
            model.addAttribute("error", "Your username is empty");
            return "login";
        }

        if (matchedPassword == false) {
            model.addAttribute("username", username);
            model.addAttribute("error","Password did not match");
            return "login";
        } else {
            setUserInSession(request.getSession(), user);
            model.addAttribute("username", username);
            return "redirect:blog/newpost";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
