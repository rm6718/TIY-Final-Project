package com.ironyard.controller.rest;

import com.ironyard.data.GoalUser;
import com.ironyard.repos.GoalUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by reevamerchant on 11/10/16.
 */
@RestController
@RequestMapping(path = "rest/user")
public class GoalUserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoalUserRepository goalUserRepository;


    /**
     * Save the selected user
     * @param aUser
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public GoalUser save(@RequestBody GoalUser aUser) {
        log.debug("Begin save:" + aUser);
        goalUserRepository.save(aUser);
        GoalUser found = goalUserRepository.findOne(aUser.getId());
        log.debug("End save:" + aUser);
        return found;
    }


    /**
     * Updates specific user
     * @param aUser
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public GoalUser update(@RequestBody GoalUser aUser) {
        log.debug("Begin update:" + aUser);
        goalUserRepository.save(aUser);
        GoalUser found = goalUserRepository.findOne(aUser.getId());
        log.debug("End update:" + found);
        return found;
    }


    /**
     * Gets user by id and returns it
     * @param id
     * @return
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public GoalUser show(@PathVariable Long id) {
        log.debug("Begin show:" + id);
        GoalUser found = goalUserRepository.findOne(id);
        log.debug("End show:" + found);
        return found;
    }


    /**
     * Lists the users that match the request
     * @param page
     * @param size
     * @param sortby
     * @param direction
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<GoalUser> listAll(@RequestParam("page") Integer page,
                                      @RequestParam("size") Integer size,
                                      @RequestParam(value = "sortby", required = false) String sortby,
                                      @RequestParam(value = "dir", required = false) Sort.Direction direction) {
        log.debug(String.format("Begin listAll (page:%s, size:%s, sortby:%s, dir:%s):", page,size,sortby,direction));

        //DEFAULT Sort property
        if (sortby == null) {
            sortby = "displayName";
        }

        //DEFAULT Sort direction
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        Iterable<GoalUser> found = goalUserRepository.findAll(pr);
        log.debug(String.format("End listAll: %s", found));

        return found;
    }


    /**
     * Deletes the specific user by id
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public GoalUser delete(@PathVariable Long id) {
        log.debug(String.format("Begin delete: %s", id));
        GoalUser deleted = goalUserRepository.findOne(id);
        goalUserRepository.delete(id);
        log.debug(String.format("End delete: %s", deleted));
        return deleted;
    }


    /**
     * Catches any errors that this controller may have
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public String errorHandler(Throwable e) {
        log.error("Error in GoalUserController", e);
        return "You have done something wrong. Try again.";
    }

}
