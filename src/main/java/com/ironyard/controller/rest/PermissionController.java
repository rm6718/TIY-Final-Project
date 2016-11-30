package com.ironyard.controller.rest;

import com.ironyard.data.Permission;
import com.ironyard.repos.PermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by reevamerchant on 11/20/16.
 */
@RestController
@RequestMapping(path = "rest/permission")
public class PermissionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PermissionRepository permissionRepository;



    /**
     * Saves the provided Permission
     * @param aPermission
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Permission save(@RequestBody Permission aPermission) {
        log.debug("Begin save:" + aPermission);
        permissionRepository.save(aPermission);
        log.debug("End save:" + aPermission);
        return permissionRepository.findOne(aPermission.getId());
    }


    /**
     * Updates a Permission
     * @param aPermission
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Permission update(@RequestBody Permission aPermission) {
        permissionRepository.save(aPermission);
        return permissionRepository.findOne(aPermission.getId());
    }


    /**
     * Gets the Permission by id and returns it
     * @param id
     * @return
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Permission show(@PathVariable Long id) {
        return permissionRepository.findOne(id);
    }


    /**
     * Lists the Permissions matching the request
     * @param page
     * @param size
     * @param sortby
     * @param direction
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Permission> listAll(@RequestParam(value = "page") Integer page,
                                        @RequestParam("size") Integer size,
                                        @RequestParam(value = "sortby", required = false) String sortby,
                                        @RequestParam(value = "dir", required = false) Sort.Direction direction) {

        // DEFAULT Sort property
        if (sortby == null) {
            sortby = "item";
        }

        // DEFAULT Sort direction
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }

        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        return permissionRepository.findAll(pr);
    }


    /**
     * Deletes the specified Permission
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Permission delete(@PathVariable Long id) {
        Permission deleted = permissionRepository.findOne(id);
        permissionRepository.delete(id);
        return deleted;
    }


    /**
     * Catches any errors from this controller
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e) {
        return "You have done something wrong. Try again.";
    }

}
