package com.ironyard.controller.mvc;

import com.ironyard.data.GoalUser;
import com.ironyard.data.Permission;
import com.ironyard.repos.GoalUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by reevamerchant on 11/10/16.
 */
@Controller
@RequestMapping(path = "mvc/open")
public class MvcLoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoalUserRepository goalUserRepository;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "password", required = false) String password,
                        @RequestParam(value = "username", required = false) String username,
                        HttpServletRequest request) {
        log.info("Login attempt by:" + username);
        String destination = "open/login";
        GoalUser found = goalUserRepository.findByUsernameAndPassword(username, password);
        if (found != null) {
            request.getSession().setAttribute("user", found);

            // add permissions to session
            HashMap<String, String> permsForThisUser = new HashMap<>();
            for (Permission p: found.getAbilities()){
                permsForThisUser.put(p.getKey(), p.getKey());
            }
            request.getSession().setAttribute("user_loggedin_perms", permsForThisUser);

            destination = "redirect:/mvc/secure/goals/all";
            log.info("found user:" + found.getId());
        }
        log.info("Login attempt result:" + destination);
        return destination;

    }


    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        String destination = "/open/login";
        GoalUser found = (GoalUser) request.getSession().getAttribute("user");
        if (found != null) {
            log.info("Logging out user with id:" + found.getId());
        }
        request.getSession().invalidate();
        return destination;
    }

}
