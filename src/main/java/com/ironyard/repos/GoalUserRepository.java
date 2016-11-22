package com.ironyard.repos;

import com.ironyard.data.GoalUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by reevamerchant on 11/10/16.
 */
public interface GoalUserRepository extends PagingAndSortingRepository<GoalUser, Long> {

    GoalUser findByUsernameAndPassword(String username, String password);

}
