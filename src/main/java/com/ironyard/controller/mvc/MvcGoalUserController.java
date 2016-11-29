package com.ironyard.controller.mvc;

import com.ironyard.data.Goal;
import com.ironyard.data.GoalUser;
import com.ironyard.repos.GoalRepository;
import com.ironyard.repos.GoalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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



    @RequestMapping(value = "accomplished/add", method = RequestMethod.GET)
    public String addAccomplished(@RequestParam("id") Long id, HttpServletRequest request){

        String destination = "redirect:/mvc/secure/goals/all";

        // fetch specific goal that user wants to mark as accomplished
        Goal accomplishedGoal = goalRepository.findOne(id);

        // update goal to accomplished (true)
        accomplishedGoal.setAccomplished(true);

        // save goal
        goalRepository.save(accomplishedGoal);

        // forward to list all goals controller
        return destination;
    }


    @RequestMapping(value = "accomplished/remove", method = RequestMethod.GET)
    public String removeFromAccomplished(@RequestParam("id") Long id, HttpServletRequest request){

        String destination = "redirect:/mvc/secure/goals/all";

        Goal found = goalRepository.findOne(id);

        Goal goalToRemove = null;
        if (found.getId() == id){
            goalToRemove = found;
        }

        if (goalToRemove != null){
            found.setAccomplished(false);
        }

        goalRepository.save(found);

        return destination;
    }



}
