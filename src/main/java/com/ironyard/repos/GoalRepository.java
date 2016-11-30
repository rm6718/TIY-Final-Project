package com.ironyard.repos;

import com.ironyard.data.Goal;
import com.ironyard.data.GoalUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by reevamerchant on 11/7/16.
 */
public interface GoalRepository extends PagingAndSortingRepository<Goal, Long> {

    Iterable<Goal> findByAccomplished(boolean isAccomplished);

}
