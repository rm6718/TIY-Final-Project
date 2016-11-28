package com.ironyard.repo;

import com.ironyard.data.Goal;
import com.ironyard.data.GoalUser;
import com.ironyard.data.Permission;
import com.ironyard.repos.GoalRepository;
import com.ironyard.repos.GoalUserRepository;
import com.ironyard.repos.PermissionRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by reevamerchant on 11/21/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GoalUserRepositoryTest {

    @Autowired
    private PermissionRepository permRepo;

    @Autowired
    private GoalRepository goalRepo;

    @Autowired
    private GoalUserRepository userRepo;



    // this will ensure each time the test runs, it starts with no users in the DB
    @Before
    public void clearAllUsers() throws Exception {
        Iterable<GoalUser> users = userRepo.findAll();
        for(GoalUser g: users){
            userRepo.delete(g.getId());
        }
    }



    @Test
    public void testDeleteAUser() throws Exception{
        // create a goal
        Goal savedGoal = goalRepo.save(new Goal("See the star wars movies", "tomorrow", "never seen before"));

        // create permission
        Permission savedPermission = permRepo.save(new Permission("DO_BEFORE_25", "A goal you should complete before you turn 25"));

        //create a user
        GoalUser testUser = new GoalUser("reeva", "password", "Reeva Merchant");

        testUser.setAbilities(new HashSet<>());
        testUser.getAbilities().add(savedPermission);

        userRepo.save(testUser);

        //confirm all relationships
        GoalUser fetchedUser = userRepo.findOne(testUser.getId());

        assertEquals(savedPermission.getId(), fetchedUser.getAbilities().iterator().next().getId());

        long permId = fetchedUser.getAbilities().iterator().next().getId();

        userRepo.delete(fetchedUser.getId());

        Assert.assertNotNull(permRepo.findOne(permId));

    }



    @Test
    public void testFindByUsernameAndPass() throws Exception{

        GoalUser testUser = new GoalUser("reeva", "reeva", "Reeva Merchant");
        userRepo.save(testUser);

        GoalUser found = userRepo.findByUsernameAndPassword("reeva", "reeva");
        Assert.assertNotNull(found);

    }



    @After
    public void tearDown() throws Exception {

    }

}
