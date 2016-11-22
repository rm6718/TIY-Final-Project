package com.ironyard.controller.mvc;

import com.ironyard.data.GoalUser;
import com.ironyard.data.Permission;
import com.ironyard.repos.GoalUserRepository;
import com.ironyard.repos.PermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by reevamerchant on 11/15/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/admin")
public class MvcAdminController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoalUserRepository goalUserRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    private void addUserAndPermList(Model model){
        Iterable<GoalUser> users = goalUserRepository.findAll();
        model.addAttribute("users", users);

        Iterable<Permission> permissions = permissionRepository.findAll();
        model.addAttribute("permissions", permissions);
    }



    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String list(Model model) {
        String destination = "/secure/admin_user";
        addUserAndPermList(model);
        return destination;
    }



    @RequestMapping(value = "user/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id,
                       Model model) {
        String destination = "/secure/admin_user";
        GoalUser editUser = goalUserRepository.findOne(id);

        // add a hash of perms this user case (so we can mark them in the checkboxes)
        HashMap<String, String> permsForThisUser = new HashMap<>();
        for (Permission p: editUser.getAbilities()){
            permsForThisUser.put(p.getKey(), p.getKey());
        }

        model.addAttribute("username", editUser.getUsername());
        model.addAttribute("displayname", editUser.getDisplayName());
        model.addAttribute("password", editUser.getPassword());
        model.addAttribute("password2", editUser.getPassword());
        model.addAttribute("id", editUser.getId());
        addUserAndPermList(model);

        return destination;
    }



    @RequestMapping(value = "user/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, Model model, HttpServletRequest req) {
        String destination = "/secure/admin_user";

        GoalUser user = (GoalUser)req.getSession().getAttribute("user");
        if (user.getId() == id){
            model.addAttribute("error_message", "Can delete currently logged in user!");
        } else {
            goalUserRepository.delete(id);
        }
        addUserAndPermList(model);
        return destination;
    }



    @RequestMapping(value = "user/save", method = RequestMethod.POST)
    public String saveUser(@RequestParam("id") Long id,
                           @RequestParam("username") String username,
                           @RequestParam("displayname") String displayname,
                           @RequestParam("password") String password,
                           @RequestParam("password2") String password2,
                           @RequestParam(value = "permissions", required = false ) Long[] perms,
                           Model model) {

        String destination = "redirect:/mvc/secure/admin/users";

        if (!password.equals(password2)) {

            model.addAttribute("error_message", "Passwords do not match!");
            addUserAndPermList(model);
            destination = "/secure/admin_user";

            model.addAttribute("username", username);
            model.addAttribute("displayname", displayname);
            model.addAttribute("id", id);

        } else {
            if (id == null) {

                GoalUser newUser = new GoalUser();
                newUser.setUsername(username);
                newUser.setDisplayName(displayname);
                newUser.setPassword(password);

                if (perms != null) {
                    newUser.setAbilities(new HashSet<>());
                    // fetch perms
                    for (int i = 0; i < perms.length; i++) {
                        newUser.getAbilities().add(permissionRepository.findOne(perms[i]));
                    }
                }
                goalUserRepository.save(newUser);
            } else {

                GoalUser editUser = goalUserRepository.findOne(id);
                editUser.setUsername(username);
                editUser.setDisplayName(displayname);
                editUser.setPassword(password);

                // clear out any existing perms
                if (editUser.getAbilities() != null){
                    editUser.getAbilities().clear();
                } else {
                    editUser.setAbilities(new HashSet<>());
                }

                // add any selected perms
                if (perms != null && perms.length > 0) {
                    // fetch perms and add to user
                    for (int i = 0; i < perms.length; i++) {
                        editUser.getAbilities().add(permissionRepository.findOne(perms[i]));
                    }
                }
                goalUserRepository.save(editUser);
            }
        }

        return destination;

    }
}
