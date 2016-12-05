package com.ironyard.controller.mvc;

import com.ironyard.data.Goal;
import com.ironyard.data.GoalUser;
import com.ironyard.repos.GoalRepository;
import com.ironyard.repos.GoalUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GoalUserRepository goalUserRepository = null;

    @Autowired
    GoalRepository goalRepository = null;


    /**
     * Adds an already created goal (specified by the id) to your list of accomplished goals
     * @param id
     * @return
     */
    @RequestMapping(value = "accomplished/add", method = RequestMethod.GET)
    public String addAccomplished(@RequestParam("id") Long id){

        String destination = "redirect:/mvc/secure/goals/all";

        // fetch specific goal that user wants to mark as accomplished
        Goal accomplishedGoal = goalRepository.findOne(id);
        log.debug("Begin add to accomplished goals list:" + accomplishedGoal);

        // update goal to accomplished (true)
        accomplishedGoal.setAccomplished(true);

        // save goal
        goalRepository.save(accomplishedGoal);
        log.debug("End add to accomplished goals list:" + accomplishedGoal);

        // forward to list all goals controller
        return destination;
    }


    /**
     * Removes a goal by id from the accomplished goals list
     * and moves it back to the list of unaccomplished goals
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "accomplished/remove", method = RequestMethod.GET)
    public String removeFromAccomplished(@RequestParam("id") Long id){

        String destination = "redirect:/mvc/secure/goals/all";

        Goal found = goalRepository.findOne(id);
        log.debug("Begin removing goal:" + found + "from the list of accomplished goals");

        Goal goalToRemove = null;
        if (found.getId() == id){
            goalToRemove = found;
        }

        if (goalToRemove != null){
            found.setAccomplished(false);
        }

        goalRepository.save(found);
        log.debug("Finish removing goal:" + found + "from the list of accomplished goals");

        return destination;
    }



}
