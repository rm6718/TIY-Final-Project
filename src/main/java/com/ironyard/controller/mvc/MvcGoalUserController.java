package com.ironyard.controller.mvc;

import com.ironyard.repos.GoalRepository;
import com.ironyard.repos.GoalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by reevamerchant on 11/10/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/user")
public class MvcGoalUserController {

    @Autowired
    GoalUserRepository goalUserRepository = null;

    @Autowired
    GoalRepository goalRepository = null;



}
