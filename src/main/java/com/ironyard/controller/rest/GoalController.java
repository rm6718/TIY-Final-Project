package com.ironyard.controller.rest;

import com.ironyard.data.Goal;
import com.ironyard.repos.GoalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by reevamerchant on 11/7/16.
 */
@RestController
@RequestMapping(path = "/rest/goal")
public class GoalController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoalRepository goalRepository;


    /**
     * Save the goal to the list
     * @param aGoal
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Goal save(@RequestBody Goal aGoal) {
        log.debug("Begin save:" + aGoal);
        goalRepository.save(aGoal);
        Goal found = goalRepository.findOne(aGoal.getId());
        log.debug("End save:" + aGoal);
        return found;
    }


    /**
     * Update goal
     * @param aGoal
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Goal update(@RequestBody Goal aGoal) {
        log.debug("Begin update:" + aGoal);
        goalRepository.save(aGoal);
        Goal found = goalRepository.findOne(aGoal.getId());
        log.debug("End update:" + found);
        return found;
    }


    /**
     * Get the goal from the list by id and return it
     * @param id
     * @return
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Goal show(@PathVariable Long id) {
        log.debug("Begin show:" + id);
        Goal found = goalRepository.findOne(id);
        log.debug("End show:" + found);
        return found;
    }


    /**
     * List the goals matching the request
     * @param page
     * @param size
     * @param sortby
     * @param direction
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Goal> listAll(@RequestParam("page") Integer page,
                                  @RequestParam("size") Integer size,
                                  @RequestParam(value = "sortby", required = false) String sortby,
                                  @RequestParam(value = "dir", required = false) Sort.Direction direction) {
        log.debug(String.format("Begin listAll (page:%s, size:%s, sortby:%s, dir:%s):", page, size, sortby, direction));

        //Default Sort property
        if (sortby == null) {
            sortby = "item";
        }

        //Default Sort direction
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        Iterable<Goal> found = goalRepository.findAll(pr);
        log.debug(String.format("End listAll: %s", found));

        return found;
    }


    /**
     * Delete the specified goal based on id
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Goal delete(@PathVariable Long id) {
        log.debug((String.format("Begin delete: %s", id)));
        Goal deleted = goalRepository.findOne(id);
        goalRepository.delete(id);
        log.debug(String.format("End delete: %s", deleted));
        return deleted;
    }


    @ExceptionHandler(value = Throwable.class)
    public String errorHandler(Throwable e) {
        log.error("Error in GoalController", e);
        return "You have done something wrong. Try again.";
    }

}
