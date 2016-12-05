package com.ironyard.controller.mvc;

import com.ironyard.data.Goal;
import com.ironyard.repos.GoalRepository;
import com.ironyard.repos.GoalUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by reevamerchant on 11/7/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/goals")
public class MvcGoalController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GoalUserRepository goalUserRepository;

    @Autowired
    GoalRepository goalRepository;


    /**
     * Gets all the user's goals from the database
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        String destination = "/secure/goals";

        // gets all players
        Iterable<Goal> goals = goalRepository.findAll();
        log.debug("Begin list:" + goals);

        // puts them in a model called "goals"
        model.addAttribute("goals", goals);
        log.debug("End list:" + goals);

        //sends the model to the home page
        return destination;
    }


    /**
     * Lists all the goals that a user has in their database
     * and separates them based on whether they are accomplished (boolean = true)
     * or if they belong in the unaccomplished goals list (boolean = false)
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String allGoals(Model model){

        Iterable<Goal> aPageOfCompletedGoals = goalRepository.findByAccomplished(true);
        log.debug("Begin list of completed goals:" + aPageOfCompletedGoals);

        Iterable<Goal> aPageOfIncompleteGoals = goalRepository.findByAccomplished(false);
        log.debug("Begin list of uncompleted goals:" + aPageOfIncompleteGoals);


        model.addAttribute("completed_goals", aPageOfCompletedGoals.iterator());
        model.addAttribute("notcompleted_goals", aPageOfIncompleteGoals.iterator());
        log.debug("End list of completed goals:" + aPageOfCompletedGoals);
        log.debug("End list of uncompleted goals:" + aPageOfIncompleteGoals);


        return "/secure/goals";
    }


    /**
     * User creates a new goal for their list
     *
     * @param item
     * @param dateToBeCompleted
     * @param comments
     * @return
     */
    @RequestMapping(value = "list/create", method = RequestMethod.POST)
    public String save(@RequestParam("item") String item,
                         @RequestParam("dateToBeCompleted") String dateToBeCompleted,
                         @RequestParam("comments") String comments) {

        String destination = "redirect:/mvc/secure/goals/all";

        Goal newGoal = new Goal();
        newGoal.setItem(item);
        newGoal.setDateToBeCompleted(dateToBeCompleted);
        newGoal.setComments(comments);

        goalRepository.save(newGoal);
        log.debug("Finish saving:" + newGoal);

        return destination;
    }



    /**
     * User deletes a goal from their list based on the specific id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "list/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id) {
        String destination = "redirect:/mvc/secure/goals/all";

        Goal foundGoal = goalRepository.findOne(id);
        log.debug("Begin delete:" + foundGoal);

        goalRepository.delete(foundGoal);
        log.debug("End delete:" + foundGoal);

        return destination;
    }



    /**
     * Gets the goal from the database based on the id
     * and puts them into a model and sends them to the jsp page used to edit goals
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "list/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id,
                       Model model) {
        String destination = "/secure/edit";

        Goal editGoal = goalRepository.findOne(id);
        log.debug("Begin retrieving" + editGoal + "from database");

        model.addAttribute("item", editGoal.getItem());
        model.addAttribute("dateToBeCompleted", editGoal.getDateToBeCompleted());
        model.addAttribute("comments", editGoal.getComments());
        model.addAttribute("id", editGoal.getId());

        log.debug("End retrieving" + editGoal + "from database");

        return destination;
    }


    /**
     * Updates the database with any changes made to the edited goal
     * and saves the edited goal
     *
     * @param id
     * @param item
     * @param dateToBeCompleted
     * @param comments
     * @return
     */
    @RequestMapping(value = "list/save", method = RequestMethod.POST)
    public String save(@RequestParam("id") Long id,
                       @RequestParam("item") String item,
                       @RequestParam("dateToBeCompleted") String dateToBeCompleted,
                       @RequestParam("comments") String comments) {

        String destination = "redirect:/mvc/secure/goals/all";

        Goal editGoal = goalRepository.findOne(id);
        log.debug("Begin editing:" + editGoal);

        editGoal.setItem(item);
        editGoal.setDateToBeCompleted(dateToBeCompleted);
        editGoal.setComments(comments);

        goalRepository.save(editGoal);
        log.debug("Finish editing:" + editGoal);

        return destination;
    }

}
